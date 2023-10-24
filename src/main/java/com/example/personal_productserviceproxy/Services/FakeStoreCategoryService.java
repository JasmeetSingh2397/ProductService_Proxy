package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreCategoryDTO;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreCategoryService implements IProductCategoryService{
    
    private FakeStoreClient fakeStoreClient;
    
    public FakeStoreCategoryService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    
    public List<Categories> getAllProductCategories(){
        List<String> fakeStoreCategoryDTOlist= fakeStoreClient.getAllProductCategories();
        List<Categories> categoryList= new ArrayList<>();
        for(String fakeStoreCategoryDTO: fakeStoreCategoryDTOlist){
            Categories category= new Categories();
            category.setName(fakeStoreCategoryDTO);
            categoryList.add(category);
        }
        return categoryList;

    }


    @Override
    public List<Products> getProductsInASingleCategory(String categoryName) {
        List<FakeStoreProductDTO> fakeStoreProductDTOList= fakeStoreClient.getProductsInASingleCategory(categoryName);
        List<Products> productsList= new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOList){
            Products products= new Products();
            products.setId(fakeStoreProductDTO.getId());
            products.setTitle(fakeStoreProductDTO.getTitle());
            products.setPrice(fakeStoreProductDTO.getPrice());
            products.setDescription(fakeStoreProductDTO.getDescription());
            products.setImageUrl(fakeStoreProductDTO.getImageUrl());
            Categories category= new Categories();
            category.setName(fakeStoreProductDTO.getCategory());
            products.setCategory(category);
            productsList.add(products);
        }
        return productsList;
    }


}
