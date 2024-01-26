package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface SearchRepository extends ElasticsearchRepository<Product,Long> {

    Page<Product> searchByTitle(String query, String[] fields, Pageable pageable);


}
