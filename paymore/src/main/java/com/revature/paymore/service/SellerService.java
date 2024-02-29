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
    private final AddressRepository addressRepository;

    private final ProductRepository productRepository;


    private final SellerValidator sellerValidator;

    private final ProductValidator productValidator;

    @Autowired
    public SellerService(SellerRepository sellerRepository, AddressRepository addressRepository, ProductRepository productRepository, SellerValidator sellerValidator, ProductValidator productValidator) {

        this.sellerRepository = sellerRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.sellerValidator = sellerValidator;
        this.productValidator = productValidator;
    }



    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);




    private void validateSeller(Seller seller){
        Errors errors = new BeanPropertyBindingResult(seller, "seller");
        sellerValidator.validate(seller, errors);
        if (errors.hasErrors()) {
            throw new InvalidSellerException("Seller is Invalid.", errors);
        }
    }

    private void validateProduct(Product product){
        Errors errors = new BeanPropertyBindingResult(product, "product");
        productValidator.validate(product, errors);
        if (errors.hasErrors()) {
            throw new InvalidProductException("Product is Invalid.", errors);
        }
    }





    // register as a seller
    public SellerDTO registerSeller(Seller seller) {
        // seller validation
        // ensure there is an address
        // ensure there is a companyName
        // ensure there is a valid

        if (sellerRepository.findByUsername(seller.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // register address id present

        Address sellerAddress = seller.getAddress();
        sellerAddress.setAddressType(AddressType.SELLER);
        addressRepository.save(sellerAddress);

        if(!seller.getProducts().isEmpty()){
            for(Product product: seller.getProducts()){
                validateProduct(product);
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

