package com.example.personal_productserviceproxy.DTOs;

import com.example.personal_productserviceproxy.Models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    // You can replace this with MapStruct if preferred
    public static Page<ProductDto> getProductDtoPageFromProductsPage(Page<Product> productPage){
        return productPage.map(ProductDto::mapProductToProductDto);

    }

}
