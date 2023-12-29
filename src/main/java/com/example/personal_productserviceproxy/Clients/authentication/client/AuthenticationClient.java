package com.example.personal_productserviceproxy.Clients.authentication.client;


import com.example.personal_productserviceproxy.Clients.authentication.dtos.ValidateTokenRequestDto;
import com.example.personal_productserviceproxy.Clients.authentication.dtos.ValidateTokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public ValidateTokenResponseDto validate(String token, long userId){
        RestTemplate restTemplate= restTemplateBuilder.build();
        ValidateTokenRequestDto validateTokenRequestDto= new ValidateTokenRequestDto();
        validateTokenRequestDto.setToken(token);
        validateTokenRequestDto.setUserId(userId);




        ResponseEntity<ValidateTokenResponseDto> validateTokenResponseDto= restTemplate.
                postForEntity("http://localhost:9000/auth/validate",
                        validateTokenRequestDto, ValidateTokenResponseDto.class);
        return validateTokenResponseDto.getBody();

    }
}
