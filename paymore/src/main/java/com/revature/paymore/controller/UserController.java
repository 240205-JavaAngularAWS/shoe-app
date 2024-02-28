package com.revature.paymore.controller;
import com.revature.paymore.model.DTO.LoginDTO;
import com.revature.paymore.model.DTO.UserDTO;
import com.revature.paymore.model.User;
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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        UserDTO response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {

        long userId = userService.authenticateUser(loginDTO);

        return ResponseEntity.ok().body(userId);

    }

}
