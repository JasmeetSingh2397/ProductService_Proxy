package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreCategoryService implements IProductCategoryService{
    
    private FakeStoreClient fakeStoreClient;


    public FakeStoreCategoryService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    
    public List<Category> getAllProductCategories(){
        List<String> fakeStoreCategoryDTOlist= fakeStoreClient.getAllProductCategories();
        List<Category> categoryList= new ArrayList<>();
        for(String fakeStoreCategoryDTO: fakeStoreCategoryDTOlist){
            Category category= new Category();
            category.setName(fakeStoreCategoryDTO);
            categoryList.add(category);
        }
        return categoryList;

    }


    @Override
    public List<Product> getProductsInASingleCategory(String categoryName) {
        List<FakeStoreProductDTO> fakeStoreProductDTOList= fakeStoreClient.getProductsInASingleCategory(categoryName);
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOList){
            Product product = new Product();
            product.setId(fakeStoreProductDTO.getId());
            product.setTitle(fakeStoreProductDTO.getTitle());
            product.setPrice(fakeStoreProductDTO.getPrice());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setImageUrl(fakeStoreProductDTO.getImageUrl());
            Category category= new Category();
            category.setName(fakeStoreProductDTO.getCategory());
            product.setCategory(category);
            productList.add(product);
        }
        return productList;
    }


}
