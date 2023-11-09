package com.example.personal_productserviceproxy.DTOs;

import com.example.personal_productserviceproxy.Models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private String name;
    private String description;

    public static CategoryDTO mapCategoryToCategoryDto(Category category){

        CategoryDTO categoryDTO= new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }


}
