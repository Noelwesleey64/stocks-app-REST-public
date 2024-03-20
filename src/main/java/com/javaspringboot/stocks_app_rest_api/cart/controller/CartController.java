package com.javaspringboot.stocks_app_rest_api.cart.controller;


import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.cart.responsePayload.CartCreatedMessage;
import com.javaspringboot.stocks_app_rest_api.cart.service.CartService;
import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    //Post mapping for creating a cart
    @PostMapping(value = "cart/createCart")
    public ResponseEntity<?> createCart(@RequestBody String email){

        //Create a payload message object to return the status of cart creation
        //Call the method create cart and pass userName to it
        CartCreatedMessage cartCreatedMessage = cartService.CreateCart(email);

        //return the cartCreatedMessage object created
        return  ResponseEntity.status(HttpStatus.OK).body("true");

    }

    @GetMapping(value = "cart/user/getCartItems/{email}")
    public ResponseEntity<?> getCartItems(@PathVariable String email){

        List<CartItem> cartItems = cartService.getCartItems(email);

        //return the cartCreatedMessage object created
        return  ResponseEntity.status(HttpStatus.OK).body(cartItems);

    }


    //Post mapping for creating a cart
    @GetMapping (value = "cart/createCart/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email){

        //Create a payload message object to return the status of cart creation
        //Call the method create cart and pass userName to it
        Cart cart = cartService.getCart(email);

        //return the cartCreatedMessage object created
        return  ResponseEntity.status(HttpStatus.OK).body(cart);

    }











}
