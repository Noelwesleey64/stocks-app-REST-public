package com.javaspringboot.stocks_app_rest_api.product.service;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;
import com.javaspringboot.stocks_app_rest_api.category.repository.CategoryRepository;
import com.javaspringboot.stocks_app_rest_api.product.dto.ProductDTO;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.product.repository.ProductRepository;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //Implementing a method in ProductService interface that adds a product to the database
    //The method takes UserTbl object and also ProductDTO object
    @Override
    public String addProduct(UserTbl user, ProductDTO productdto) {

        //get a catergory object in database with the CategoryId passed from the categoryId attribute in productDTO
        Category category = categoryRepository.findByCategoryId(productdto.getCategoryId());

        //Create a new product object then map the productDTO values to it's attributes
        Product product = new Product(productdto.getProductName(), productdto.getPrice(), productdto.getDescription(), productdto.getMinOrder(), productdto.getAvailableStock(), user, category);

        //Set the user attribute of the product object to the user object passed from the method
        product.setUser(user);

        //Method to save the product to the database
        productRepository.save(product);

        //Get a product object from the database with the productName passed from the dto
        Product product2 = productRepository.findByProductName(productdto.getProductName());

        //Return a string plus the productName gotten from the database
        return "Product Name is: "+ product2.getProductName();
    }

    //Return a list of products using the findAll method in productRepository Interface object
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
