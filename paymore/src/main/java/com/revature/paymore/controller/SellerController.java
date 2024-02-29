package com.revature.paymore.controller;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.SellerDTO;
import com.revature.paymore.model.Seller;
import com.revature.paymore.service.SellerService;
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

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    private final SellerService sellerService;




    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @PostMapping("/registerSeller")
    public ResponseEntity<SellerDTO> registerSeller(@RequestBody Seller seller){
        SellerDTO response = sellerService.registerSeller(seller);
        logger.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/loginSeller")
    public ResponseEntity<?> loginSeller(@RequestBody LoginDTO loginDTO) {

        long userId = sellerService.authenticateSeller(loginDTO);

        return ResponseEntity.ok().body(userId);

    }



}
