package com.revature.paymore.service;

import com.revature.paymore.model.*;
import com.revature.paymore.model.DTO.*;
import com.revature.paymore.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }




    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);



    // DTO Methods
    public SellerDTO convertToSimpleDTO(Seller seller){
        // new sellerDTO
        return new SellerDTO(
                seller.getCompanyName(),
                seller.getEmail(),
                seller.getUsername());

    }

    public SellerDTO convertToExtendedDTO(Seller seller){
        // new sellerDTO

        // Add logic to pull products.
        Set<ProductDTO> products = new HashSet<>();
        return new SellerDTO(
                seller.getId(),
                seller.getCompanyName(),
                seller.getEmail(),
                seller.getUsername(),
                seller.getPassword(),
                products
        );

    }

    public Seller convertToEntity(SellerDTO sellerDto) {
        // requires Extended DTO

        // Add logic to pull products.
        Set<Product> products = new HashSet<>();
        Seller seller = new Seller();
        seller.setId(sellerDto.getId());
        seller.setCompanyName(sellerDto.getCompanyName());
        seller.setEmail(sellerDto.getEmail());
        seller.setUsername(sellerDto.getUsername());
        seller.setPassword(sellerDto.getPassword());
        seller.setProducts(products);
        return seller;
    }






    // register as a seller
    public Seller registerSeller(Seller seller)
    {

    }


    // Log into the application
    public long authenticateSeller()
    {

    }





}

