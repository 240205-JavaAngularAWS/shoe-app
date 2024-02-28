package com.revature.paymore.service;
import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UnauthorizedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.DTO.AddressDTO;
import com.revature.paymore.model.DTO.LoginDTO;
import com.revature.paymore.model.DTO.OrderDTO;
import com.revature.paymore.model.DTO.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Order;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    // DTO Methods
    public UserDTO convertToSimpleDTO(User user){
        // new userDTO
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername());

    }

    public UserDTO convertToExtendedDTO(User user){
        // new userDTO
        Set<OrderDTO> orders = new HashSet<>();
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getShippingAddress().getId(),
                orders
        );

    }

    public User convertToEntity(UserDTO userDto) {
        // requires Extended DTO

        // Add logic to pull orders.
        Set<Address> addresses = new HashSet<>();
        Set<Order> orders = new HashSet<>();
        User user = new User();
                user.setId(userDto.getId());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                if(user.getShippingAddress() != null){
                    // TODO: create custom exception
                    Address shippingAddress = addressRepository.findById(userDto.getShippingAddressId())
                            .orElseThrow(() -> new RuntimeException());
                    user.setShippingAddress(shippingAddress);
                }

                user.setOrders(orders);
                return user;
    }






    // register as a buyer/user
    public UserDTO registerUser(UserDTO userDTO){

        User user = convertToEntity(userDTO);

        // checking for username doesn't exist
//        userRepository.findByUsername(user.getUsername()).ifPresent(existingUser -> {
//                    throw new UsernameAlreadyExistsException("Username already exists.");

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        userRepository.save(user);
        return convertToSimpleDTO(user);

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
