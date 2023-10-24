package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
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
    public boolean existsById(Long id);
//    public List<Products> findAllProductsbycategory(Categories category);
}
