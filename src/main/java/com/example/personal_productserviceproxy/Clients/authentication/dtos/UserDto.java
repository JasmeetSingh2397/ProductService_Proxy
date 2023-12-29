package com.example.personal_productserviceproxy.Clients.authentication.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserDto {

    private Set<Role> roles=new HashSet<>();
    private String email;

}
