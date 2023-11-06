package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.SelfProductService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {
//    @MockBean
//    IProductService productService;

    @Autowired
    ProductController productController;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @MockBean
    SelfProductService productService;


    @Test
    public void test_whenGetSingleProductIsCalled_ReturnCorrectProduct() throws ProductNotFoundException {
        Product product= new Product();
        product.setId(2l);
        product.setTitle("test");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);
        when(productService.getSingleProduct(any(Long.class))).thenReturn(product);

        ResponseEntity<ProductDto> productsResponseEntity= productController.getSingleProduct(2l);
        assertNotNull(productsResponseEntity);
        assertEquals(2L, productsResponseEntity.getBody().getId());
    }

//    @Test
//    public void test_whengetSingleProductIsCalled_ReturnException(){
//        when(productService.getSingleProduct(any(Long.class))).thenThrow(new RuntimeException("Something is wrong"));
//        assertThrows(RuntimeException.class,
//                ()-> productController.getSingleProduct(2l));
//
//    }

    @Test
    void test_whengetAllProductsCalled_thenReturnProductDto() throws Exception {
        ArrayList<Product> products = new ArrayList<>();


        Product productToCreate = new Product();
        Category category= new Category();
        productToCreate.setCategory(category);
        productToCreate.setId(1L);
        products.add(productToCreate);


        when(productService.getAllProducts()).thenReturn(products);
        List<ProductDto> ExpectedproductDtos= new ArrayList<>();

        ExpectedproductDtos.add(ProductDto.mapProductToProductDto(productToCreate));

        ResponseEntity<List<ProductDto>> productDtosResponseEntity= productController.getAllProducts();
        assertNotNull(productDtosResponseEntity);
        assertEquals(ExpectedproductDtos.get(0).getId(), productDtosResponseEntity.getBody().get(0).getId());
    }


    @Test
    void testWhenGetAllProductsCalledWithNoRecordsInDBThenThrowNullPointerException() throws Exception {

        when(productService.getAllProducts()).thenThrow(new NullPointerException("No Products Found"));

        try{
            productController.getAllProducts();
            fail();


        }catch (NullPointerException e){
            assertThat(e.getMessage()).isEqualTo("No Products Found");
        }

    }

    @Test
    void test_whenputProductCalled_thenReturnProductDto() throws Exception {
        Long id= 1L;
        ProductDto productToReplace = new ProductDto();
        productToReplace.setId(id);

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.replaceProduct(any(Long.class), any(Product.class))).thenReturn(expectedProduct);

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.putProduct(id, productToReplace);

        assertEquals(productDtoExpected.getId(), productDtoResponseEntity.getBody().getId());



    }


//    @Test
//    void test_whenputProductCalled_thenThrowException() throws Exception {
//        Long id= 1L;
//        ProductDto productToReplace = new ProductDto();
//        productToReplace.setId(id);
//
//        when(productService.replaceProduct(any(Long.class), any(Products.class))).
//                thenThrow(new NullPointerException("Product with specified id Not Found"));
//
//        mockMvc.perform(
//                        put("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(productToReplace)))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Product with specified id Not Found"));
//    }
//
    @Test
    void test_whenpatchProductCalled_thenReturnProductDto() throws Exception {
        Long id= 1L;
        ProductDto productToReplace = new ProductDto();
        productToReplace.setId(id);

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.updateProduct(any(Long.class), any(Product.class))).thenReturn(expectedProduct);

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.patchProduct(id, productToReplace);

        assertEquals(productDtoExpected.getId(), productDtoResponseEntity.getBody().getId());
    }

//
//    @Test
//    void test_whenpatchProductCalled_thenThrowException() throws Exception {
//        Long id= 1L;
//        ProductDto productToUpdate = new ProductDto();
//        productToUpdate.setId(id);
//
//        when(productService.updateProduct(any(Long.class), any(Products.class))).
//                thenThrow(new NullPointerException("Product with specified id Not Found"));
//
//        mockMvc.perform(
//                        patch("/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(productToUpdate)))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Product with specified id Not Found"));
//    }
//
    @Test
    void test_whendeleteProductCalled_thenReturnProductDto() throws Exception {
        Product product= new Product();
        product.setId(2l);
        product.setTitle("test");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);
        when(productService.deleteProduct(any(Long.class))).thenReturn(product);

        ResponseEntity<ProductDto> productsResponseEntity= productController.deleteProduct(2l);
        assertNotNull(productsResponseEntity);
        assertEquals(2L, productsResponseEntity.getBody().getId());
    }
//
//    @Test
//    void test_whendeleteProductCalled_thenThrowException() throws Exception {
//        Long id= 1L;
//
//        when(productService.deleteProduct(any(Long.class))).
//                thenThrow(new NullPointerException("Product with specified id Not Found"));
//
//        mockMvc.perform(
//                        delete("/products/{id}", id))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Product with specified id Not Found"));
//    }
//
//
    @Test
    void test_whenaddnewProductCalled_thenReturnProductDto() throws Exception {
        ProductDto productToCreate = new ProductDto();
        productToCreate.setId(1L);

        Category category= new Category();
        Product expectedProduct = new Product();
        expectedProduct.setCategory(category);
        expectedProduct.setId(1L);

        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(expectedProduct);

        when(productService.addNewProduct(any(Product.class))).thenReturn(expectedProduct);

        ResponseEntity<ProductDto> productDtoResponseEntity= productController.addNewProduct(productToCreate);
        assertNotNull(productDtoResponseEntity);
        assertEquals(productDtoExpected.getId(), productDtoResponseEntity.getBody().getId());
    }

//    @Test
//    void test_ifProductControllerCallsProductService_withSameId(){
////        Long id= 1L;
////        when(productService.getSingleProduct(id)).thenCallRealMethod();
////        productController.getSingleProduct(id);
////
////
////        verify(productService).getSingleProduct(idCaptor.capture());
////        verify(productService,times(1)).getSingleProduct(any());
////
////        assertEquals(id,idCaptor.getValue());
//
//        Long id= 1L;
//
//        // No need to mock the `getSingleProduct()` method, as we are calling the real method.
//
//        productController.getSingleProduct(id);
//
//
//        // Verify that the `getSingleProduct()` method was called with the correct argument.
//        verify(productService).getSingleProduct(id);
//
//        // Verify that the `getSingleProduct()` method was called exactly once.
//        verify(productService,times(1)).getSingleProduct(any());
//    }


}
