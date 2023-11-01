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
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    public Optional<Categories> findById(Long id);

    public Optional<Categories> findByName(String name);
    public Categories save(Categories category);

    public void deleteById(Long id);
    public List<Categories> findAll();

    public boolean existsByName(String name);
    public void deleteByName(String name);

    @Query(value = "SELECT c.name FROM Categories c WHERE c.id = :id")
    String findCategoryNameById(Long id);

    @Query(value = "SELECT c.name FROM Categories c WHERE c.id = ?1")
    String findCategoryNameByIdV1(long id);

    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
    public List<Products> findAllProductsByCategory(@Param("categoryId") Long categoryId);
}

