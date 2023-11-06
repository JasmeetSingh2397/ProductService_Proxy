package com.example.personal_productserviceproxy.Models;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Category category;
    private String imageUrl;


    public static Product mapProductDtoToProduct(ProductDto productDto){

        Product product= new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category= new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
