package com.revature.paymore.controller;
import com.revature.paymore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;



    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    // registerUser
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto){
        UserDTO response = userService.registerUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



}
