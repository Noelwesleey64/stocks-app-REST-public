package com.javaspringboot.stocks_app_rest_api.cart.service;

import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.cart.repository.CartRepository;
import com.javaspringboot.stocks_app_rest_api.cart.responsePayload.CartCreatedMessage;
import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import com.javaspringboot.stocks_app_rest_api.cartItem.repository.CartItemRepository;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    //Method to create card
    @Override
    public CartCreatedMessage CreateCart(String email) {

        //Find a user with the userName passed
        Optional<UserTbl> user = userRepository.findByEmail(email);

        Cart cart2 = cartRepository.findByUser(user.get());

        if(cart2 != null){
            return new CartCreatedMessage();
        }

        //Create a Cart object that will be added to the database
        Cart cart = new Cart();

        //Add the user to the cart object
        cart.setUser(user.get());

        //Save Cart to database
        cartRepository.save(cart);


        return new CartCreatedMessage("Cart Created Successfully", true);
    }

    @Override
    public List<CartItem> getCartItems(String email) {
        //Find a user with the userName passed
        Optional<UserTbl> user = userRepository.findByEmail(email);

        List<CartItem> cartItems = cartItemRepository.findByUserAndStatus(user.get(), true);




        return cartItems;
    }

    @Override
    public Cart getCart(String email) {
        //Find a user with the userName passed
        Optional<UserTbl> user = userRepository.findByEmail(email);

        Cart cart = cartRepository.findByUser(user.get());

        return cart;
    }
}
