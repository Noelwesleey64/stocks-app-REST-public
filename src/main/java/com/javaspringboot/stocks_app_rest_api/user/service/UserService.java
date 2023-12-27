package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;

public interface UserService {

    //Method which will be implemented to register user
    String addUser(UserDTo userDTo);

    //Method which will be implemented to login user
    LoginResponse loginUser(LoginDTO loginDTO);

}
