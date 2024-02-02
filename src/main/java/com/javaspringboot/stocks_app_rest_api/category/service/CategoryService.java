package com.javaspringboot.stocks_app_rest_api.category.service;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;

public interface CategoryService {

    //Defining a method to be implemented that adds a category to the database and returns a string
    //It takes Category Name
    String addCategory(String categoryName);
}
