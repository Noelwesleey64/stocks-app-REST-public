package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.User;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Implementing the user service interface methods
@Service
public class UserServiceimpl implements UserService{


    //Auto wire User repository
    @Autowired
    private UserRepository userRepository;

    //Auto Wire Password Encoder
    @Autowired
    private PasswordEncoder passwordEncoder;



    //Mapping the UserDTO object we receive form the POST request to our User entity object
    //So that we can save the user to database
    @Override
    public String addUser(UserDTo userDTo) {

        //Instantiate user object to store userDTo data passed from post request
        User user = new User();

        //map dto received to user entity
        user.setUserName(userDTo.getUserName());
        user.setFirstName(userDTo.getFirstName());
        user.setLastName(userDTo.getLastName());
        user.setEmail(userDTo.getEmail());
        user.setCreatedTime(userDTo.getCreatedTime());
        user.setPhoneNo(userDTo.getPhoneNo());
        user.setPassword(passwordEncoder.encode(userDTo.getPassword()));

        //Checking if user already exists
        //Check if a user with the username exists
        User userValid = userRepository.findByUserName(user.getUserName());

        //Check if the user with the email exist
        User userValid2 = userRepository.findByEmail(user.getEmail());

        if(userValid != null || userValid2 != null){
            return "User Already Exists";

        }else{
            //save the mapped user from dto to database
            userRepository.save(user);

            //return username of user after saving
            return user.getUserName();
        }



    }




    //Method to login user
    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";

        //find user based on email
        User user = userRepository.findByEmail(loginDTO.getUserNameOrEmail());

        //return user based on username
        User user2 = userRepository.findByUserName(loginDTO.getUserNameOrEmail());

        //Check for user using email and password

        //If the email exists in the database
        if(user != null){

            //get the password passed form loginDTO
            String password = loginDTO.getPassword();

            //Get the encoded password of the user found from database
            String encodedPassword = user.getPassword();

            //check if password from the request body matches the one in the database
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            //If the password matches the one in database, then check if the password and email in database for user exist
            if(isPwdRight){
                Optional<User> emailPasswordUser = userRepository.findByEmailAndPassword(loginDTO.getUserNameOrEmail(), encodedPassword);

                //If password and email are in database then login success
                if(emailPasswordUser.isPresent()){
                    return new LoginResponse("Login Success", true);

                }else{
                    //login failed
                    return new LoginResponse("Login Failed", false);
                }
             //If password is not right  then Incorrec Password or email

            }else {
                return new LoginResponse("Incorrect Password or email", false);

            }

            //Login using username and password

            //Check if there is a user with the username given
        }else if(user2 != null){
            //Get the password from the Post request body
            String password = loginDTO.getPassword();

            //Get the encoded password of the user from the database
            String encodedPassword = user2.getPassword();

            //Check if the password matches the one in the datatbase
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            //if it matches then
            if(isPwdRight){

                //Then check if there is a user with the username and encodedpassword existing
                Optional<User> UserNamePasswordUser = userRepository.findByUserNameAndPassword(loginDTO.getUserNameOrEmail(), encodedPassword);

                //If user exists then login
                if(UserNamePasswordUser.isPresent()){
                    return new LoginResponse("Login Success", true);

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

}



