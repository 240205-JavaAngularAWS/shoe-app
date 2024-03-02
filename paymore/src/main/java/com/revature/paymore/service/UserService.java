package com.revature.paymore.service;
import com.revature.paymore.exception.AccessDeniedException;
import com.revature.paymore.exception.UnauthorizedException;
import com.revature.paymore.exception.UsernameAlreadyExistsException;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.repository.AddressRepository;
import com.revature.paymore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    UserRepository userRepository;


    AddressRepository addressRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private ModelMapper modelMapper = new ModelMapper();





    // register as a buyer/user
    public UserDTO registerUser(User user){
        // check if username exists
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);

    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).toList();

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
