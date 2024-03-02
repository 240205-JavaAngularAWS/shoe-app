package com.revature.paymore.controller;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.SellerDTO;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.UserDTO;
import com.revature.paymore.service.ResponseHelperService;
import com.revature.paymore.service.SellerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SellerController {

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    private final SellerService sellerService;
    private final ResponseHelperService responseHelperService;




    @Autowired
    public SellerController(SellerService sellerService, ResponseHelperService responseHelperService) {
        this.sellerService = sellerService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/registerSeller")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody Seller seller, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        SellerDTO response = sellerService.registerSeller(seller);
        logger.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/sellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<SellerDTO> response = sellerService.getAllSellers();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/loginSeller")
    public ResponseEntity<?> loginSeller(@Valid @RequestBody LoginDTO loginDTO) {

        long userId = sellerService.authenticateSeller(loginDTO);

        return ResponseEntity.ok().body(userId);

    }



}
