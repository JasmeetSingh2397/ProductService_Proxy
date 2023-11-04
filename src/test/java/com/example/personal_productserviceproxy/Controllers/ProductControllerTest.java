package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.CommonlyUsedMethods;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {
    @MockBean
    IProductService productService;

    @Autowired
    ProductController productController;


    CommonlyUsedMethods commonlyUsedMethods= new CommonlyUsedMethods();

    @Test
    public void test_whenGetSingleProductIsCalled_ReturnCorrectProduct(){
        Products product= new Products();
        product.setId(2l);
        product.setTitle("test");
        Categories categories= new Categories();
        categories.setName("Electronics");
        product.setCategory(categories);
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
        ArrayList<Products> products = new ArrayList<>();


        Products productToCreate = new Products();
        Categories category= new Categories();
        productToCreate.setCategory(category);
        productToCreate.setId(1L);
        products.add(productToCreate);


        when(productService.getAllProducts()).thenReturn(products);
        List<ProductDto> ExpectedproductDtos= new ArrayList<>();

        ExpectedproductDtos.add(commonlyUsedMethods.convertToProductDto(productToCreate));

        ResponseEntity<List<ProductDto>> productDtosResponseEntity= productController.getAllProducts();
        assertNotNull(productDtosResponseEntity);
        assertEquals(ExpectedproductDtos.get(0).getId(), productDtosResponseEntity.getBody().get(0).getId());
    }


//    @Test
//    void test_whengetAllProductsCalled_thenIsExceptionHandledOrNot() throws Exception {
//
//        when(productService.getAllProducts()).thenThrow(new NullPointerException("No Products Found"));
////        assertThrows(NullPointerException.class,
////                ()-> productController.getAllProducts());
//
//    }

    @Test
    void test_whenputProductCalled_thenReturnProductDto() throws Exception {
        Long id= 1L;
        ProductDto productToReplace = new ProductDto();
        productToReplace.setId(id);

        Categories category= new Categories();
        Products expectedProduct = new Products();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= commonlyUsedMethods.convertToProductDto(expectedProduct);

        when(productService.replaceProduct(any(Long.class), any(Products.class))).thenReturn(expectedProduct);

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

        Categories category= new Categories();
        Products expectedProduct = new Products();
        expectedProduct.setCategory(category);
        expectedProduct.setId(id);

        ProductDto productDtoExpected= commonlyUsedMethods.convertToProductDto(expectedProduct);

        when(productService.updateProduct(any(Long.class), any(Products.class))).thenReturn(expectedProduct);

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
        Products product= new Products();
        product.setId(2l);
        product.setTitle("test");
        Categories categories= new Categories();
        categories.setName("Electronics");
        product.setCategory(categories);
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

        Categories category= new Categories();
        Products expectedProduct = new Products();
        expectedProduct.setCategory(category);
        expectedProduct.setId(1L);

        ProductDto productDtoExpected= commonlyUsedMethods.convertToProductDto(expectedProduct);

        when(productService.addNewProduct(any(Products.class))).thenReturn(expectedProduct);

        ResponseEntity<ProductDto> productDtoResponseEntity= productController.addNewProduct(productToCreate);
        assertNotNull(productDtoResponseEntity);
        assertEquals(productDtoExpected.getId(), productDtoResponseEntity.getBody().getId());
    }

}
