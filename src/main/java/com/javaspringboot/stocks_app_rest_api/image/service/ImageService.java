package com.javaspringboot.stocks_app_rest_api.image.service;

import com.javaspringboot.stocks_app_rest_api.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {

    public String uploadImage1ToFileSystem(MultipartFile file, Long productId) throws IOException;

    public String uploadImage2ToFileSystem(MultipartFile file, Long productId) throws IOException;

    public String uploadImage3ToFileSystem(MultipartFile file, Long productId) throws IOException;

    public byte[] downloadImage1FromFileSystem(String fileName) throws IOException;

    public byte[] downloadImage2FromFileSystem(String fileName) throws IOException;

    public byte[] downloadImage3FromFileSystem(String fileName) throws IOException;

    //Method to get the image object related to the product, it takes productId and returns Image
    public Optional<Image> getProductImage(Long productId);




}
