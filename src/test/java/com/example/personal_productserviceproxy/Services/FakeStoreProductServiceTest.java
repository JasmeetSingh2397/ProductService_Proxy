//package com.example.personal_productserviceproxy.Services;
//
//import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
//import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
//import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
//import com.example.personal_productserviceproxy.Models.Category;
//import com.example.personal_productserviceproxy.Models.Product;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class FakeStoreProductServiceTest {
//    @MockBean
//    private FakeStoreClient fakeStoreClient;
//
//    @Autowired
//    private FakeStoreProductService fakeStoreProductService;
//
//    @Test
//    public void test_whenGetSingleProductIsCalled_thenReturnCorrectProduct() throws ProductNotFoundException {
//
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(fakeStoreProductDTO);
//
//        Product fetchedProduct = fakeStoreProductService.getSingleProduct(1L);
//        assertNotNull(fetchedProduct);
//        assertEquals(1L, fetchedProduct.getId());
//    }
//
//    @Test
//    public void test_whenGetSingleProductIsCalled_thenThrowProductNotFoundException(){
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(null);
//        assertThrows(ProductNotFoundException.class,
//                ()-> fakeStoreProductService.getSingleProduct(2L));
//
//    }
//
//    @Test
//    void test_whenGetAllProductsIsCalled_thenReturnCorrectProductList(){
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//        List<FakeStoreProductDTO> fakeStoreProductDTOList = new ArrayList<>();
//
//        fakeStoreProductDTOList.add(fakeStoreProductDTO);
//
//        when(fakeStoreClient.getAllProducts()).thenReturn(fakeStoreProductDTOList);
//
//
//        List<Product> expectedProducts= fakeStoreProductService.getAllProducts();
//        assertNotNull(expectedProducts);
//        assertEquals(expectedProducts.get(0).getId(), fakeStoreProductDTOList.get(0).getId());
//    }
////
////
//    @Test
//    void test_whenGetAllProductsIsCalled_thenReturnEmptyList() {
//
//        when(fakeStoreClient.getAllProducts()).thenReturn(new ArrayList<>());
//
//        List<Product> expectedProducts= fakeStoreProductService.getAllProducts();
//
//        assert(expectedProducts.isEmpty());
//    }
//
//    @Test
//    void test_whenReplaceProductIsCalled_thenReturnTheReplacedProduct() throws ProductNotFoundException {
//
//        Long id= 1L;
//        Category category= new Category();
//        category.setName("Electronics");
//        Product productToReplaceWith = new Product();
//        productToReplaceWith.setCategory(category);
//        productToReplaceWith.setId(id);
//
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//
//        when(fakeStoreClient.replaceProduct(any(Long.class), any(FakeStoreProductDTO.class))).
//                thenReturn(fakeStoreProductDTO);
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(fakeStoreProductDTO);
//
//        Product replacedProduct = fakeStoreProductService.replaceProduct(id, productToReplaceWith);
//        assertNotNull(replacedProduct);
//        assertEquals(productToReplaceWith.getId(), replacedProduct.getId());
//
//    }
//
//
//    @Test
//    void test_whenReplaceProductIsCalled_thenThrowProductNotFoundException() {
//
//        Long id= 1L;
//        Category category= new Category();
//        category.setName("Electronics");
//        Product productToReplaceWith = new Product();
//        productToReplaceWith.setCategory(category);
//        productToReplaceWith.setId(id);
//
//        when(fakeStoreClient.replaceProduct(any(Long.class), any(FakeStoreProductDTO.class))).
//                thenReturn(null);
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(null);
//
//        assertThrows(ProductNotFoundException.class,
//                ()-> fakeStoreProductService.replaceProduct(2L, productToReplaceWith));
//    }
//
//    @Test
//    void test_whenUpdateProductIsCalled_thenReturnCorrectProduct() throws ProductNotFoundException {
//        Long id= 1L;
//        Category category= new Category();
//        category.setName("Electronics");
//        Product productToUpdateWith = new Product();
//        productToUpdateWith.setCategory(category);
//        productToUpdateWith.setId(id);
//
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//
//        when(fakeStoreClient.updateProduct(any(Long.class), any(FakeStoreProductDTO.class))).
//                thenReturn(fakeStoreProductDTO);
//
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(fakeStoreProductDTO);
//
//        Product updatedProduct = fakeStoreProductService.updateProduct(id, productToUpdateWith);
//        assertNotNull(updatedProduct);
//        assertEquals(productToUpdateWith.getId(), updatedProduct.getId());
//
//    }
//
//
//    @Test
//    void test_whenUpdateProductIsCalled_thenThrowProductNotFoundException() {
//        Long id= 1L;
//        Category category= new Category();
//        category.setName("Electronics");
//        Product productToUpdateWith = new Product();
//        productToUpdateWith.setCategory(category);
//        productToUpdateWith.setId(id);
//
//        when(fakeStoreClient.updateProduct(any(Long.class), any(FakeStoreProductDTO.class))).
//                thenReturn(null);
//        when(fakeStoreClient.getSingleProduct(any(Long.class))).thenReturn(null);
//
//        assertThrows(ProductNotFoundException.class,
//                ()-> fakeStoreProductService.updateProduct(2L, productToUpdateWith));
//    }
//
//    @Test
//    void test_whenDeleteProductIsCalled_thenReturnTheDeletedProduct() throws ProductNotFoundException {
//
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//
//        when(fakeStoreClient.deleteProduct(any(Long.class))).thenReturn(fakeStoreProductDTO);
//
//
//        Product deletedProduct = fakeStoreProductService.deleteProduct(1L);
//        assertNotNull(deletedProduct);
//        assertEquals(1L, deletedProduct.getId());
//
//    }
//
//    @Test
//    void test_whenDeleteProductIsCalled_thenThrowProductNotFoundException() {
//
//        when(fakeStoreClient.deleteProduct(any(Long.class))).thenReturn(null);
//        assertThrows(ProductNotFoundException.class,
//                ()-> fakeStoreProductService.deleteProduct(2L));
//    }
//
//
//    @Test
//    void test_whenAddNewProductIsCalled_thenReturnTheAddedProduct(){
//
//        Category category= new Category();
//        category.setName("Electronics");
//        Product productToAdd = new Product();
//
//        productToAdd.setCategory(category);
//
//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setId(1L);
//        fakeStoreProductDTO.setTitle("test");
//        fakeStoreProductDTO.setPrice(100.0);
//        fakeStoreProductDTO.setCategory("Electronics");
//        fakeStoreProductDTO.setImageUrl("some image");
//        fakeStoreProductDTO.setDescription("Best iPhone Ever");
//
//        when(fakeStoreClient.addNewProduct(any(FakeStoreProductDTO.class))).
//                thenReturn(fakeStoreProductDTO);
//
//        Product addedProduct = fakeStoreProductService.addNewProduct(productToAdd);
//        assertNotNull(addedProduct);
//        assertEquals(fakeStoreProductDTO.getId(), addedProduct.getId());
//
//    }
//
//
//
//}
