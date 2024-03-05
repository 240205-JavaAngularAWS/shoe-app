package com.revature.paymore.service;

import com.revature.paymore.exception.EntityAlreadyExistsException;
import com.revature.paymore.model.*;
import com.revature.paymore.model.dto.CreditCardDTO;
import com.revature.paymore.model.enums.AddressType;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.CreditCardRepository;
import com.revature.paymore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;


    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository =  userRepository;
        this.addressRepository =  addressRepository;
    }

    ModelMapper modelMapper = new ModelMapper();


    public CreditCardDTO addCreditCard(CreditCard creditCard) {
        // uses the entity so that address can be directly added.
        // Get user
        User user = creditCard.getUser();


        // check if credit card is already associated with user.
        creditCardRepository.findByUserAndCardNumber(user, creditCard.getCardNumber())
                .ifPresent(c -> {
                    throw new EntityAlreadyExistsException("Credit Card Already Added.");
                });

        Address address = creditCard.getAddress();
        // always ensure address type is set to billing
        address.setAddressType(AddressType.BILLING);

        Address savedAddress = addressRepository.save(address);
        creditCard.setUser(user);
        creditCard.setAddress(savedAddress);

        creditCardRepository.save(creditCard);
        return modelMapper.map(creditCard, CreditCardDTO.class);
    }


    public List<CreditCardDTO> findCreditCardsByUserId(Long userId){
        // get user object from userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because user wasn't found"));
        return creditCardRepository.findByUser(user).stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class)).toList();
    }


    public List<CreditCardDTO> findAllCreditCards(){
        return creditCardRepository.findAll().stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class)).toList();
    }



    public boolean deleteCreditCard(long creditCardId){
        // ensure at least one creditcard exists prior to deletion.
        return creditCardRepository.findById(creditCardId)
                .map(user -> { creditCardRepository.deleteById(creditCardId);
                    return true;})
                .orElse(false);




    }




}
