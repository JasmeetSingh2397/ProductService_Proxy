package com.example.personal_productserviceproxy.Clients.authentication.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.support.SessionStatus;

@Getter
@Setter
public class ValidateTokenResponseDto {

    private UserDto userDto;
    private SessionStatus sessionStatus;

}
