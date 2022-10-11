package com.example.product.exception;

public class RequestParamNotValidException extends RuntimeException{

    private final String message;


    public RequestParamNotValidException(String message) {
        this.message = message;
    }

}
