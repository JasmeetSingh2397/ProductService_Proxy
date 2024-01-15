package com.example.personal_productserviceproxy.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductRequestDto {
    private String query;
    private int sizeOfPage;
    private int offset;
    private List<SortParamsDto> sortParamsDtos;

    
}
