package com.revature.paymore.service;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba

import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.Seller;
<<<<<<< HEAD
=======
import com.revature.paymore.model.*;
import com.revature.paymore.model.DTO.*;
>>>>>>> b94d23698f8065784286810bef107517cadac388
=======

import com.revature.paymore.model.*;
import com.revature.paymore.model.DTO.*;

>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba
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


    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


<<<<<<< HEAD
<<<<<<< HEAD
    private static final Logger logger = LoggerFactory.getLogger(service.SellerService.class);
=======

>>>>>>> b94d23698f8065784286810bef107517cadac388
=======
>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba

    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);


<<<<<<< HEAD
<<<<<<< HEAD
=======

=======
>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba
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






<<<<<<< HEAD
>>>>>>> b94d23698f8065784286810bef107517cadac388
=======
>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba
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
<<<<<<< HEAD
<<<<<<< HEAD
=======


>>>>>>> b94d23698f8065784286810bef107517cadac388
=======
>>>>>>> 328fcc0cba1cf17ecbee7370c2fee724bb1821ba



}

