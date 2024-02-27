package com.revature.paymore.service;
import com.revature.paymore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import com.revature.paymore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

>>>>>>> cf9447008c0965ce04b0ae0cef4fffb74306300e

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
<<<<<<< HEAD
=======



    // register as a buyer
    public User registerSeller(User user)
    {

    }


    // Log into the application
    public long authenticateSeller()
    {

    }
>>>>>>> cf9447008c0965ce04b0ae0cef4fffb74306300e
}
