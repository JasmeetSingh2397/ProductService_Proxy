package com.example.personal_productserviceproxy.Clients.FakeStore.Client;

import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreCategoryDTO;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.FakeStoreProductService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDTO> getAllProducts(){
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity= restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class);
        return List.of(responseEntity.getBody());
    }

    public FakeStoreProductDTO getSingleProduct(Long ProductId) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDto= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDTO.class,ProductId);
       return fakeStoreproductDto.getBody();
    }

    public FakeStoreProductDTO addNewProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= restTemplate.postForEntity("https://fakestoreapi.com/products",
                fakeStoreProductDTO, FakeStoreProductDTO.class);

        return fakeStoreproductDtoResponseEntity.getBody();
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate= restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreProductDTO updateProduct(Long ProductId, FakeStoreProductDTO fakeStoreProductDTO) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(fakeStoreProductDTO);
        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,requestEntity, FakeStoreProductDTO.class,ProductId);
//        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= requestForEntity(HttpMethod.PATCH,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDTO,
//                FakeStoreProductDTO.class,
//                ProductId);
        return fakeStoreproductDtoResponseEntity.getBody();

    }

    public FakeStoreProductDTO putProduct(Long ProductId, FakeStoreProductDTO fakeStoreProductDTO) {
//        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= requestForEntity(HttpMethod.PUT,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDTO,
//                FakeStoreProductDTO.class,
//                ProductId);

        RestTemplate restTemplate= restTemplateBuilder.build();
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(fakeStoreProductDTO);
        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,requestEntity, FakeStoreProductDTO.class,ProductId);

        return fakeStoreproductDtoResponseEntity.getBody();

    }

    public FakeStoreProductDTO deleteProduct(Long productId){
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE, null, FakeStoreProductDTO.class,productId);
//        ResponseEntity<FakeStoreProductDTO> fakeStoreproductDtoResponseEntity= requestForEntity(HttpMethod.DELETE,
//                "https://fakestoreapi.com/products/{id}",
//                null,
//                FakeStoreProductDTO.class,
//                productId);
        return fakeStoreproductDtoResponseEntity.getBody();
    }

    public List<String> getAllProductCategories(){
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<String[]> fakeStoreCategoryDtoResponseEntities= restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                String[].class);
        return List.of(fakeStoreCategoryDtoResponseEntities.getBody());
    }

    public List<FakeStoreProductDTO> getProductsInASingleCategory(String categoryName){
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductDtoResponseEntities=
                restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}",
                FakeStoreProductDTO[].class,categoryName);
        return List.of(fakeStoreProductDtoResponseEntities.getBody());
    }


}
