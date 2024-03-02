package com.revature.paymore.controller;


import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.service.AddressService;
import com.revature.paymore.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {



    private final AddressService addressService;
    private final ResponseHelperService responseHelperService;

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);


    @Autowired
    public AddressController(AddressService addressService, ResponseHelperService responseHelperService) {
        this.addressService = addressService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/registerAddress")
    public ResponseEntity<?> registerAddress(@Valid @RequestBody Address address, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        AddressDTO response = addressService.registerAddress(address);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long addressId){

        boolean deleted = addressService.deleteAddress(addressId);
        if(!deleted){
            throw new BadRequestException("Product with id " + addressId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Product Successfully Deleted\"}", HttpStatus.OK);
    }















}
