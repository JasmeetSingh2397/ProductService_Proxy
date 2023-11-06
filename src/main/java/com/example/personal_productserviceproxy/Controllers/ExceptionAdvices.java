package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {

        return new ResponseEntity<>("Oops! Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoCategoriesFoundException.class})
    public ResponseEntity<String> handleEmptyCategoryListException(NoCategoriesFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler({NoProductsFoundException.class})
    public ResponseEntity<String> handleEmptyProductListException(NoProductsFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler({NoProductsInCategoryException.class})
    public ResponseEntity<String> handleNoProductsInCategoryException(NoProductsInCategoryException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }



}
