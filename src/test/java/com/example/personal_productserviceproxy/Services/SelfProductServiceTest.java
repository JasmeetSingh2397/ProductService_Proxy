package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Controllers.ProductController;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.NoProductsFoundException;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {

    @Autowired
    SelfProductService productService;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @MockBean
    ProductRepository productRepository;


    @Test
    public void test_whenGetSingleProductIsCalled_thenReturnCorrectProduct() throws ProductNotFoundException {
        Product product= new Product();
        product.setId(2L);
        product.setTitle("test");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);
        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(product));

        Product fetchedproduct= productService.getSingleProduct(2l);
        assertNotNull(fetchedproduct);
        assertEquals(2L, fetchedproduct.getId());
    }

    @Test
    public void test_whenGetSingleProductIsCalled_thenThrowProductNotFoundException(){
        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        assertThrows(ProductNotFoundException.class,
                ()-> productService.getSingleProduct(2L));

    }

    @Test
    void test_whenGetAllProductsIsCalled_thenReturnCorrectProductList() throws NoProductsFoundException {
        ArrayList<Product> products = new ArrayList<>();


        Product productToCreate = new Product();
        Category category= new Category();
        productToCreate.setCategory(category);
        productToCreate.setId(1L);
        products.add(productToCreate);

        when(productRepository.findAll()).thenReturn(products);
       

        List<Product> expectedProducts= productService.getAllProducts();
        assertNotNull(expectedProducts);
        assertEquals(expectedProducts.get(0).getId(), products.get(0).getId());
    }


    @Test
    void test_whenGetAllProductsIsCalledWithNoRecordsInDB_thenThrowNoProductsFoundException() {

        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ProductNotFoundException.class,
                ()-> productService.getSingleProduct(2L));
    }

    @Test
    void test_whenReplaceIsProductIsCalled_thenReturnTheReplacedProduct() throws ProductNotFoundException {
        Long id= 1L;
        Category category= new Category();
        category.setName("Electronics");
        Product productToReplaceWith = new Product();
        productToReplaceWith.setCategory(category);
        productToReplaceWith.setId(id);

        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);


        when(productRepository.existsById(any(Long.class))).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);


        Product replacedProduct = productService.replaceProduct(id, productToReplaceWith);
        assertNotNull(replacedProduct);
        assertEquals(expectedProduct.getId(), replacedProduct.getId());

    }


        @Test
    void test_whenReplaceProductIsCalled_thenThrowProductNotFoundException() {
        Long id= 1L;
        Category category= new Category();
        category.setName("Electronics");
        Product productToReplaceWith = new Product();
        productToReplaceWith.setCategory(category);
        productToReplaceWith.setId(id);

        when(productRepository.existsById(any(Long.class))).thenReturn(false);

        assertThrows(ProductNotFoundException.class,
                ()-> productService.replaceProduct(2L, productToReplaceWith));
    }

    @Test
    void test_whenUpdateProductIsCalled_thenReturnCorrectProduct() throws ProductNotFoundException {
        Long id= 1L;
        Category category= new Category();
        category.setName("Electronics");
        Product productToReplaceWith = new Product();
        productToReplaceWith.setCategory(category);
        productToReplaceWith.setId(id);

        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);


        when(productRepository.existsById(any(Long.class))).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);


        Product replacedProduct = productService.updateProduct(id, productToReplaceWith);
        assertNotNull(replacedProduct);
        assertEquals(expectedProduct.getId(), replacedProduct.getId());

    }


    @Test
    void test_whenUpdateProductCalled_thenThrowProductNotFoundException(){
        Long id= 1L;
        Category category= new Category();
        category.setName("Electronics");
        Product productToReplaceWith = new Product();
        productToReplaceWith.setCategory(category);
        productToReplaceWith.setId(id);

        when(productRepository.existsById(any(Long.class))).thenReturn(false);

        assertThrows(ProductNotFoundException.class,
                ()-> productService.updateProduct(2L, productToReplaceWith));
    }

    @Test
    void test_whenDeleteProductIsCalled_thenReturnTheDeletedProduct() throws ProductNotFoundException {

        Product product= new Product();
        product.setId(2L);
        product.setTitle("test");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);

        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(product));


        Product fetchedproduct= productService.deleteProduct(2L);
        assertNotNull(fetchedproduct);
        assertEquals(2L, fetchedproduct.getId());

    }

    @Test
    void test_whenDeleteProductIsCalled_thenThrowProductNotFoundException() throws Exception {

        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());
        assertThrows(ProductNotFoundException.class,
                ()-> productService.deleteProduct(2L));
    }
//
//
    @Test
    void test_whenAddNewProductIsCalled_thenReturnTheAddedProduct() throws Exception {

        Category category= new Category();
        category.setName("Electronics");
        Product productToAdd = new Product();

        productToAdd.setCategory(category);


        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        Product addedProduct = productService.addNewProduct(productToAdd);
        assertNotNull(addedProduct);
        assertEquals(expectedProduct.getId(), addedProduct.getId());
    }


}
