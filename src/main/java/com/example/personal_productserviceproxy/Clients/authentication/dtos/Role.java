package com.example.personal_productserviceproxy.Clients.authentication.dtos;

import com.example.personal_productserviceproxy.Models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Role extends BaseModel {
    private String name;

//    private List<User> users
}
