package com.example.personal_productserviceproxy.Clients.FakeStore.DTO;

import com.example.personal_productserviceproxy.Clients.IClientProductDTO;
import com.example.personal_productserviceproxy.DTOs.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FakeStoreProductDTO implements IClientProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
    private RatingDto rating;


}
