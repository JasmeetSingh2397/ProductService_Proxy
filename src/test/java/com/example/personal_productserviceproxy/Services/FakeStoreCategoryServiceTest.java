//package com.example.personal_productserviceproxy.Services;
//
//import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
//import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
//import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
////@SpringBootTest
////public class FakeStoreCategoryServiceTest {
////
////    @MockBean
////    private FakeStoreClient fakeStoreClient;
////
////    @Autowired
////    private FakeStoreCategoryService fakeStoreCategoryService;
////
////    @Test
////    public void test_whenGetAllProductCategoriesIsCalled_ThenReturnTheCorrectCategoryList() {
////        List<String> fakeStoreCategoryDTOlist = new ArrayList<>();
////
////        fakeStoreCategoryDTOlist.add("Electronics");
////        when(fakeStoreClient.getAllProductCategories()).thenReturn(fakeStoreCategoryDTOlist);
////        List<Category> returnedCategoryList = fakeStoreCategoryService.getAllProductCategories();
////
////        assert(returnedCategoryList.size() == 1);
////        assert(returnedCategoryList.get(0).getName().equals("Electronics"));
////
////
////    }
////
////    @Test
////    public void test_whenGetAllProductCategoriesIsCalled_ThenReturnEmptyList() {
////
////        when(fakeStoreClient.getAllProductCategories()).thenReturn(new ArrayList<>());
////
////        List<Category> returnedCategoryList = fakeStoreCategoryService.getAllProductCategories();
////
////        assert(returnedCategoryList.isEmpty());
////
////    }
////
////    @Test
////    public void test_whenGetProductsInSingleCategoryIsCalled_ThenReturnTheCorrectProductList() throws CategoryNotFoundException {
////
////        List<FakeStoreProductDTO> fakeStoreProductDTOList = new ArrayList<>();
////        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
////        fakeStoreProductDTO.setId(1L);
////        fakeStoreProductDTO.setTitle("iPhone 15 Pro Max");
////        fakeStoreProductDTO.setPrice(1000.0);
////        fakeStoreProductDTO.setCategory("Electronics");
////        fakeStoreProductDTO.setImageUrl("some image");
////        fakeStoreProductDTO.setDescription("Best iPhone Ever");
////        fakeStoreProductDTOList.add(fakeStoreProductDTO);
////        when(fakeStoreClient.getProductsInASingleCategory("Electronics")).thenReturn(fakeStoreProductDTOList);
////        List<Product> returnedProductList = fakeStoreCategoryService.getProductsInASingleCategory("Electronics");
////
////        assert(returnedProductList.size() == 1);
////        assert(returnedProductList.get(0).getId().equals(1L));
////
////
////
////    }
////
////
////    @Test
////    public void test_whenGetProductsInSingleCategoryIsCalled_ThenReturnEmptyProductList() throws CategoryNotFoundException {
////
////        when(fakeStoreClient.getProductsInASingleCategory(anyString())).thenReturn(new ArrayList<>());
////
////        List<Product> returnedProductList = fakeStoreCategoryService.
////                getProductsInASingleCategory("Electronics");
////
////        assert(returnedProductList.isEmpty());
////
////
////
////    }
////
//////    @Test
//////    public void test_whenGetProductsInSingleCategoryIsCalled_ThenThrowCategoryNotFoundException() {
//////
//////        when(fakeStoreClient.getProductsInASingleCategory("Electronics")).thenReturn(null);
//////
//////        assertThrows(CategoryNotFoundException.class, () ->
//////                fakeStoreCategoryService.getProductsInASingleCategory("Electronics"));
//////
//////
//////    }
////
////
////}
