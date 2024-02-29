package com.revature.paymore.service;



import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.*;
import com.revature.paymore.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerService {


    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }



    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);



    // DTO Methods


    // register as a seller)
    public SellerDTO registerSeller(Seller seller) {
        if (sellerRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        sellerRepository.save(seller);
        return new SellerDTO(seller);
    }

    // Log into the application
    public long authenticateSeller(LoginDTO loginDTO) {
        Seller seller = (sellerRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()))
                .orElseThrow(() -> new AccessDeniedException("Invalid username or password"));
                return seller.getId();
    }


}

