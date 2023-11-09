package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Exceptions.NoProductsFoundException;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

//@Service

public class FakeStoreProductService implements IProductService{


    private FakeStoreClient fakeStoreClient;


    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {

        this.fakeStoreClient= fakeStoreClient;
    }

    private Product getProduct(FakeStoreProductDTO fakestoreproductDto) {
        Product product = new Product();
        product.setId(fakestoreproductDto.getId());
        product.setTitle(fakestoreproductDto.getTitle());
        product.setPrice(fakestoreproductDto.getPrice());
        Category category = new Category();
        category.setName(fakestoreproductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakestoreproductDto.getImageUrl());
        product.setDescription(fakestoreproductDto.getDescription());
        return product;
    }
    @Override
    public List<Product> getAllProducts() throws NoProductsFoundException {


        List<FakeStoreProductDTO> fakestoreproductDtos= fakeStoreClient.getAllProducts();
        if(fakestoreproductDtos.isEmpty()){
            throw new NoProductsFoundException("No Products Found");
        }
        List<Product> products= new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreproductDto: fakestoreproductDtos){
            products.add(getProduct(fakeStoreproductDto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
      try {
            FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.getSingleProduct(productId);
            return getProduct(fakeStoreProductDTO);
        } catch (Exception e) {
            if( e instanceof  RestClientException){
                throw new RestClientException("An error occurred while fetching data. Please try again later.");
            }
            else {
                throw new ProductNotFoundException("Product with specified id Not Found");
            }
        }

    }

    @Override
    public Product updateProduct(Long ProductId, Product product) throws ProductNotFoundException {

        try {
            FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.updateProduct(ProductId,
                    getFakeStoreProductDTOfromProduct(product));
            return getProduct(fakeStoreProductDTO);
        } catch (Exception e) {
            if( e instanceof  RestClientException){
                throw new RestClientException("An error occurred while fetching data. Please try again later.");
            }
            else {
                throw new ProductNotFoundException("Product with specified id Not Found");
            }
        }
    }

    private FakeStoreProductDTO getFakeStoreProductDTOfromProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDTO= new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImageUrl(product.getImageUrl());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductDTO;
    }
    @Override
    public Product addNewProduct(Product product) {


        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.addNewProduct(
                getFakeStoreProductDTOfromProduct(product));
        return getProduct(fakeStoreProductDTO);
    }

    @Override
    public Product deleteProduct(Long ProductId) throws ProductNotFoundException {

        try{
            FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.deleteProduct(ProductId);
            return getProduct(fakeStoreProductDTO);
        }catch (Exception e) {
            if( e instanceof  RestClientException){
                throw new RestClientException("An error occurred while fetching data. Please try again later.");
            }
            else {
                throw new ProductNotFoundException("Product with specified id Not Found");
            }
        }
    }

    @Override
    public Product replaceProduct(Long ProductId, Product product) throws ProductNotFoundException {
        try{

            FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.replaceProduct(ProductId, getFakeStoreProductDTOfromProduct(product));
            return getProduct(fakeStoreProductDTO);
        }catch (Exception e) {
            if( e instanceof  RestClientException){
                throw new RestClientException("An error occurred while fetching data. Please try again later.");
            }
            else {
                throw new ProductNotFoundException("Product with specified id Not Found");
            }
        }

    }
}

