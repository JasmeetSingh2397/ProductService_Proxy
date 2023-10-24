package com.example.personal_productserviceproxy.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products extends BaseModel {
    private String title;
    private double price;
    private String description;

    @ManyToOne(cascade= CascadeType.PERSIST)
    private Categories category;
    private String imageUrl;


}
