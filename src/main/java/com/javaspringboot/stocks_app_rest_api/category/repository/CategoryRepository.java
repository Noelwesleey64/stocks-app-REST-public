package com.javaspringboot.stocks_app_rest_api.category.repository;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Method to be implemented to find the category based on it's name
    Category findByCategoryName(String categoryName);

    //Mehtod to return all category objects
    List<Category> findAll();

    //defining method to be implemented for finding a Category based on it's Id
    Category findByCategoryId(Long categoryId);



}
