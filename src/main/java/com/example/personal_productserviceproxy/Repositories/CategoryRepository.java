package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
