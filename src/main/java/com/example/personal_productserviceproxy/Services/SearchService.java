package com.example.personal_productserviceproxy.Services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Repositories.SearchRepository;
import com.example.personal_productserviceproxy.utils.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class SearchService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Autowired
    private SearchRepository searchRepository;

    public Product insertProduct(Product product) {
        return searchRepository.save(product);
    }

    public Product updateProduct(Product product, long id) {
        Product product1  = searchRepository.findById(id).get();
        product1.setPrice(product.getPrice());
        return product1;
    }

    public void deleteProduct(long id ) {
        searchRepository.deleteById(id);
    }


    public List<Hit<Product>> matchAllProductsServices() throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        return listOfHits;
    }

    //matchProductWithName

    public List<Hit<Product>> matchProductsWithName(String fieldValue) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierWithNameField(fieldValue);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        return listOfHits;
    }
}
