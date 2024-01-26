package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.ConfirmationTokenRepository;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class UserTblServiceimplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    public void saveuser(){
        UserDTo userDTo = new UserDTo();
        userDTo.setUserName("NoelWesley64");
        userDTo.setFirstName("Noel");
        userDTo.setLastName("Wesley");
        userDTo.setEmail("NoelWesley64@gmail.com");
        userDTo.setPhoneNo("0703334653");
        userDTo.setPassword("NoelWesley254");

        String username = String.valueOf(userService.addUser(userDTo));

    System.out.println("The username is: " + username);
    }

    @Test
    public void deleteUser(){
        UserTbl userTbl = userRepository.findByUserName("noelwesleey64");
        ConfirmationToken confirmationToken= confirmationTokenRepository.findByToken("e91d1886-3e56-45a9-9f79-1fb744e21321");

        confirmationTokenRepository.delete(confirmationToken);
        userRepository.delete(userTbl);
    }

    @Test
    public void calenderTime(){

        ConfirmationToken token = confirmationTokenRepository.findByToken("846fc9a0-a08a-484f-a1a0-b53dea42f8ba");

        Calendar calendar = Calendar.getInstance();
        //sets the calendar’s time value to the current system time in milliseconds.


        System.out.println("Curremt time is" + calendar.getTime().getTime());
        System.out.println("Current Token Expiration Time is" + token.getExpirationTime().getTime());


    }

    @Test
    public void getTokenExpirationTime(){
        ConfirmationToken token = confirmationTokenRepository.findByToken("846fc9a0-a08a-484f-a1a0-b53dea42f8ba");


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