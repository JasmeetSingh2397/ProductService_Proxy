package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Repositories.CategoryRepository;
import com.example.personal_productserviceproxy.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductCategoryService implements IProductCategoryService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductCategoryService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Categories> getAllProductCategories() {
        List<Categories> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()){
            throw new NullPointerException("No Categories Found");
        }

        return categoryList;
    }




    @Override
    public List<Products> getProductsInASingleCategory(String categoryName) {
//        Optional<Categories> Optionalcategory= categoryRepository.findByName(categoryName);
//        if (!Optionalcategory.isPresent()){
//            throw new NullPointerException("Category Not Found");
//        }
//        Categories category= Optionalcategory.get();
//        List<Products> productsList= productRepository.findAllProductsbycategory(category);
//        return productsList;
        return null;

    }
}
