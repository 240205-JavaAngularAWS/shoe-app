package com.revature.paymore.service;
import com.revature.paymore.model.DTO.AddressDTO;
import com.revature.paymore.model.DTO.OrderDTO;
import com.revature.paymore.model.DTO.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Order;
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
        Set<AddressDTO> addresses = new HashSet<>();
        Set<OrderDTO> orders = new HashSet<>();
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                addresses,
                orders
        );

    }

    public User convertToEntity(UserDTO userDto) {
        // requires Extended DTO

        // Add logic to pull addresses and orders.
        Set<Address> addresses = new HashSet<>();
        Set<Order> orders = new HashSet<>();
        User user = new User();
                user.setId(userDto.getId());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                user.setAddresses(addresses);
                user.setOrders(orders);
                return user;
    }






    // register as a buyer/user



    // Log into the application
    public long authenticateSeller() {
        return 495L;}

}
