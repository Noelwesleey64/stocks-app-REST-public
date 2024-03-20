package com.javaspringboot.stocks_app_rest_api.cartItem.controller;

import com.javaspringboot.stocks_app_rest_api.cart.service.CartService;
import com.javaspringboot.stocks_app_rest_api.cartItem.dto.CartItemDto;
import com.javaspringboot.stocks_app_rest_api.cartItem.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping(value = "cart/addCartItem")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemDto cartItemDto){

        String response = cartItemService.AddCartItem(cartItemDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //post mapping for an item from cartItem
    @PostMapping(value = "cart/removeCartItem/{userName}")
    public ResponseEntity<?> removeCartItem(@RequestBody Long productId, @PathVariable String userName){
        //Pass the productId to the removeCartItem method in cartItemService
        //Then store the string message received
        String message = cartItemService.removeCartItem(productId, userName);

        //Return the message after removing cartItem
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    //post mapping for removing a product item from cartItem
    @PostMapping(value = "cart/removeCartItem/units/{userName}/{units}")
    public ResponseEntity<?> removeUnitsFromCartItem(@RequestBody Long productId, @PathVariable String userName, @PathVariable int units){
        //Pass the productId to the removeCartItem method in cartItemService
        //Then store the string message received
        String message = cartItemService.removeUnitsFromCartItem(productId, userName, units);

        //Return the message after removing cartItem
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    //post mapping for add units to  a product in cartItem
    @PostMapping(value = "cart/addCartItem/units/{userName}/{units}")
    public ResponseEntity<?> addUnitsToCartItem(@RequestBody Long productId, @PathVariable String userName, @PathVariable int units){
        //Pass the productId to the addUnitsToCartItem method in cartItemService
        //Then store the string message received
        String message = cartItemService.addUnitsToCartItem(productId, userName, units);

        //Return the message after removing cartItem
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }






}
