package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.User;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceimplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveuser(){
        UserDTo userDTo = new UserDTo();
        userDTo.setUserName("NoelWesley64");
        userDTo.setFirstName("Noel");
        userDTo.setLastName("Wesley");
        userDTo.setEmail("NoelWesley64@gmail.com");
        userDTo.setPhoneNo("0703334653");
        userDTo.setPassword("NoelWesley254");

        String username = userService.addUser(userDTo);

    System.out.println("The username is: " + username);
    }


    @Test
    public void login(){

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUserNameOrEmail("NoelWesley64");

       loginDTO.setPassword("NoelWesley254");



        LoginResponse loginResponse =  userService.loginUser(loginDTO);

        System.out.println("Login Reponse" + loginResponse);



    }









}