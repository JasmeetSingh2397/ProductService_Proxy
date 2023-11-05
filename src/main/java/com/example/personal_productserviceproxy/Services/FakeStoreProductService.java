package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreProductService implements IProductService{


    private FakeStoreClient fakeStoreClient;


    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {

        this.fakeStoreClient= fakeStoreClient;
    }

    private Products getProduct(FakeStoreProductDTO fakestoreproductDto) {
        Products product = new Products();
        product.setId(fakestoreproductDto.getId());
        product.setTitle(fakestoreproductDto.getTitle());
        product.setPrice(fakestoreproductDto.getPrice());
        Categories category = new Categories();
        category.setName(fakestoreproductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakestoreproductDto.getImageUrl());
        product.setDescription(fakestoreproductDto.getDescription());
        return product;
    }
    @Override
    public List<Products> getAllProducts() {
//        RestTemplate restTemplate= restTemplateBuilder.build();
//        ResponseEntity<ProductDto[]> productDtos=restTemplate.getForEntity("https://fakestoreapi.com/products",
//                ProductDto[].class);

        List<FakeStoreProductDTO> fakestoreproductDtos= fakeStoreClient.getAllProducts();

        List<Products> products= new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreproductDto: fakestoreproductDtos){
            products.add(getProduct(fakeStoreproductDto));
        }
        return products;
    }

    @Override
    public Products getSingleProduct(Long productId) {
//        RestTemplate restTemplate= restTemplateBuilder.build();
//        ResponseEntity<ProductDto> productDto= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
//                ProductDto.class,ProductId);
        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.getSingleProduct(productId);
        Products product= getProduct(fakeStoreProductDTO);
        return product;
    }

    @Override
    public Products updateProduct(Long ProductId, Products product) {
        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.updateProduct(ProductId,
                getFakeStoreProductDTOfromProduct(product));
//        System.out.println(fakeStoreProductDTO.getDescription());
//        System.out.println(fakeStoreProductDTO.getTitle());
//        System.out.println(fakeStoreProductDTO.getPrice());

        return getProduct(fakeStoreProductDTO);
    }

    public FakeStoreProductDTO getFakeStoreProductDTOfromProduct(Products product){
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
    public Products addNewProduct(Products product) {
//        RestTemplate restTemplate= restTemplateBuilder.build();
//        ResponseEntity<ProductDto> productDtoResponseEntity= restTemplate.postForEntity("https://fakestoreapi.com/products",
//                productDto, ProductDto.class);

        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.addNewProduct(getFakeStoreProductDTOfromProduct(product));
        return getProduct(fakeStoreProductDTO);
    }

    @Override
    public Products deleteProduct(Long ProductId) {

        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreClient.deleteProduct(ProductId);
        return getProduct(fakeStoreProductDTO);
    }

    public Products replaceProduct(Long ProductId, Products product) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.putProduct(ProductId, getFakeStoreProductDTOfromProduct(product));
        return getProduct(fakeStoreProductDTO);
    }
}

