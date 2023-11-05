package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.CommonlyUsedMethods;
import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    private IProductCategoryService ProductcategoryService;

    @Autowired
    public CategoryController(IProductCategoryService ProductCategoryService) {
        this.ProductcategoryService = ProductCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllProductCategories(){

        List<Categories> categoriesList= ProductcategoryService.getAllProductCategories();

        List<CategoryDTO> categoryDTOList= new ArrayList<>();
        for(Categories categories: categoriesList){
            CategoryDTO categoryDTO= new CategoryDTO();
            categoryDTO.setName(categories.getName());
//            categoryDTO.setDescription(categories.getDescription());
            categoryDTOList.add(categoryDTO);
        }
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsinCategory(@PathVariable("categoryName") String categoryName){

        List<Products> productsList= ProductcategoryService.getProductsInASingleCategory(categoryName);
        List<ProductDto> productDtoList= new ArrayList<>();
        for(Products product: productsList){
            productDtoList.add(CommonlyUsedMethods.convertProductToProductDto(product));
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }



}
