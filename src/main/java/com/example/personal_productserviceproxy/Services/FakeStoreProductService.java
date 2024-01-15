package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class FakeStoreProductService implements IProductService{


    private FakeStoreClient fakeStoreClient;
    private RedisTemplate<Long, Object> redisTemplate;



    public FakeStoreProductService(FakeStoreClient fakeStoreClient, RedisTemplate<Long, Object> redisTemplate) {

        this.fakeStoreClient= fakeStoreClient;
        this.redisTemplate = redisTemplate;
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
    public List<Product> getAllProducts() {


        List<FakeStoreProductDTO> fakestoreproductDtos= fakeStoreClient.getAllProducts();
        List<Product> products= new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreproductDto: fakestoreproductDtos){
            products.add(getProduct(fakeStoreproductDto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO= (FakeStoreProductDTO) redisTemplate.opsForHash().get(productId, "PRODUCTS");
        if(fakeStoreProductDTO!=null){
            return getProduct(fakeStoreProductDTO);
        }
        FakeStoreProductDTO productDto = fakeStoreClient.getSingleProduct(productId);

        if (productDto == null) {
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto);
        return getProduct(productDto);
    }

    @Override
    public Product updateProduct(Long ProductId, Product product) throws ProductNotFoundException {
        if (fakeStoreClient.getSingleProduct(ProductId) == null) {
            throw new ProductNotFoundException("Product with specified id Not Found");
        }


        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.updateProduct(ProductId,
                getFakeStoreProductDTOfromProduct(product));



        return getProduct(fakeStoreProductDTO);
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
    public Product deleteProduct(Long productId) throws ProductNotFoundException {

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.deleteProduct(productId);
        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        return getProduct(fakeStoreProductDTO);
    }



    @Override
    public Product replaceProduct(Long ProductId, Product product) throws ProductNotFoundException {

        if (fakeStoreClient.getSingleProduct(ProductId) == null) {
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.replaceProduct(ProductId,
                getFakeStoreProductDTOfromProduct(product));


        return getProduct(fakeStoreProductDTO);
    }


}


