package com.revature.paymore.service;

import com.revature.paymore.exception.EntityAlreadyExistsException;
import com.revature.paymore.model.*;
import com.revature.paymore.model.dto.AddressDTO;
import com.revature.paymore.model.dto.CreditCardDTO;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.repository.CreditCardRepository;
import com.revature.paymore.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private UserRepository userRepository;


    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository =  userRepository;
    }

    ModelMapper modelMapper = new ModelMapper();


    public CreditCardDTO addCreditCard(CreditCardDTO creditCardDto) {
        // Make sure user exists.
        User user = userRepository.findById(creditCardDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        // check if credit card is already associated with user.
        creditCardRepository.findByUserAndCardNumber(user, creditCardDto.getCardNumber())
                .ifPresent(creditCard -> {
                    throw new EntityAlreadyExistsException("Credit Card Already Added");
                });

        // create new credit card with user.
        CreditCard creditCard = new CreditCard(creditCardDto.getCardNumber(),
                creditCardDto.getSecurityCode(),
                creditCardDto.getFirstName(),
                creditCardDto.getLastName(),
                creditCardDto.getExpirationDate(),
                user);

        creditCardRepository.save(creditCard);
        user.addCreditCard(creditCard); // Assuming there's a method in User to add a credit card

        userRepository.save(user);
        return modelMapper.map(creditCard, CreditCardDTO.class);
    }


    public List<CreditCardDTO> findCreditCardsByUserId(Long userId){
        // get user object from userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("There is no product because user wasn't found"));
        return creditCardRepository.findByUser(user).stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDTO.class)).toList();
    }

    public boolean deleteCreditCard(long creditCardId){
        CreditCard creditCard = creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new EntityNotFoundException("Credit Card Not Found"));

        User user = creditCard.getUser();
        user.removeCreditCard(creditCard);
        userRepository.save(user);

        creditCardRepository.deleteById(creditCardId);

        return true;



    }




}
