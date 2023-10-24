package com.example.personal_productserviceproxy.Controllers;

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
            productDtos.add(convertToProductDto(product));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long ProductId){
        try{
            MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            Products product= productService.getSingleProduct(ProductId);
            if(ProductId<1){
                throw new IllegalArgumentException("Something went wrong");
            }
            return new ResponseEntity<>(convertToProductDto(product),headers, HttpStatus.OK);

        }catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

    public Products convertProductDtoToProduct(ProductDto productDto){

        Products product= new Products();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category= new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        return product;
    }
    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        Products newProduct= productService.addNewProduct(convertProductDtoToProduct(productDto));
        ResponseEntity<ProductDto> responseEntity= new ResponseEntity<>(productDto, HttpStatus.OK);
        return responseEntity;


    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Products product = convertProductDtoToProduct(productDto);
        productService.updateProduct(productId, product);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Products product = convertProductDtoToProduct(productDto);
        productService.replaceProduct(productId, product);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long productId){

        Products product= productService.deleteProduct(productId);
        return new ResponseEntity<>(convertToProductDto(product), HttpStatus.OK);
    }

    public ProductDto convertToProductDto(Products product){
        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
