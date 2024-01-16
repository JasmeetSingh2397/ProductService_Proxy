//package com.example.personal_productserviceproxy.Controllers;
//
//import com.example.personal_productserviceproxy.DTOs.ProductDto;
//import com.example.personal_productserviceproxy.DTOs.SearchProductRequestDto;
//import com.example.personal_productserviceproxy.Models.Product;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/search")
//public class SearchController {
//
//    private ProductElasticSearchService productElasticSearchService;
//
//    public SearchController(ProductElasticSearchService productElasticSearchService) {
//        this.productElasticSearchService = productElasticSearchService;
//    }
//
//    @GetMapping("/products")
//    public ResponseEntity<Page<ProductDto>> searchProduct(@ModelAttribute SearchProductRequestDto request){
//
//        Page<Product> products= productElasticSearchService.searchProducts(request.getQuery(),
//                request.getOffset(), request.getSizeOfPage(), request.getSortParamsDtos());
//        return new ResponseEntity<>(ProductDto.getProductDtoPageFromProductsPage(products), HttpStatus.OK);
//    }
//
//
//}
