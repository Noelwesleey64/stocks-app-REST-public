package com.javaspringboot.stocks_app_rest_api.user.controller;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.event.RegistrationCompleteEvent;
import com.javaspringboot.stocks_app_rest_api.user.event.listener.RegistrationCompleteEventListener;
import com.javaspringboot.stocks_app_rest_api.user.repository.ConfirmationTokenRepository;
import com.javaspringboot.stocks_app_rest_api.user.requestBody.ProfileUploadRequest;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.ProfileUploadResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.RegisterResponse;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import com.javaspringboot.stocks_app_rest_api.user.utility_objects.ImageProfile;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
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

    @Autowired
    private RegistrationCompleteEventListener eventListener;

    @Autowired
    private HttpServletRequest servletRequest;

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


        //Create a string variable to hold the link that we will resend when the token expires
        String url = applicationUrl(servletRequest) + "/register/resend-verification-token?token=" + token;

         //Return ConfirmationToken object if the token is available in the database
        ConfirmationToken verifyToken = confirmationTokenRepository.findByToken(token);

        //Check if  user who has the passed token,  isEnabled property is true
        if(verifyToken.getUser().isEnabled()){
            return "This account has already been verified";
        }



       //if the above  IfStatement is not valid, the validate if the token has expired or not using userService.validateToken method
       //and set the isEnabled property of a user to true if token hasn't expired
        String verificationResult = userService.validateToken(token);

        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email has been succesfully verified";

        }

        //Show a link to resend email
        return "Invalid verification link, <a href=\"" + url +"\"> Get a new verification link. </a>";

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

    ////The GET request will have a token value attached to it
    @GetMapping("/register/resend-verification-token")
    public String resendVerififcationCode(@RequestParam("token") String oldToken, @Autowired HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

            //Generate a new confirmation token object from and store it in the database
            ConfirmationToken confirmationToken = userService.generateNewVerificationToken(oldToken);
            //Get user with the confirmation token object gotten from the database below
            UserTbl user = confirmationToken.getUser();

            //Method to resend cerification email
            resendVerificationTokenEmail(user, applicationUrl(request), confirmationToken);

            return "A new Verification link has been sent to your email";

    }

    @GetMapping("/user/getUser/{username}")
    public UserTbl getUser(@PathVariable String username){
        UserTbl user = userService.getUser(username);

        return user;

    }

    //Annotate the method with @PostMapping to map it to the /user/profileUpload/{userName} URL, where {userName} is a path variable
    //the Post method returns a ProfileUploadResponse and takes a MultiPartFile
    @PostMapping("/user/profileUpload/{userName}")
    public ProfileUploadResponse profileUpload(@RequestParam MultipartFile file, @PathVariable String userName) throws IOException {

        //Call the uploadProfile method from the userService object, passing the file and userName parameters, and assign the returned value to uploadImage
         ProfileUploadResponse uploadImage = userService.uploadProfile(file, userName);

        //Return the uploadImage object as the response body
         return uploadImage;

    }

    //Annotate the method with @GetMapping to map it to the /user/getProfile/{userName} URL, where {userName} is a path variable
    @GetMapping(value = "/user/getProfile")
    public ResponseEntity<?> getProfileImage() throws IOException {

        //Call the getProfileImage method from the userService object, passing the userName parameter, and assign the returned value to image
        ImageProfile image = userService.getProfileImage("noelwesley64@gmail.com");
        String imageString = Base64.getEncoder().encodeToString(image.getImage());

        //Create a new ResponseEntity object with the status code of OK (200), the content type of the image, and the image data as the body
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(image.getImageType())).body(image.getImage());

    }

    @GetMapping("/user/getHello/{username}")
    public ResponseEntity<?> getHello(@PathVariable String username) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(username + "Hello Noelski");

    }


    //Method to resend verification token
    private void resendVerificationTokenEmail(UserTbl user,
                                              String applicationUrl,
                                              ConfirmationToken confirmationToken) throws MessagingException, UnsupportedEncodingException {

        //Build the verification url to be sent to the user
        String url = applicationUrl+"/register/verifyEmail?token=" + confirmationToken.getToken().toString();

        //Call the method used to send email from the RegistrationCompleteEventListenerObject
        eventListener.sendVerificationEmail(url, user);

        //Show our url created in the log
        log.info("Click the link to verify your registration: {}", url);
    }




}


