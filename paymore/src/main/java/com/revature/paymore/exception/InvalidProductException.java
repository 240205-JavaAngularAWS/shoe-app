package com.revature.paymore.exception;



import org.springframework.validation.Errors;

public class InvalidProductException extends RuntimeException {

    private Errors errors;
    public InvalidProductException(String message, Errors errors) {

        super(message);
        this.errors = errors;
    }

    public InvalidProductException(String message) {

        super(message);
    }
}
