package com.revature.paymore.exception;



import org.springframework.validation.Errors;

public class StockException extends RuntimeException {

    private Errors errors;
    public StockException(String message, Errors errors) {

        super(message);
        this.errors = errors;
    }

    public StockException(String message) {

        super(message);
    }
}
