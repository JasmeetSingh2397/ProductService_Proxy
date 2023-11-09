package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IProductService productService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void test_whenGetAllProductsIsCalled_thenReturnCorrectProductDtoList() throws Exception {
        ArrayList<Product> products = new ArrayList<>();

        Product productToCreate = new Product();
        Category category= new Category();
        productToCreate.setCategory(category);
        products.add(productToCreate);

        when(productService.getAllProducts()).thenReturn(products);
        List<ProductDto> productDtos= new ArrayList<>();

        productDtos.add(ProductDto.mapProductToProductDto(products.get(0)));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtos)));
    }


    @Test
    void test_whenGetAllProductsIsCalled_thenReturnStatusNoContent() throws Exception {

        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products"))
                .andExpect(status().isNoContent());

    }

//    @Test
//    void test_whenGetAllProductsIsCalled_thenThrowInternalServerException() throws Exception {
//
//        when(productService.getAllProducts()).thenThrow(new HttpServerErrorException("No Products Found");
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("No Products Found"));
//    }

    @Test
    void test_whenGetSingleProductIsCalled_thenReturnCorrectProductDto() throws Exception {
        Long id= 1L;
        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.getSingleProduct(any(Long.class))).thenReturn(expectedProduct);

        mockMvc.perform(
                        get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoExpected)));
    }

    @Test
    void test_whenGetSingleProductIsCalled_thenThrowProductNotFoundException() throws Exception {
        Long id= 1L;

        when(productService.getSingleProduct(any(Long.class))).
                thenThrow(new ProductNotFoundException("Product with specified id Not Found"));

        mockMvc.perform(
                        get("/products/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with specified id Not Found"));
    }


    @Test
    void test_whenPutProductIsCalled_thenReturnCorrectProductDto() throws Exception {
        Long id= 1L;
        ProductDto productToReplace = new ProductDto();
        productToReplace.setId(id);

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.replaceProduct(any(Long.class), any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(
                        put("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToReplace)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoExpected)));
    }


    @Test
    void test_whenPutProductIsCalled_thenThrowProductNotFoundException() throws Exception {
        Long id= 1L;
        ProductDto productToReplace = new ProductDto();
        productToReplace.setId(id);

        when(productService.replaceProduct(any(Long.class), any(Product.class))).
                thenThrow(new ProductNotFoundException("Product with specified id Not Found"));

        mockMvc.perform(
                        put("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToReplace)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with specified id Not Found"));
    }

    @Test
    void test_whenPatchProductIsCalled_thenReturnCorrectProductDto() throws Exception {
        Long id= 1L;
        ProductDto productToUpdate = new ProductDto();
        productToUpdate.setId(id);

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(
                        patch("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoExpected)));
    }


    @Test
    void test_whenPatchProductIsCalled_thenThrowProductNotFoundException() throws Exception {
        Long id= 1L;
        ProductDto productToUpdate = new ProductDto();
        productToUpdate.setId(id);

        when(productService.updateProduct(any(Long.class), any(Product.class))).
                thenThrow(new ProductNotFoundException("Product with specified id Not Found"));

        mockMvc.perform(
                        patch("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToUpdate)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with specified id Not Found"));
    }

    @Test
    void test_whenDeleteProductIsCalled_thenReturnCorrectProductDto() throws Exception {
        Long id= 1L;
        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.deleteProduct(any(Long.class))).thenReturn(expectedProduct);

        mockMvc.perform(
                        delete("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoExpected)));
    }

    @Test
    void test_whenDeleteProductIsCalled_thenThrowProductNotFoundException() throws Exception {
        Long id= 1L;

        when(productService.deleteProduct(any(Long.class))).
                thenThrow(new ProductNotFoundException("Product with specified id Not Found"));

        mockMvc.perform(
                        delete("/products/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with specified id Not Found"));
    }


    @Test
    void test_whenAddNewProductIsCalled_thenReturnCorrectProductDto() throws Exception {
        ProductDto productToCreate = new ProductDto();
        productToCreate.setTitle("Nikhil");

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setTitle("Nikhil");

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.addNewProduct(any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtoExpected)))
                .andExpect(jsonPath("$.title", is("Nikhil")));
    }




//    @Test
//    void test_whenaddnewProductCalled_thenThrowException() throws Exception {
//
//
//    }






}
