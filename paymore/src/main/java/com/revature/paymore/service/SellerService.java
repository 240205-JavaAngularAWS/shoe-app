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
import com.revature.paymore.validation.ProductValidator;
import com.revature.paymore.validation.SellerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


@Service
public class SellerService {


    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final ValidationService validationService;



    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);


    @Autowired
    public SellerService(SellerRepository sellerRepository, UserRepository userRepository, AddressRepository addressRepository, ProductRepository productRepository, ValidationService validationService) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    

    // register as a seller
    public SellerDTO registerSeller(Seller seller) {
        // Validate Seller Object
        validationService.validateSeller(seller);

        // Ensure Username isn't taken by other sellers.
        if (sellerRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        // Ensure Username isn't taken by other users.
        if (userRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // Validate Address
        validationService.validateAddress(seller.getAddress());

        Address sellerAddress = seller.getAddress();
        sellerAddress.setAddressType(AddressType.SELLER);
        // save address to repo
        addressRepository.save(sellerAddress);

        if(!seller.getProducts().isEmpty()){
            for(Product product: seller.getProducts()){
                // validate product
                validationService.validateProduct(product);

                // save product to repo
                productRepository.save(product);
            }

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

