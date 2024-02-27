package com.revature.paymore.service;

import com.revature.paymore.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }


    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);



    // register as a seller
    public Seller registerSeller(Seller seller)
    {

    }


    // Log into the application
    public long authenticateSeller()
    {

    }




}

