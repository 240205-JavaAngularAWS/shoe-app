package com.revature.paymore.controller;
import com.revature.paymore.model.dto.LoginDTO;
import com.revature.paymore.model.dto.UserDTO;
import com.revature.paymore.model.User;
import com.revature.paymore.service.ResponseHelperService;
import com.revature.paymore.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final ResponseHelperService responseHelperService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService, ResponseHelperService responseHelperService) {
        this.userService = userService;
        this.responseHelperService = responseHelperService;
    }


    // registerUser
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        UserDTO response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        long userId = userService.authenticateUser(loginDTO);

        return ResponseEntity.ok().body(userId);

    }

}
