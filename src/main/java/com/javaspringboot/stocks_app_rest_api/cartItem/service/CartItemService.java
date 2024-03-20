package com.javaspringboot.stocks_app_rest_api.cartItem.service;

import com.javaspringboot.stocks_app_rest_api.cartItem.dto.CartItemDto;

public interface CartItemService {

    String AddCartItem(CartItemDto cartItem);

    String removeCartItem(Long productId, String userName);

    String removeUnitsFromCartItem(Long productId, String userName, int units);

    String addUnitsToCartItem(Long productId, String userName, int units);
}

