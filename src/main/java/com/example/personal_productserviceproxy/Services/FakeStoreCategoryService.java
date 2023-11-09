package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoCategoriesFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoProductsInCategoryException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreCategoryService implements IProductCategoryService{
    
    private FakeStoreClient fakeStoreClient;


    public FakeStoreCategoryService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    
    public List<Category> getAllProductCategories() throws NoCategoriesFoundException {
        List<String> fakeStoreCategoryDTOlist= fakeStoreClient.getAllProductCategories();

        if (fakeStoreCategoryDTOlist.isEmpty()){
            throw new NoCategoriesFoundException("No Categories Found in Fakestore");
        }

        List<Category> categoryList= new ArrayList<>();
        for(String fakeStoreCategoryDTO: fakeStoreCategoryDTOlist){
            Category category= new Category();
            category.setName(fakeStoreCategoryDTO);
            categoryList.add(category);
        }
        return categoryList;

    }


    @Override
    public List<Product> getProductsInASingleCategory(String categoryName) throws CategoryNotFoundException, NoProductsInCategoryException {
        try {
            List<FakeStoreProductDTO> fakeStoreProductDTOList = fakeStoreClient.
                    getProductsInASingleCategory(categoryName);
            if (fakeStoreProductDTOList.isEmpty()){
                throw new NoProductsInCategoryException("No Products Found in the mentioned Category");
            }
            List<Product> productList = new ArrayList<>();
            for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOList){
                Product product = mapFakeStoreProductDTOToProduct(fakeStoreProductDTO);
                productList.add(product);

            }
            return productList;
        }catch(Exception e){
            throw new CategoryNotFoundException("Category Not Found in Fakestore");

        }


    }

    private Product mapFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImageUrl());
        Category category= new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }


}
