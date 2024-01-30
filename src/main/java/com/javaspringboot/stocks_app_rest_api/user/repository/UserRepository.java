package com.javaspringboot.stocks_app_rest_api.user.repository;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//annotation
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<UserTbl, Long> {

    //Query method to find user by email
    Optional<UserTbl> findByEmail(String email);


    //Query method to find user by username
    UserTbl findByUserName(String userName);

    //Query method to find user by email and password
    Optional<UserTbl> findByEmailAndPassword(String email, String password);

    UserTbl findByUserNameOrEmail(String username, String email);


    //Query method to find user by username and password
    Optional<UserTbl> findByUserNameAndPassword(String userName, String password);
}
