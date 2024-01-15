package com.example.personal_productserviceproxy.ElasticSearchRepositories;

import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository("productElasticSearchRepository")
public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, Long> {

    Page<Product> findAllContaining(String query, Pageable pageable);

}
