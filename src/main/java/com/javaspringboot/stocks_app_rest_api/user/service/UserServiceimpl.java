package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.ConfirmationTokenRepository;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.RegisterResponse;
import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

//Implementing the user service interface methods
@Service
public class UserServiceimpl implements UserService{


    //Auto wire User repository
    @Autowired
    private UserRepository userRepository;

    //Auto Wire Password Encoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Dependency Inject ConfirmationTokenRepository instance
    @Autowired
    private ConfirmationTokenRepository tokenRepository;





    //Mapping the UserDTO object we receive form the POST request to our User entity object
    //So that we can save the user to database
    @Override
    public RegisterResponse addUser(UserDTo userDTo) {

        //Instantiate user object to store userDTo data passed from post request
        UserTbl userTbl = new UserTbl();

        RegisterResponse registerResponse = new RegisterResponse();

        //map dto received to user entity
        userTbl.setUserName(userDTo.getUserName());
        userTbl.setFirstName(userDTo.getFirstName());
        userTbl.setLastName(userDTo.getLastName());
        userTbl.setEmail(userDTo.getEmail());
        userTbl.setCreatedTime(userDTo.getCreatedTime());
        userTbl.setPhoneNo(userDTo.getPhoneNo());
        userTbl.setPassword(passwordEncoder.encode(userDTo.getPassword()));

        //Checking if user already exists
        //Check if a user with the username exists
        UserTbl userTblValid = userRepository.findByUserName(userTbl.getUserName());

        //Check if the user with the email exist
        Optional<UserTbl> userTblValid2 = userRepository.findByEmail(userTbl.getEmail());

        if(userTblValid != null ||userTblValid2.isPresent()){
            return new RegisterResponse("User Already Exists", false,"");

        }else{
            //save the mapped user from dto to database
            userRepository.save(userTbl);

            //return username of user after saving
            return new RegisterResponse("User Created Successfully, Check email for verification", true, userTbl.getUserName());
        }



    }




    //Method to login user
    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";

        //find user based on email
       Optional<UserTbl> userTbl = userRepository.findByEmail(loginDTO.getUserNameOrEmail());

        //return user based on username
        UserTbl userTbl2 = userRepository.findByUserName(loginDTO.getUserNameOrEmail());

        //Check for user using email and password

        //If the email exists in the database
        if(userTbl.isPresent()){

            Boolean enabled = userTbl.get().isEnabled();

            //get the password passed form loginDTO
            String password = loginDTO.getPassword();

            //Get the encoded password of the user found from database
            String encodedPassword = userTbl.get().getPassword();

            //check if password from the request body matches the one in the database
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            //If the password matches the one in database, then check if the password and email in database for user exist
            if(isPwdRight){
                Optional<UserTbl> emailPasswordUser = userRepository.findByEmailAndPassword(loginDTO.getUserNameOrEmail(), encodedPassword);

                //If password and email are in database and the user is enabled then login success
                if(emailPasswordUser.isPresent()){
                    //If user is enabled return a login response object with message below, else return the else statement
                    if(enabled){
                        return new LoginResponse("Login Success", true);
                    } else {
                        return new LoginResponse("Verify Email to login", false);
                    }


                }else{
                    //login failed
                    return new LoginResponse("Verify Email to login", false);
                }
             //If password is not right  then Incorrect Password or email

            }else {
                return new LoginResponse("Incorrect Password or email", false);

            }

            //Login using username and password

            //Check if there is a user with the username given
        }else if(userTbl2 != null){
            //Get the password from the Post request body
            String password = loginDTO.getPassword();

            Boolean enabled = userTbl2.isEnabled();

            //Get the encoded password of the user from the database
            String encodedPassword = userTbl2.getPassword();

            //Check if the password matches the one in the datatbase
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            //if it matches then
            if(isPwdRight){

                //Then check if there is a user with the username and encodedpassword existing
                Optional<UserTbl> UserNamePasswordUser = userRepository.findByUserNameAndPassword(loginDTO.getUserNameOrEmail(), encodedPassword);

                //If user exists then login
                if(UserNamePasswordUser.isPresent()){
                    //If user is enabled return a login response object with message below, else return the else statement
                    if(enabled){
                        return new LoginResponse("Login Success", true);
                    } else {
                        return new LoginResponse("Verify Email to login", false);
                    }

                    //if password is not correct, or doesn't match with the one in the database then login failed
                }else{
                    return new LoginResponse("Login Failed", false);
                }


                //If user with the username exist but password is wrong then
            }else {
                return new LoginResponse("Incorrect Password or email or username", false);

            }
            //If the username or password doesn't have a matching entity then user doesn't exist
        }else{
            return new LoginResponse("Email or Username does not exist", false);
        }
    }

    @Override
    public List<UserTbl> getUsers() {
        userRepository.findAll();
        return null;
    }

    @Override
    public UserTbl getUser(String username) {

        //return the user found by username passed
       return userRepository.findByUserName(username);

    }

    @Override
    public void saveUserVerificationToken(UserTbl user, String verificationToken) {
        //Creating a confirmation token object and passing user and the verification token
        ConfirmationToken confirmationToken = new ConfirmationToken(verificationToken, user);

        //Saving verification token for the user
        tokenRepository.save(confirmationToken);




    }

    @Override
    public String validateToken(String verifyToken) {
        //finding a token object using the methodFindByToken, which returns a token object if the token passed from the get request exists
        ConfirmationToken token = tokenRepository.findByToken(verifyToken);

        UserTbl user = token.getUser();

        Calendar calendar = Calendar.getInstance();
        //sets the calendar’s time value to the current system time in milliseconds.


        //If the token expiration time has passed, then execute the statement
        if(token.getExpirationTime().getTime() < calendar.getTime().getTime()){

            return "Token already expired";
        }

        //If the token is available and has not expired then set isEnabled to true
        user.setEnabled(true);

        //Save user to database with the isEnabled property being true
        userRepository.save(user);

        return "valid";
    }

    //method to generate a new confirmation token
    @Override
    public ConfirmationToken generateNewVerificationToken(String oldToken) {

        //Get a token object whose token matches the old expired token passed to the method
        ConfirmationToken confirmationToken = tokenRepository.findByToken(oldToken);

        //Create a new Confirmation token object so that we can access the time the token needs to expire
        ConfirmationToken confirmationTokenTime = new ConfirmationToken();

        //Generate a new token and set it to the confirmation token object found from the database
        confirmationToken.setToken(UUID.randomUUID().toString());

        //Set expiration time to the confirmationTokenTime getTokenExpirationTime which returns the expiration time for the token
        confirmationToken.setExpirationTime(confirmationTokenTime.getTokenExpirationTime());

        return tokenRepository.save(confirmationToken);
    }

}



