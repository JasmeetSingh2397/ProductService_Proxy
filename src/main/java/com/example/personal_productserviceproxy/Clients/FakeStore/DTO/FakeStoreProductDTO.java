package com.example.personal_productserviceproxy.Clients.FakeStore.DTO;

import com.example.personal_productserviceproxy.DTOs.RatingDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FakeStoreProductDTO implements Serializable {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private RatingDto rating;


}
