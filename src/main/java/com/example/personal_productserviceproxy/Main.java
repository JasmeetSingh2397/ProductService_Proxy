package com.example.personal_productserviceproxy;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.FakeStoreProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;

public class Main {
    public static void main(String[] args) {
        RestTemplateBuilder restTemplateBuilder= new RestTemplateBuilder();
        FakeStoreClient fakeStoreClient = new FakeStoreClient(restTemplateBuilder);
        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(fakeStoreClient);
        Products product= new Products();
        Categories categories= new Categories();
        categories.setName("Electronics");

        product.setId(1L);
        product.setTitle("JasmeetSingh");
        product.setPrice(100.0);
        product.setDescription("This is a test description");
        product.setImageUrl("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        product.setCategory(categories);
        fakeStoreProductService.updateProduct(1L, product);
    }
}
