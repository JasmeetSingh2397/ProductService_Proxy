package com.example.personal_productserviceproxy.DTOs;

import com.example.personal_productserviceproxy.Models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {

    private String name;
    private String description;

    public static CategoryDTO mapCategoryToCategoryDto(Category category){

        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }


}
