package com.javaspringboot.stocks_app_rest_api.product.controller;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;
import com.javaspringboot.stocks_app_rest_api.category.repository.CategoryRepository;
import com.javaspringboot.stocks_app_rest_api.product.dto.ProductDTO;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.product.repository.ProductRepository;
import com.javaspringboot.stocks_app_rest_api.product.service.ProductService;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.repository.UserRepository;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//Rest Controller for our login
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;


    //Post method to add a product to the database
    //It takes a ProductDTO object as it's RequestBody
    //And returns a response entity containing a string
    @PostMapping(value = "product/addProduct/")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){

        //Get user connected to the username passed from productDto
        UserTbl user = userRepository.findByUserName(productDTO.getUsername());


        //Call an addProduct method for the productService object and pass it the user object and the productDTO
       String response = productService.addProduct(user, productDTO);




       //Return the String response in the body of the get request
       return ResponseEntity.status(HttpStatus.OK).body(response);



    }

    //A GetMapping method to get a list of all products in the database
    @GetMapping(value = "product/getProducts")
    public List<Product> getAllProduct(){

        //Call the getAllProduct method in the productService object in the
        return productService.getAllProduct();
    }


}
