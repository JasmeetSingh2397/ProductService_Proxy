package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpaProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findById(Long id);
    public Product save(Product product);
    public void deleteById(Long id);
    public List<Product> findAll();
    boolean existsById(Long id);

//    List<Products> findByPriceBetween(double greaterthan, double lessthan);
//    Optional<Products> findByProductsName(String productName);
//    List<Products> findByIdIsNotNullOrderByPrice();
//    List<Products> findAllByisPublicFalse();

}
