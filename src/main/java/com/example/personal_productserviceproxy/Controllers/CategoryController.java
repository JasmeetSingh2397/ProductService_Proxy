package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Factories.HTTPStatusFactory;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CategoryDTO>> getAllProductCategories() {

        List<Category> categoryList = ProductcategoryService.getAllProductCategories();


        return HTTPStatusFactory.getResponseEntityForGetAllProductCategories(categoryList);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws CategoryNotFoundException {

        List<Product> productList = ProductcategoryService.getProductsInASingleCategory(categoryName);
        return HTTPStatusFactory.getResponseEntityForGetProductsInASingleCategory(productList);
    }

}
