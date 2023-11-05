package com.example.personal_productserviceproxy;

import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;


public class CommonlyUsedMethods {
    public static ProductDto convertProductToProductDto(Products product){

        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    public static CategoryDTO convertCategoryToCategoryDto(Categories category){

        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Products convertProductDtoToProduct(ProductDto productDto){

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
}

