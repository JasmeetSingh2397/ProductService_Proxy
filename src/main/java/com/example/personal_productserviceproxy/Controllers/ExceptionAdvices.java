package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RestClientException.class})
    public ResponseEntity<String> handleRestClientException (RestClientException e) {

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
//    public ResponseEntity<String> handleInternalServerErrorException(HttpServerErrorException.InternalServerError e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//    }
//




}
