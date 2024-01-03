package com.javaspringboot.stocks_app_rest_api.user.securityconfig;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //defines the signature of the method, which takes a string parameter named email and returns an object that implements the UserDetails
    //The method also throws a UsernameNotFoundException if the user is not found by the email.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


            // invokes the findByEmail method of the userRepository object, which returns an Optional<User> object.
        return userRepository.findByEmail(email)

                //.map(UserRegistrationDetails::new) applies the map operation on the Optional object,
                // which transforms the value inside the Optional, if present, into UserRegistrationDetails objects by applying a constructor.
                .map(UserRegistrationDetails::new)
                //Throw an exception if the optional value is empty
                .orElseThrow(()->new UsernameNotFoundException("User not found"));


    }
}
