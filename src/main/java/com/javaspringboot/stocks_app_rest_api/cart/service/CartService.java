package com.javaspringboot.stocks_app_rest_api.cart.service;

import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.cart.responsePayload.CartCreatedMessage;
import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;

import java.util.List;

public interface CartService {

    CartCreatedMessage CreateCart(String email);

    List<CartItem> getCartItems(String email);

    Cart getCart(String email);



}
