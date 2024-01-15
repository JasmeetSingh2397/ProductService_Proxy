package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("jpaCategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findById(Long id);

    public Optional<Category> findByName(String name);
    public Category save(Category category);

    public void deleteById(Long id);
    public List<Category> findAll();

    public boolean existsByName(String name);
    public void deleteByName(String name);

    @Query(value = "SELECT c.name FROM Category c WHERE c.id = :id")
    String findCategoryNameById(Long id);

    @Query(value = "SELECT c.name FROM Category c WHERE c.id = ?1")
    String findCategoryNameByIdV1(long id);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findAllProductsByCategory(@Param("categoryId") Long categoryId);
}

