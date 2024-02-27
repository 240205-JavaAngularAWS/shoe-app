package com.revature.paymore.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {




    @PostMapping("/order")
    public ResponseEntity<?> registerAddress(@RequestBody AddressDTO addressDto){
        AddressDTO response = addressService.registerAddress(addressDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



}
