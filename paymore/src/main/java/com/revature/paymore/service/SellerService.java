package com.revature.paymore.service;


import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.Seller;
import com.revature.paymore.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    private static final Logger logger = LoggerFactory.getLogger(service.SellerService.class);

    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);


    // register as a seller
    public Seller registerSeller(SellerDTO sellerDTO) {
        if (sellerRepository.findByUsername(sellerDTO.getUsername().isPresent)) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        return sellerRepository.save(sellerDTO);
    }


    // Log into the application
    public long authenticateSeller(SellerLoginDTO sellerLoginDTO) {
        Seller seller = (sellerRepository.findByUsernameAndPassword(sellerLoginDTO.getUsername(), user.getPassword()))
                .orElseThrow(() -> new AccessDeniedException("Invalid username or password"));
                return seller.getId();

    }



}

