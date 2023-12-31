package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;

import java.util.List;

public interface IProductCategoryService {
    public List<Category> getAllProductCategories();
    public List<Product> getProductsInASingleCategory(String categoryName) throws CategoryNotFoundException;


}
