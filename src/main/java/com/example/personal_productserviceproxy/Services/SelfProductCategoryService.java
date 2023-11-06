package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Exceptions.CategoryNotFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoCategoriesFoundException;
import com.example.personal_productserviceproxy.Exceptions.NoProductsInCategoryException;
import com.example.personal_productserviceproxy.Models.Category;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductCategoryService implements IProductCategoryService{

    private CategoryRepository categoryRepository;

    public SelfProductCategoryService( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllProductCategories() throws NoCategoriesFoundException {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()){
            throw new NoCategoriesFoundException("No Categories Found");
        }

        return categoryList;
    }


    @Override
    public List<Product> getProductsInASingleCategory(String categoryName) throws CategoryNotFoundException, NoProductsInCategoryException {
        Optional<Category> Optionalcategory= categoryRepository.findByName(categoryName);
        if (Optionalcategory.isEmpty()){
            throw new CategoryNotFoundException("Category Not Found");
        }
        Category category= Optionalcategory.get();

        List<Product> productsInCategory=  categoryRepository.findAllProductsByCategory(category.getId());
        if(productsInCategory.isEmpty()){
            throw new NoProductsInCategoryException("No Products Found in the mentioned Category");
        }
        return productsInCategory;


    }

    public Optional<Category> findByName(String categoryName){

        return categoryRepository.findByName(categoryName);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }
}
