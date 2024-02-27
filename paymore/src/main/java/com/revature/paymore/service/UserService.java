package com.revature.paymore.service;
import com.revature.paymore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    // register as a buyer



    // Log into the application
    public long authenticateSeller() {
        return 495L;}

}
