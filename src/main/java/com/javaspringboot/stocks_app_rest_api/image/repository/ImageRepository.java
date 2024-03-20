package com.javaspringboot.stocks_app_rest_api.image.repository;

import com.javaspringboot.stocks_app_rest_api.image.entity.Image;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByFirstImageName(String image1_Name);
    Optional<Image> findBySecondImageName(String image2_Name);

    Optional<Image> findByThirdImageName(String image3_Name);

    Optional<Image> findByProduct(Product product);







}
