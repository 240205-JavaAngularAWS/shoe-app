package com.revature.paymore.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAccessDeniedException(AccessDeniedException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidOrderException(InvalidOrderException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidSellerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidSellerException(InvalidSellerException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidProductException(InvalidProductException ex){
        return ex.getMessage();
    }

}
