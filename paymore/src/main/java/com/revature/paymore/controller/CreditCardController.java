package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.CreditCardDTO;
import com.revature.paymore.service.CreditCardService;
import com.revature.paymore.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CreditCardController {

    private final CreditCardService creditCardService;
    private final ResponseHelperService responseHelperService;


    @Autowired
    public CreditCardController(CreditCardService creditCardService, ResponseHelperService responseHelperService) {
        this.creditCardService = creditCardService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/creditcards")
    public ResponseEntity<?> addCreditCard(@Valid @RequestBody CreditCardDTO creditCardDto, BindingResult bindingResult){
        // Using DTO so we don't have to use the entire User object.
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        // add product to seller
        CreditCardDTO response = creditCardService.addCreditCard(creditCardDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // find creditcards by userId
    @GetMapping("/creditcards/user/{userId}")
    public ResponseEntity<List<CreditCardDTO>> findCreditCardsByUserId(@PathVariable Long userId){

        List<CreditCardDTO> response = creditCardService.findCreditCardsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/creditcards/{creditCardId}")
    public ResponseEntity<String> deleteCreditCardById(@PathVariable Long creditCardId){

        boolean deleted = creditCardService.deleteCreditCard(creditCardId);
        if(!deleted){
            throw new BadRequestException("Product with id " + creditCardId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Product Successfully Deleted\"}", HttpStatus.OK);
    }







}
