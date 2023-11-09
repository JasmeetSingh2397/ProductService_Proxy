package com.example.personal_productserviceproxy.Factories;

import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class HTTPStatusFactory {

    public static ResponseEntity<ProductDto> getResponseEntityForPutProduct(Product product){


        ProductDto productDto= ProductDto.mapProductToProductDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);


    }

    public static ResponseEntity<ProductDto> getResponseEntityForPatchProduct(Product product){


        ProductDto productDto= ProductDto.mapProductToProductDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);


    }

    public static ResponseEntity<ProductDto> getResponseEntityForGetSingleProduct(Product product){
        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("auth-token", "heyaccess");
        return new ResponseEntity<>(ProductDto.mapProductToProductDto(product),headers, HttpStatus.OK);
    }

    public static ResponseEntity<ProductDto> getResponseEntityForDeleteProduct(Product product){

        return new ResponseEntity<>(ProductDto.mapProductToProductDto(product), HttpStatus.OK);


    }


    public static ResponseEntity<List<ProductDto>> getResponseEntityForGetAllProducts(List<Product> products){

        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            List<ProductDto> productDtos= new ArrayList<>();
            for(Product product: products){
                productDtos.add(ProductDto.mapProductToProductDto(product));

            }

            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        }

    }

    public static ResponseEntity<ProductDto> getResponseEntityForAddNewProduct(Product product){

        ProductDto newProductDto= ProductDto.mapProductToProductDto(product);
        return new ResponseEntity<>(newProductDto, HttpStatus.OK);
    }

    public static ResponseEntity<List<CategoryDTO>> getResponseEntityForGetAllProductCategories(List<Category> categoryList){
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            List<CategoryDTO> categoryDTOList= new ArrayList<>();

            for(Category category : categoryList){
                CategoryDTO categoryDTO= new CategoryDTO();
                categoryDTO.setName(category.getName());
                categoryDTOList.add(categoryDTO);
            }
            return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
        }
    }

    public static ResponseEntity<List<ProductDto>> getResponseEntityForGetProductsInASingleCategory(List<Product> productList){
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            List<ProductDto> productDtoList= new ArrayList<>();
            for(Product product: productList){
                productDtoList.add(ProductDto.mapProductToProductDto(product));
            }
            return new ResponseEntity<>(productDtoList, HttpStatus.OK);
        }
    }



}
