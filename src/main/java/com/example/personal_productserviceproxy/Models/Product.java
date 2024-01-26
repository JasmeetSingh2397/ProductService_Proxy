package com.example.personal_productserviceproxy.Models;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Category category;
    private String imageUrl;
    @Transient
    private String searchSimilar;


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
