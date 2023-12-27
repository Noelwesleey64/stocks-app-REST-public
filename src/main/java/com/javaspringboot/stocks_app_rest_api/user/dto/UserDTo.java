package com.javaspringboot.stocks_app_rest_api.user.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//Class whose object will be recieved by the request and mapped onto the entity for storage
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

//Its object will be used to store the data in the post request bodydfff
public class UserDTo {


    //Fields that the body will contain when user sends a post request
    private String userName;
    private String firstName;

    private String lastName;
    private  String email;

    private String phoneNo;
    private String password;
    private LocalDateTime createdTime;

}
