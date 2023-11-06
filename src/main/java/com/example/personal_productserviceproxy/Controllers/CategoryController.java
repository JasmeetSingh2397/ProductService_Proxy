package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoCategoriesFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoProductsFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoProductsInCategoryException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
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
    public ResponseEntity<List<CategoryDTO>> getAllProductCategories() throws NoCategoriesFoundException {

        List<Category> categoryList = ProductcategoryService.getAllProductCategories();

        List<CategoryDTO> categoryDTOList= new ArrayList<>();
        for(Category category : categoryList){
            CategoryDTO categoryDTO= new CategoryDTO();
            categoryDTO.setName(category.getName());
//            categoryDTO.setDescription(categories.getDescription());
            categoryDTOList.add(categoryDTO);
        }
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws CategoryNotFoundException, NoProductsInCategoryException {

        List<Product> productList = ProductcategoryService.getProductsInASingleCategory(categoryName);
        List<ProductDto> productDtoList= new ArrayList<>();
        for(Product product: productList){
            productDtoList.add(ProductDto.mapProductToProductDto(product));
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }



}
