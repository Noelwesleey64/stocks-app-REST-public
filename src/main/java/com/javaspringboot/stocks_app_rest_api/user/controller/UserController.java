package com.javaspringboot.stocks_app_rest_api.user.controller;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Rest Controller for our login
@RestController
@CrossOrigin
public class UserController {

    //Create a user service object to access important methods
    @Autowired
    private UserService userService;

    //Post mapping to save user
    @PostMapping(value = "/register")
    public String saveUser(@RequestBody UserDTo userDto){

        //uername of the user added to the database
        return userService.addUser(userDto);

    }

    @PostMapping("/login")
    //ResponseEntity class is used to represent the entire HTTP response, including the status code, headers, and body.
    //The ? in ResponseEntity<?> is a wildcard that represents an unknown type
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDto){
        LoginResponse loginMessage = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginMessage);
    }
}
