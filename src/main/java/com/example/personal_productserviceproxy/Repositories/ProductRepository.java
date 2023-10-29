package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    public Optional<Products> findById(Long id);
    public Products save(Products product);
    public void deleteById(Long id);
    public List<Products> findAll();
    boolean existsById(Long id);

    List<Products> findByPriceBetween(double greaterthan, double lessthan);
    Optional<Products> findByProductsName(String productName);
    List<Products> findByIdIsNotNullOOrderByPrice();
    List<Products> findAllByisPublicFalse();
    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
    public List<Products> findAllProductsByCategory(@Param("categoryId") Long categoryId);
}
