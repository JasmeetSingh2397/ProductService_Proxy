package com.example.personal_productserviceproxy.Clients.FakeStore.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FakeStoreCategoryDTO {
    private String name;
}
