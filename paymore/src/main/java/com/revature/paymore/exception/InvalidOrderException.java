package com.revature.paymore.exception;

import org.springframework.validation.Errors;

public class InvalidOrderException extends RuntimeException {

    private Errors errors;
    public InvalidOrderException(String message, Errors errors) {

        super(message);
        this.errors = errors;
    }

    public InvalidOrderException(String message) {

        super(message);
    }
}



