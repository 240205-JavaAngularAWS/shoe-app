package com.revature.paymore.service;



import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.exception.InvalidSellerException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.dto.*;
import com.revature.paymore.model.enums.AddressType;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.SellerRepository;
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

    private final SellerValidator sellerValidator;

    @Autowired
    public SellerService(SellerRepository sellerRepository, AddressRepository addressRepository, SellerValidator sellerValidator) {

        this.sellerRepository = sellerRepository;
        this.addressRepository = addressRepository;
        this.sellerValidator = sellerValidator;
    }



    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);




    private void validateSeller(Seller seller){
        Errors errors = new BeanPropertyBindingResult(seller, "seller");
        sellerValidator.validate(seller, errors);
        if (errors.hasErrors()) {
            throw new InvalidSellerException("Seller is Invalid.", errors);
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

//        if(seller.getProducts() != null){
//            seller.getProducts().stream().map()
//        }
        


        // register products if present.



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

