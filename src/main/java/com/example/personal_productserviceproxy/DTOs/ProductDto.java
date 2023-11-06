package com.example.personal_productserviceproxy.DTOs;

import com.example.personal_productserviceproxy.Models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private RatingDto rating;

    public static ProductDto mapProductToProductDto(Product product){

        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
}
