package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.CommonlyUsedMethods;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.FakeStoreProductService;
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
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        List<Products> products= productService.getAllProducts();
        List<ProductDto> productDtos= new ArrayList<>();
        for(Products product: products){
            productDtos.add(CommonlyUsedMethods.convertProductToProductDto(product));

        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long ProductId){

        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("auth-token", "heyaccess");
        Products product= productService.getSingleProduct(ProductId);
        if(ProductId<1){
            throw new IllegalArgumentException("Something went wrong");
        }
        return new ResponseEntity<>(CommonlyUsedMethods.convertProductToProductDto(product),headers, HttpStatus.OK);

    }



    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        Products newProduct= productService.addNewProduct(CommonlyUsedMethods.convertProductDtoToProduct(productDto));
        ProductDto newProductDto= CommonlyUsedMethods.convertProductToProductDto(newProduct);

        ResponseEntity<ProductDto> responseEntity= new ResponseEntity<>(newProductDto, HttpStatus.OK);
        return responseEntity;


    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Products product = CommonlyUsedMethods.convertProductDtoToProduct(productDto);
        productService.updateProduct(productId, product);
        ProductDto updatedProductDto= CommonlyUsedMethods.convertProductToProductDto(product);

        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Products product = CommonlyUsedMethods.convertProductDtoToProduct(productDto);
        productService.replaceProduct(productId, product);
        ProductDto replacedProductDto= CommonlyUsedMethods.convertProductToProductDto(product);

        return new ResponseEntity<>(replacedProductDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long productId){

        Products product= productService.deleteProduct(productId);
        return new ResponseEntity<>(CommonlyUsedMethods.convertProductToProductDto(product), HttpStatus.OK);
    }

}
