package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Exceptions.NoProductsFoundException;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProducts() throws NoProductsFoundException;
    public Product getSingleProduct(Long ProductId) throws ProductNotFoundException;
    public Product updateProduct(Long ProductId, Product product) throws ProductNotFoundException;
    public Product addNewProduct(Product product);
    public Product deleteProduct(Long ProductId) throws ProductNotFoundException;

    public Product replaceProduct(Long ProductId, Product product) throws ProductNotFoundException;



}
