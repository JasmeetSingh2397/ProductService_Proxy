package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoCategoriesFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoProductsInCategoryException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductCategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private SelfProductCategoryService selfProductCategoryService;

    @Test
    public void test_whenGetAllProductCategoriesIsCalled_ThenReturnTheCorrectCategoryList() throws NoCategoriesFoundException {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        categoryList.add(category);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> actualCategoryList = selfProductCategoryService.getAllProductCategories();

        assert(actualCategoryList.size() == 1);
        assert(actualCategoryList.get(0).getName().equals("Electronics"));
        assertEquals(actualCategoryList.get(0).getId(), 1L);



    }

    @Test
    public void test_whenGetAllProductCategoriesIsCalled_ThenReturnNoCategoriesFoundException() throws NoCategoriesFoundException {

        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NoCategoriesFoundException.class, () -> selfProductCategoryService.getAllProductCategories());
    }

    @Test
    public void test_whenGetProductsInSingleCategoryIsCalled_ThenReturnTheCorrectProductList() throws NoProductsInCategoryException, CategoryNotFoundException {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setTitle("iPhone 15 Pro Max");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);
        productList.add(product);
        when(categoryRepository.findByName("Electronics")).thenReturn(java.util.Optional.of(category));
        when(categoryRepository.findAllProductsByCategory(category.getId())).thenReturn(productList);


        List<Product> returnedProductList = selfProductCategoryService.getProductsInASingleCategory("Electronics");

        assert(returnedProductList.size() == 1);
        assert(returnedProductList.get(0).getCategory().getName().equals("Electronics"));
        assertEquals(returnedProductList.get(0).getId(), 1L);



    }


    @Test
    public void test_whenGetProductsInSingleCategoryIsCalled_ThenThrowNoProductsInCategoryException() {

        Category category = new Category();
        category.setName("Electronics");
        when(categoryRepository.findByName("Electronics")).thenReturn(java.util.Optional.of(category));
        when(categoryRepository.findAllProductsByCategory(category.getId())).thenReturn(new ArrayList<>());

        assertThrows(NoProductsInCategoryException.class, () ->
                selfProductCategoryService.getProductsInASingleCategory("Electronics"));





    }

    @Test
    public void test_whenGetProductsInSingleCategoryIsCalled_ThenThrowCategoryNotFoundException() {

        when(categoryRepository.findByName("Electronics")).thenReturn(java.util.Optional.empty());
//        when(categoryRepository.findAllProductsByCategory(category.getId())).thenReturn(new ArrayList<>());

        assertThrows(CategoryNotFoundException.class, () ->
                selfProductCategoryService.getProductsInASingleCategory("Electronics"));





    }


}
