package com.example.personal_productserviceproxy.Controllers;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductService;
import com.example.personal_productserviceproxy.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class SearchController {
    @Autowired
    private IProductService productService;

    @Autowired
    private SearchService searchService;


    //matchAllProducts video content
    @GetMapping("/matchAllProducts")
    public ResponseEntity<Page<ProductDto>> matchAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int size) throws IOException {
        List<Hit<Product>> listOfHits = searchService.matchAllProductsServices();


        List<ProductDto> listOfProductDtos = new ArrayList<>();

        // Step 1: Convert each Product to ProductDto
        for (Hit<Product> hit : listOfHits) {
            ProductDto productDto = ProductDto.mapProductToProductDto(hit.source());
            listOfProductDtos.add(productDto);
        }
        Pageable pageable = PageRequest.of(pageNumber, size);

        // Step 2: Create a Page<ProductDto>

        Page<ProductDto> pageOfProductDtos = new PageImpl<>(listOfProductDtos, pageable, listOfHits.size());


        // Step 3: Return the ResponseEntity
        return ResponseEntity.ok(pageOfProductDtos);
    }

    @GetMapping("/matchAllProducts/{fieldValue}")
    public ResponseEntity<Page<ProductDto>> matchAllProductsWithName(@PathVariable String fieldValue, @RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int size) throws IOException {
        List<Hit<Product>> listOfHits =  searchService.matchProductsWithName(fieldValue);



        List<ProductDto> listOfProductDtos  = new ArrayList<>();

        for (Hit<Product> hit : listOfHits) {
            ProductDto productDto = ProductDto.mapProductToProductDto(hit.source());
            listOfProductDtos.add(productDto);
        }
        Pageable pageable = PageRequest.of(pageNumber, size);

        // Step 2: Create a Page<ProductDto>

        Page<ProductDto> pageOfProductDtos = new PageImpl<>(listOfProductDtos, pageable, listOfHits.size());


        // Step 3: Return the ResponseEntity
        return ResponseEntity.ok(pageOfProductDtos);
    }


}
