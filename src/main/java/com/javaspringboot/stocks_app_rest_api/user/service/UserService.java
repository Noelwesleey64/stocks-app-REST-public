package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.dto.LoginDTO;
import com.javaspringboot.stocks_app_rest_api.user.dto.UserDTo;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.requestBody.ProfileUploadRequest;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.LoginResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.ProfileUploadResponse;
import com.javaspringboot.stocks_app_rest_api.user.responsepayload.RegisterResponse;
import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import com.javaspringboot.stocks_app_rest_api.user.utility_objects.ImageProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    //Method which will be implemented to register user
    RegisterResponse addUser(UserDTo userDTo);

    //Method which will be implemented to login user
    LoginResponse loginUser(LoginDTO loginDTO);

    List<UserTbl> getUsers();

    UserTbl getUser(String username);


    void saveUserVerificationToken(UserTbl user, String verificationToken);

    String validateToken(String verifyToken);


    ProfileUploadResponse uploadProfile(MultipartFile file, String userName) throws IOException;

    ConfirmationToken generateNewVerificationToken(String oldToken);

    ImageProfile getProfileImage(String userName) throws IOException;
}
