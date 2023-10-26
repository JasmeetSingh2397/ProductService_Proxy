package com.example.personal_productserviceproxy.Models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Products> productsList;
}
