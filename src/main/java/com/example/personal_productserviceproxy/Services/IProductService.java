package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Models.Products;

import java.util.List;

public interface IProductService {
    public List<Products> getAllProducts();
    public Products getSingleProduct(Long ProductId);
    public Products updateProduct(Long ProductId,Products product);
    public Products addNewProduct(Products product);
    public Products deleteProduct(Long ProductId);

    public Products replaceProduct(Long ProductId, Products product);



}
