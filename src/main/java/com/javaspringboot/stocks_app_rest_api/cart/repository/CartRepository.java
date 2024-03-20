package com.javaspringboot.stocks_app_rest_api.cart.repository;

import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findById(Long cartId);

    Cart findByUser(UserTbl user);




}
