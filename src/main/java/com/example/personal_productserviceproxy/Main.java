package com.example.personal_productserviceproxy;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.FakeStoreProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;

public class Main {
    public static void main(String[] args) {
        RestTemplateBuilder restTemplateBuilder= new RestTemplateBuilder();
        FakeStoreClient fakeStoreClient = new FakeStoreClient(restTemplateBuilder);
        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(fakeStoreClient);
        Product a= new Product();
        Category category = new Category();
        category.setName("Electronics");

        a.setId(1L);
        a.setTitle("JasmeetSingh");
        a.setPrice(100.0);
        a.setDescription("This is a test description");
        a.setImageUrl("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        a.setCategory(category);
        fakeStoreProductService.updateProduct(1L,a );

//    Product(1L, a);
    }
}
