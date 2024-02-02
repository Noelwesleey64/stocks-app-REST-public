package com.javaspringboot.stocks_app_rest_api.category.controller;

import com.javaspringboot.stocks_app_rest_api.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//Rest Controller for our login
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Post method to add a category to the Db, it takes a String named categoryName as it's parameter
    @PostMapping(value = "category/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody String categoryName){
        //call addCategory method from category service and pass categoryName gotten from the request to it
        String response = categoryService.addCategory(categoryName);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
