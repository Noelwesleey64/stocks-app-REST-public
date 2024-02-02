package com.javaspringboot.stocks_app_rest_api.category.service;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;
import com.javaspringboot.stocks_app_rest_api.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Class to implement methods in CategorySEl;
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    //Method to addCategory object to the database
    //The method receives a category name

    @Override
    public String addCategory(String categoryName) {

        //We create a new category object and pass the categoryName gotten from the mothod to it
        Category category = new Category();
        category.setCategoryName(categoryName);

        //We then save the category to database
        Category categoryReturned = categoryRepository.save(category);

        //We then return the category below
        return "The category is: " + categoryReturned.getCategoryName();
    }
}
