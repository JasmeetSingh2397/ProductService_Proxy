package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Repositories.CategoryRepository;
import com.example.personal_productserviceproxy.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private SelfProductCategoryService selfProductCategoryService;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Products> getAllProducts() {
        if(productRepository.findAll().isEmpty()){
            throw new NullPointerException("No Products Found");
        }

        return productRepository.findAll();
    }

    @Override
    public Products getSingleProduct(Long ProductId) {
        Optional<Products> optionalproduct= productRepository.findById(ProductId);
        if (optionalproduct.isEmpty()){
            throw new NullPointerException("Product with specified id Not Found");
        }
        return optionalproduct.get();
    }

    @Override
    public Products replaceProduct(Long ProductId, Products product){
        if(!productRepository.existsById(ProductId)){
            throw new NullPointerException("Product with specified id Not Found");
        }
        checkifCategoryNameExists(product);
        product.setId(ProductId);
        return productRepository.save(product);
    }

    @Override
    public Products updateProduct(Long ProductId, Products product) {
        if(!productRepository.existsById(ProductId)){
            throw new NullPointerException("Product with specified id Not Found");
        }
        checkifCategoryNameExists(product);
        product.setId(ProductId);
        return productRepository.save(product);
    }

    private void checkifCategoryNameExists(Products product) {
        Categories category= product.getCategory();
        Optional<Categories> Optionalcategory= categoryRepository.findByName(category.getName());
        if(Optionalcategory.isPresent()){
            product.setCategory(Optionalcategory.get());
        }
        else{
            Categories savedCategory= categoryRepository.save(category);
            product.setCategory(savedCategory);

        }
    }


    @Override
    public Products addNewProduct(Products product) {
        checkifCategoryNameExists(product);
        return productRepository.save(product);
    }


    @Override
    public Products deleteProduct(Long ProductId) {
        Optional<Products> Optionalproduct = productRepository.findById(ProductId);
        if(Optionalproduct.isEmpty()){
            throw new NullPointerException("Product with specified id Not Found");
        }
        productRepository.deleteById(ProductId);
        return Optionalproduct.get();
    }

//    public List<Products> getProductsInASingleCategory(Long categoryId) {
//        if(!categoryRepository.existsById(categoryId)){
//            throw new NullPointerException("Category with specified id Not Found");
//        }
//
//        List<Products> productsList= productRepository.findAllProductsByCategory(categoryId);
//        if(productsList.isEmpty()){
//            throw new NullPointerException("No Products Found in the mentioned category");
//        }
//
//        return productsList;
//
//    }
}
