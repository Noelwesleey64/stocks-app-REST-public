package com.javaspringboot.stocks_app_rest_api.user.repository;

import com.javaspringboot.stocks_app_rest_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//annotation
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Query method to find user by email
    User findByEmail(String email);

    //Query method to find user by user name
    User findByUserName(String userName);

    //Query method to find user by email and password
    Optional<User> findByEmailAndPassword(String email, String password);


    //Query method to find user by username and password
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
