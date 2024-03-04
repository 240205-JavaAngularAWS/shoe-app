package com.revature.paymore.exception;



import org.springframework.validation.Errors;

public class InvalidReviewException extends RuntimeException {

    private Errors errors;
    public InvalidReviewException(String message, Errors errors) {

        super(message);
        this.errors = errors;
    }

    public InvalidReviewException(String message) {

        super(message);
    }
}
