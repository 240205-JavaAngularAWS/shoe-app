package com.revature.paymore.controller;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // registerUser
    @PostMapping("/registerUser")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user) {
        UserDTO response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }




    @PostMapping("/loginUser")
    public ResponseEntity<Long> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        long userId = userService.authenticateUser(loginDTO);

        return ResponseEntity.ok().body(userId);

    }

}
