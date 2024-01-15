//package com.example.personal_productserviceproxy.clients;
//
//import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
//import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//@SpringBootTest
//public class FakestoreClientTest {
//
//    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;
//
//    @Test
//    void TEST_NON_EXISTING_PRODUCT_RETURNS_NULL(){
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO> response= restTemplate.
//                getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,0);
//        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
//
//        assertNull(fakeStoreProductDTO);
//
//    }
//
//
//}
