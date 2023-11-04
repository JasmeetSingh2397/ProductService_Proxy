package com.example.personal_productserviceproxy;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Products;
import org.springframework.stereotype.Component;


public class CommonlyUsedMethods {
    public ProductDto convertToProductDto(Products product){

        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());

        return productDto;
    }
}

