package com.revature.paymore.controller;


import com.revature.paymore.model.Address;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.service.AddressService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AddressController {



    private final AddressService addressService;

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);


    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping("/registerAddress")
    public ResponseEntity<AddressDTO> registerAddress(@Valid @RequestBody Address address){
        AddressDTO response = addressService.registerAddress(address);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }














}
