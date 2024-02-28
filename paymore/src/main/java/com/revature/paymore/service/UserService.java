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





    // register as a buyer/user
    public UserDTO registerUser(User user){


        // check if username exists
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        userRepository.save(user);
        return new UserDTO(user);

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
