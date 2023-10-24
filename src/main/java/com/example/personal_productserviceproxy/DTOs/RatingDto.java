package com.example.personal_productserviceproxy.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RatingDto {
    private double rate;
    private double count;
}
