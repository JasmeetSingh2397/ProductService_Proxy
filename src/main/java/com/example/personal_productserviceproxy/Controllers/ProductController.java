package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.NoProductsFoundException;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() throws NoProductsFoundException {

        List<Product> products= productService.getAllProducts();
        List<ProductDto> productDtos= new ArrayList<>();
        for(Product product: products){
            productDtos.add(ProductDto.mapProductToProductDto(product));

        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long ProductId) throws ProductNotFoundException {

        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("auth-token", "heyaccess");
        Product product= productService.getSingleProduct(ProductId);
        if(ProductId<1){
            throw new IllegalArgumentException("Something went wrong");
        }
        return new ResponseEntity<>(ProductDto.mapProductToProductDto(product),headers, HttpStatus.OK);

    }



    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        Product newProduct= productService.addNewProduct(Product.mapProductDtoToProduct(productDto));
        ProductDto newProductDto= ProductDto.mapProductToProductDto(newProduct);

        return new ResponseEntity<>(newProductDto, HttpStatus.OK);


    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        productService.updateProduct(productId, product);
        ProductDto updatedProductDto= ProductDto.mapProductToProductDto(product);

        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        productService.replaceProduct(productId, product);
        ProductDto replacedProductDto= ProductDto.mapProductToProductDto(product);

        return new ResponseEntity<>(replacedProductDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {

        Product product= productService.deleteProduct(productId);
        return new ResponseEntity<>(ProductDto.mapProductToProductDto(product), HttpStatus.OK);
    }

}
