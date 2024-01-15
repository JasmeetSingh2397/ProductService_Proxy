package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;
    private SelfProductCategoryService selfProductCategoryService;

    private RedisTemplate<Long, Object> redisTemplate;

    public SelfProductService(ProductRepository productRepository, SelfProductCategoryService selfProductCategoryService, RedisTemplate<Long, Object> redisTemplate) {
        this.productRepository = productRepository;
        this.selfProductCategoryService = selfProductCategoryService;
        this.redisTemplate= redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }



    @Override
    public Product getSingleProduct(Long ProductId) throws ProductNotFoundException {
        Product product= (Product) redisTemplate.opsForHash().get(ProductId, "PRODUCTS");
        if(product!=null){
            return product;
        }
        Optional<Product> optionalproduct= productRepository.findById(ProductId);
        if(optionalproduct.isEmpty()){
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        Product product1= optionalproduct.get();
        redisTemplate.opsForHash().put(ProductId, "PRODUCTS", product1);
        return product1;
    }

    @Override
    public Product replaceProduct(Long ProductId, Product product) throws ProductNotFoundException {
        if(!productRepository.existsById(ProductId)){
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        setTheCategoryIfItExistsElseSetNewCategory(product);
        product.setId(ProductId);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long ProductId, Product product) throws ProductNotFoundException {
        if(!productRepository.existsById(ProductId)){
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        setTheCategoryIfItExistsElseSetNewCategory(product);
        product.setId(ProductId);
        return productRepository.save(product);
    }

    private void setTheCategoryIfItExistsElseSetNewCategory(Product product) {
        Category category= product.getCategory();
        Optional<Category> Optionalcategory= selfProductCategoryService.findByName(category.getName());
        if(Optionalcategory.isPresent()){
            product.setCategory(Optionalcategory.get());
        }
        else{
            Category savedCategory= selfProductCategoryService.save(category);
            product.setCategory(savedCategory);

        }
    }


    @Override
    public Product addNewProduct(Product product) {
        setTheCategoryIfItExistsElseSetNewCategory(product);
        return productRepository.save(product);
    }


    @Override
    public Product deleteProduct(Long ProductId) throws ProductNotFoundException {
        Optional<Product> Optionalproduct = productRepository.findById(ProductId);
        if(Optionalproduct.isEmpty()){
            throw new ProductNotFoundException("Product with specified id Not Found");
        }
        productRepository.deleteById(ProductId);
        return Optionalproduct.get();
    }

//    public List<Products> getProductsInASingleCategory(Long categoryId) {
//        if(!categoryRepository.existsById(categoryId)){
//            throw new NullPointerException("Category with specified id Not Found");
//        }
//
//        List<Products> productsList= productRepository.findAllProductsByCategory(categoryId);
//        if(productsList.isEmpty()){
//            throw new NullPointerException("No Products Found in the mentioned category");
//        }
//
//        return productsList;
//
//    }
}
