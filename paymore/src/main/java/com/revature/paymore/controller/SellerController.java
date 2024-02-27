package com.revature.paymore.controller;
import com.revature.paymore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SellerController {


    @Autowired
    private SellerService sellerService;

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);


    @PostMapping("/registerSeller")
    public ResponseEntity<?> registerSeller(@RequestBody SellerDTO sellerDto){
        SellerDTO response = sellerService.registerSeller(sellerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }





}
