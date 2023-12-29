package com.example.personal_productserviceproxy.Clients.authentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {

    private String token;
    private long userId;

}
