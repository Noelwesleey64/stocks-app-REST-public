package com.javaspringboot.stocks_app_rest_api.user.repository;

import com.javaspringboot.stocks_app_rest_api.user.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {


    ConfirmationToken findByToken(String token);



}
