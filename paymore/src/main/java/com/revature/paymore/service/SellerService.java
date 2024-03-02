package com.revature.paymore.service;
import com.revature.paymore.exception.*;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.*;
import com.revature.paymore.model.enums.AddressType;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.SellerRepository;
import com.revature.paymore.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SellerService {


    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;




    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    private ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public SellerService(SellerRepository sellerRepository, UserRepository userRepository, AddressRepository addressRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;

    }



    // register as a seller
    public SellerDTO registerSeller(Seller seller) {



        // Ensure Username isn't taken by other sellers.
        if (sellerRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        // Ensure Username isn't taken by other users.
        if (userRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        Address sellerAddress = seller.getAddress();
        sellerAddress.setAddressType(AddressType.SELLER);
        // save address to repo
        addressRepository.save(sellerAddress);

        if(!seller.getProducts().isEmpty()){
            for(Product product: seller.getProducts()){

                // save product to repo
                productRepository.save(product);
            }

        }
        sellerRepository.save(seller);
        return modelMapper.map(seller, SellerDTO.class);
    }


    public List<SellerDTO> getAllSellers(){
        return sellerRepository.findAll().stream()
                .map(seller -> modelMapper.map(seller, SellerDTO.class)).toList();

    }

    // Log into the application
    public long authenticateSeller(LoginDTO loginDTO) {
        Seller seller = (sellerRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()))
                .orElseThrow(() -> new AccessDeniedException("Invalid username or password"));
                return seller.getId();
    }


}

