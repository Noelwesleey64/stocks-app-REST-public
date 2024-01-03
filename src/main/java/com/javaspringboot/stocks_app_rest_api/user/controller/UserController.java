package com.javaspringboot.stocks_app_rest_api.user.controller;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.event.RegistrationCompleteEvent;
import com.javaspringboot.stocks_app_rest_api.user.repository.ConfirmationTokenRepository;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.RegisterResponse;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Rest Controller for our login
@RestController
@CrossOrigin
public class UserController {

    //Create a user service object to access important methods
    @Autowired
    private UserService userService;

    //ApplicationEventPublisher interface is part of the Spring Framework, and it encapsulates the functionality of publishing events to registered listeners.
    //  Publishing an event is a way of notifying other components of something that happened in the application.
    @Autowired
    private ApplicationEventPublisher publisher;

    //Inject confirmationTokenRepository
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    //Post mapping to save user
    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTo userDto, @Autowired HttpServletRequest request){

         RegisterResponse registerResponse = userService.addUser(userDto);

         //If the register response.status is false, which means there was a problem with sign up then: just return a ResponseEntity<?>
         if(!registerResponse.status){
             return ResponseEntity.ok(registerResponse);
             //Else publish a registration complete even
         } else {
             //get user object for the user added to database
             UserTbl user = userService.getUser(registerResponse.userName);

             //Publish Registration Event
             //Takes the Application Url if the http request
             publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));


             //username of the user added to the database
             return ResponseEntity.ok(registerResponse);
         }

    }

    @GetMapping("/register/verifyEmail")
    //The GET request will have a token value attached to it
    public String verifyEmail(@RequestParam("token") String token){

         //Return ConfirmationToken object if the token is available in the database
        ConfirmationToken verifyToken = confirmationTokenRepository.findByToken(token);

        //Check if  user who has the passed token,  isEnabled property is true
        if(verifyToken.getUser().isEnabled()){
            return "This account has already been verified";
        }



       //if the above  IfStatement is not valid, the validate if the token has expired or not using userService.validateToken method
       //and set the isEnabled property of a user to true if token hasn't expired
        return userService.validateToken(token);

    }

    @GetMapping("/damn")
    public String damn(){
        return  "Lefuck you 3";
    }

    @PostMapping("/login")
    //ResponseEntity class is used to represent the entire HTTP response, including the status code, headers, and body.
    //The ? in ResponseEntity<?> is a wildcard that represents an unknown type
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDto){
        LoginResponse loginMessage = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginMessage);
    }

    //Method to create a link from which the http request was received from
    //The HttpServletRequest object represents the HTTP request sent by the client to the server
    public String applicationUrl(HttpServletRequest request){
        //We create a link based on the HttpServletRequest Object
       // getServerName() returns the host name of the server that received the request.
        // getServerPort() returns the port number on which the request was received.
        //getContextPath() returns the part of the request URI that indicates the context of the request
        return  "http://" + request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}


