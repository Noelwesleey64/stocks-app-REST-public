package com.javaspringboot.stocks_app_rest_api.product.repository;

import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



    //Method to find product based on productName
    Product findByProductName(String productName);

    Product findByProductId(Long productId);
}
