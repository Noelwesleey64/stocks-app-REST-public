package com.javaspringboot.stocks_app_rest_api.cartItem.service;

import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.cart.repository.CartRepository;
import com.javaspringboot.stocks_app_rest_api.cartItem.dto.CartItemDto;
import com.javaspringboot.stocks_app_rest_api.cartItem.entity.CartItem;
import com.javaspringboot.stocks_app_rest_api.cartItem.repository.CartItemRepository;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.product.repository.ProductRepository;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;



    @Override
    public String AddCartItem(CartItemDto cartItemDto) {
        //Get the productId from the cartItemDto
        Long productId= cartItemDto.getProductId();

        //find a product with that productId in the database
        Product product = productRepository.findByProductId(productId);

        Optional<UserTbl> user = userRepository.findByEmail(cartItemDto.getUserName());

        //find a cartItem with that product from the database
        CartItem cartItem =  cartItemRepository.findByProductAndUser(product, user.get());

        //If it exists in the database then perform the actions below
        if (cartItem != null ){

            //Set the Quantity to add and add it to the database
            cartItem.setQuantity(cartItem.getQuantity() + 1);

            //If status is false set units to 0
            if(!cartItem.isStatus()){
                cartItem.setUnits(0);
            }

            //Add the units in the dto to the current units
            cartItem.setUnits(cartItem.getUnits() + cartItemDto.getUnits());



            cartItem.setStatus(true);

            //Save the cart item to the database with the updated quantity
            cartItemRepository.save(cartItem);

            //Get cart from the cartItem found
            Cart cart = cartItem.getCart();

            //Calculate total price of the products added
            double totalPrice = cartItemDto.getUnits() * product.getPrice();

            //add the total price to the existing total price
            cart.setTotal(cart.getTotal() + totalPrice);

            //save the cart
            cartRepository.save(cart);



            return "Product added to cart item";

        }
        else {

            String userName = cartItemDto.getUserName();


            //Get the cart from the database that matches the user
            Cart cart = cartRepository.findByUser(user.get());

            //Create a new cart item and add Dto details to it
            CartItem cartItem1 = new CartItem();

            cartItem1.setProduct(product);

            cartItem1.setUser(user.get());

            cartItem1.setQuantity(cartItemDto.getQuantity());

            cartItem1.setUnits(cartItemDto.getUnits());

            cartItem1.setStatus(true);

            cartItem1.setCart(cart);

            cartItemRepository.save(cartItem1);

            //Calculate the total price of the product by including the units
            double totalPrice = cartItem1.getUnits() * cartItem1.getQuantity() * product.getPrice();

            //Set the total field of the cart to our total price
            cart.setTotal(cart.getTotal() + totalPrice);


            cartRepository.save(cart);



            return "Cart Item Added to cart";




        }


    }

    @Override
    public String removeCartItem(Long productId, String userName) {
        //Find a product from the database with that product Id
        Product product = productRepository.findByProductId(productId);

        Optional<UserTbl> user = userRepository.findByEmail(userName);


        //Find cart Item from database with that product
        CartItem cartItem = cartItemRepository.findByProductAndUser(product, user.get());


        //Set the status of that cartItem to false, meaning it is disabled
        cartItem.setStatus(false);

        //Fetching our cart
        Cart cart = cartItem.getCart();

        double totalPrice = cartItem.getUnits() * product.getPrice();

        cart.setTotal(cart.getTotal() - totalPrice);


        cartItemRepository.save(cartItem);

        cartRepository.save(cart);
        return "Cart Item Removed";
    }

    @Override
    public String removeUnitsFromCartItem(Long productId, String userName, int units) {
        //Find a product from the database with that product Id
        Product product = productRepository.findByProductId(productId);

        Optional<UserTbl> user = userRepository.findByEmail(userName);


        //Find cart Item from database with that product
        CartItem cartItem = cartItemRepository.findByProductAndUser(product, user.get());

        //Subtract units from received from it
        cartItem.setUnits(cartItem.getUnits() - units);


        //Fetching our cart
        Cart cart = cartItem.getCart();

        double totalPrice = units * product.getPrice();

        cart.setTotal(cart.getTotal() - totalPrice);


        cartItemRepository.save(cartItem);

        cartRepository.save(cart);
        return "Cart Item Removed";
    }

    @Override
    public String addUnitsToCartItem(Long productId, String userName, int units) {
        //Find a product from the database with that product Id
        Product product = productRepository.findByProductId(productId);

        Optional<UserTbl> user = userRepository.findByEmail(userName);


        //Find cart Item from database with that product
        CartItem cartItem = cartItemRepository.findByProductAndUser(product, user.get());

        //add units from received to it
        cartItem.setUnits(cartItem.getUnits() + units);


        //Fetching our cart
        Cart cart = cartItem.getCart();

        double totalPrice = units * product.getPrice();

        cart.setTotal(cart.getTotal() + totalPrice);


        cartItemRepository.save(cartItem);

        cartRepository.save(cart);
        return "Units Added to Cart Item";
    }
}
