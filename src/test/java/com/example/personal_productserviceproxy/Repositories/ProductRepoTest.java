package com.example.personal_productserviceproxy.Repositories;

import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import org.junit.jupiter.api.Test;
import com.example.personal_productserviceproxy.Models.Categories;
import com.example.personal_productserviceproxy.Models.Products;
import com.example.personal_productserviceproxy.Repositories.CategoryRepository;
import com.example.personal_productserviceproxy.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @Test
    @Transactional
    void saveProductsAndCategory() {



//        Products product1= productRepo.findById(3L).get();
//        Categories categories= product1.getCategory();
//        Categories categories1 = categoryRepo.findById(2002L).get();
//        List<Products> productList = categories1.getProductsList();
//        System.out.println("Debug");

        Categories category = categoryRepo.findById(2002L).get();
        List<Products> productList = category.getProductsList();
        for (Products product : productList) {
            System.out.println(product.getPrice());
        }

    }
}