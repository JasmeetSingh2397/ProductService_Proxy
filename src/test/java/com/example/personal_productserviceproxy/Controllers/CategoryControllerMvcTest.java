//package com.example.personal_productserviceproxy.Controllers;
//
//import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
//import com.example.personal_productserviceproxy.DTOs.ProductDto;
//import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
//import com.example.personal_productserviceproxy.Models.Category;
//import com.example.personal_productserviceproxy.Models.Product;
//import com.example.personal_productserviceproxy.Services.IProductCategoryService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CategoryController.class)
//public class CategoryControllerMvcTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    IProductCategoryService productCategoryService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @Test
//    void test_whenGetAllCategoriesIsCalled_thenReturnCorrectCategoryDtoList() throws Exception {
//        List<Category> categoryList = new ArrayList<>();
//        Category category= new Category();
//        category.setName("Electronics");
//        categoryList.add(category);
//
//        when(productCategoryService.getAllProductCategories()).thenReturn(categoryList);
//        List<CategoryDTO> categoryDTOList= new ArrayList<>();
//        categoryDTOList.add(CategoryDTO.mapCategoryToCategoryDto(category));
//
//
//        mockMvc.perform(get("/products/categories"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(categoryDTOList)));
//    }
//
//
//    @Test
//    void test_whenGetAllCategoriesIsCalled_thenReturnNoContentStatus() throws Exception {
//
//        when(productCategoryService.getAllProductCategories()).
//                thenReturn(new ArrayList<>());
//        mockMvc.perform(get("/products/categories"))
//                .andExpect(status().isNoContent());
//
//    }
//
//    @Test
//    void test_whenGetProductsInASingleCategoryIsCalled_thenReturnCorrectProductDtoList() throws Exception {
//        Category categoryToSearch= new Category();
//        categoryToSearch.setName("Electronics");
//
//        List<Product> productList = new ArrayList<>();
//        Product product= new Product();
//        product.setCategory(categoryToSearch);
//        productList.add(product);
//
//        when(productCategoryService.getProductsInASingleCategory(any(String.class))).thenReturn(productList);
//
//        List<ProductDto> expectedproductDtoList= new ArrayList<>();
//        ProductDto productDtoExpected= ProductDto.mapProductToProductDto(product);
//        expectedproductDtoList.add(productDtoExpected);
//
//        mockMvc.perform(
//                        get("/products/category/{name}", categoryToSearch.getName()))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(expectedproductDtoList)));
//    }
//
//    @Test
//    void test_whenGetProductsInASingleCategoryIsCalled_thenReturnStatusNotFound() throws Exception {
//        Category categoryToSearch= new Category();
//        categoryToSearch.setName("Electronics");
//
//        when(productCategoryService.getProductsInASingleCategory(any(String.class))).
//                thenThrow(new CategoryNotFoundException("Category Not Found"));
//        mockMvc.perform(
//                get("/products/category/{name}",categoryToSearch.getName()))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Category Not Found"));
//    }
//
//    @Test
//    void test_whenGetProductsInASingleCategoryIsCalled_thenReturnNoContentStatus() throws Exception {
//        Category categoryToSearch= new Category();
//        categoryToSearch.setName("Electronics");
//
//        when(productCategoryService.getProductsInASingleCategory(any(String.class))).
//                thenReturn(new ArrayList<>());
//        mockMvc.perform(
//                get("/products/category/{name}",categoryToSearch.getName()))
//                .andExpect(status().isNoContent());
//
//    }
//}
