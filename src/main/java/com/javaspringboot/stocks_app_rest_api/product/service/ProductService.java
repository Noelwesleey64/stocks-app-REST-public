package com.javaspringboot.stocks_app_rest_api.product.service;

import com.javaspringboot.stocks_app_rest_api.product.dto.ProductDTO;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;

import java.util.List;

public interface ProductService {

    //declaring method to addProduct that returns a string takes UserTbl object and ProductDTO object
    String addProduct(UserTbl user, ProductDTO productdto);

    //declaring method to be implemented that gets all products from database and returns a string
    List<Product> getAllProduct();

    //declaring method to be implemented for getting a list of products based on their categories
    List<Product> getProductOnCategory(Long categoryId);

    Product getProduct(Long productId);





}
