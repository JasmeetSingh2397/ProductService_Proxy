package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.CommonlyUsedMethods;
import com.example.personal_productserviceproxy.DTOs.CategoryDTO;
import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Services.IProductCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IProductCategoryService productCategoryService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void test_whengetAllCategoriesCalled_thenReturnCategoryDtoList() throws Exception {
        List<Categories> categoriesList= new ArrayList<>();
        Categories category= new Categories();
        category.setName("Electronics");
        categoriesList.add(category);

        when(productCategoryService.getAllProductCategories()).thenReturn(categoriesList);
        List<CategoryDTO> categoryDTOList= new ArrayList<>();
        categoryDTOList.add(CommonlyUsedMethods.convertCategoryToCategoryDto(category));


        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryDTOList)));
    }


    @Test
    void test_whengetAllCategoriesCalled_thenThrowException() throws Exception {

        when(productCategoryService.getAllProductCategories()).
                thenThrow(new NullPointerException("No Categories Found"));
        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Categories Found"));
    }

    @Test
    void test_whengetProductsInASingleCategoryCalled_thenReturnProductDtoList() throws Exception {
        Categories categoryToSearch= new Categories();
        categoryToSearch.setName("Electronics");

        List<Products> productsList= new ArrayList<>();
        Products product= new Products();
        product.setCategory(categoryToSearch);
        productsList.add(product);

        when(productCategoryService.getProductsInASingleCategory(any(String.class))).thenReturn(productsList);

        List<ProductDto> expectedproductDtoList= new ArrayList<>();
        ProductDto productDtoExpected= CommonlyUsedMethods.convertProductToProductDto(product);
        expectedproductDtoList.add(productDtoExpected);

        mockMvc.perform(
                        get("/products/category/{name}", categoryToSearch.getName()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedproductDtoList)));
    }

    @Test
    void test_whengetProductsInASingleCategoryCalled_thenThrowException() throws Exception {
        Categories categoryToSearch= new Categories();
        categoryToSearch.setName("Electronics");

        when(productCategoryService.getProductsInASingleCategory(any(String.class))).
                thenThrow(new NullPointerException("Category Not Found"));
        mockMvc.perform(
                get("/products/category/{name}",categoryToSearch.getName()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Category Not Found"));
    }
}
