package com.revature.paymore.service;
import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UnauthorizedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.CreditCard;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final CreditCardRepository creditCardRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, SellerRepository sellerRepository, CreditCardRepository creditCardRepository, AddressRepository addressRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.creditCardRepository = creditCardRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }



    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private void registerUserAddresses(User user){
        user.getAddresses().forEach(
                address -> {
                    address.setUser(user);
                    addressRepository.save(address);
                });

    }

    private void registerUserCreditCards(User user) {
        user.getCreditCards().forEach(
                creditCard -> {
                    creditCard.setUser(user);
                    creditCardRepository.save(creditCard);
                });
    }

    private void registerOrders(User user) {
     user.getOrders().forEach(
             order -> {
                 order.setUser(user);
                 orderRepository.save(order);
             });
    }


    // register as a buyer/user
    public UserDTO registerUser(User user){
        // check if username exists
        if (sellerRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        userRepository.save(user);
        // save addresses
        if(user.getAddresses() != null){registerUserAddresses(user);}
        if(user.getCreditCards() != null){registerUserCreditCards(user);}
        if(user.getOrders() != null){registerOrders(user);}
        return modelMapper.map(user, UserDTO.class);

    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).toList();

    }


    public UserDTO getUserById(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return modelMapper.map(user, UserDTO.class);
    }



    // Log into the User application
    public long authenticateUser(LoginDTO loginDTO) {

        User foundUser = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new AccessDeniedException("User not found."));
        if (!loginDTO.getPassword().equals(foundUser.getPassword())) {
            throw new UnauthorizedException("Incorrect Username or Password");
        }

        return foundUser.getId();
    }

}
