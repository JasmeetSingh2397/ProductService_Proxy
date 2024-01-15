//package com.example.personal_productserviceproxy.Controllers;
//
//import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
//
//import com.example.personal_productserviceproxy.Models.Category;
//
//import com.example.personal_productserviceproxy.Services.IProductCategoryService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class CategoryControllerTest {
//
//    @Autowired
//    private CategoryController categoryController;;
//    @MockBean
//    private IProductCategoryService productCategoryService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    void test_whengetAllCategoriesCalled_thenReturnCategoryDtoList() throws Exception {
//        List<Category> categoryList = new ArrayList<>();
//        Category category= new Category();
//        category.setName("Electronics");
//        category.setId(1L);
//        categoryList.add(category);
//
//        when(productCategoryService.getAllProductCategories()).thenReturn(categoryList);
//        List<CategoryDTO> categoryDTOList= categoryController.getAllProductCategories().getBody();
//        assertNotNull(categoryDTOList);
//
//        assertEquals(category.getName(),categoryDTOList.get(0).getName());
//
//    }
////
////
//////    @Test
//////    void test_whengetAllCategoriesCalled_thenThrowException() throws Exception {
//////
//////        when(productCategoryService.getAllProductCategories()).
//////                thenThrow(new NullPointerException("No Categories Found"));
//////        mockMvc.perform(get("/products/categories"))
//////                .andExpect(status().isNotFound())
//////                .andExpect(content().string("No Categories Found"));
//////    }
//////
//////    @Test
//////    void test_whengetProductsInASingleCategoryCalled_thenReturnProductDtoList() throws Exception {
//////        Categories categoryToSearch= new Categories();
//////        categoryToSearch.setName("Electronics");
//////
//////        List<Products> productsList= new ArrayList<>();
//////        Products product= new Products();
//////        product.setCategory(categoryToSearch);
//////        productsList.add(product);
//////
//////        when(productCategoryService.getProductsInASingleCategory(any(String.class))).thenReturn(productsList);
//////
//////        List<ProductDto> expectedproductDtoList= new ArrayList<>();
//////        ProductDto productDtoExpected= commonlyUsedMethods.convertToProductDto(product);
//////        expectedproductDtoList.add(productDtoExpected);
//////
//////        mockMvc.perform(
//////                        get("/products/category/{name}", categoryToSearch.getName()))
//////                .andExpect(status().isOk())
//////                .andExpect(content().string(objectMapper.writeValueAsString(expectedproductDtoList)));
//////    }
//////
//////    @Test
//////    void test_whengetProductsInASingleCategoryCalled_thenThrowException() throws Exception {
//////        Categories categoryToSearch= new Categories();
//////        categoryToSearch.setName("Electronics");
//////
//////        when(productCategoryService.getProductsInASingleCategory(any(String.class))).
//////                thenThrow(new NullPointerException("Category Not Found"));
//////        mockMvc.perform(
//////                        get("/products/category/{name}",categoryToSearch.getName()))
//////                .andExpect(status().isNotFound())
//////                .andExpect(content().string("Category Not Found"));
//////    }
//}
