package com.revature.paymore.exception;


import org.springframework.validation.Errors;

public class InvalidSellerException extends RuntimeException {

    private Errors errors;
    public InvalidSellerException(String message, Errors errors) {

        super(message);
        this.errors = errors;
    }

    public InvalidSellerException(String message) {

        super(message);
    }
}
