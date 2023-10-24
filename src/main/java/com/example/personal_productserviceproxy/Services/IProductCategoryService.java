package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;

import java.util.List;

public interface IProductCategoryService {
    public List<Categories> getAllProductCategories();
    public List<Products> getProductsInASingleCategory(String categoryName);


}
