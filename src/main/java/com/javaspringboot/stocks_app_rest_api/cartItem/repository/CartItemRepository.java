package com.javaspringboot.stocks_app_rest_api.cartItem.repository;

import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    //find cart item based on product
    CartItem findByProduct (Product product);

    CartItem findByProductAndUser(Product product, UserTbl user);

    List<CartItem> findByUserAndStatus(UserTbl user, Boolean status );

}
