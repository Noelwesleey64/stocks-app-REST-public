package com.javaspringboot.stocks_app_rest_api.user.service;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceimplTest {



    @Autowired
    private UserRepository userRepository;

    @Test
    public void deleteUser(){

        UserTbl user = userRepository.findByUserName("Noelwesley64");

        userRepository.delete(user);



    }

}