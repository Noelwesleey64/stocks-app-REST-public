package com.javaspringboot.stocks_app_rest_api.image.controller;


import com.javaspringboot.stocks_app_rest_api.image.entity.Image;
import com.javaspringboot.stocks_app_rest_api.image.repository.ImageRepository;
import com.javaspringboot.stocks_app_rest_api.image.service.ImageService;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
//Rest Controller for our login
@RestController
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;


    //Post method to upload image of the product to the database
    //Takes a multipart file and a productId to upload the image to as a pathvariable
    //The method will return a string
    @PostMapping(value = "product/{productId}/image1Upload")
    public ResponseEntity<?> uploadImage1(@PathVariable Long productId, @RequestParam("image")MultipartFile file) throws IOException {

        //Calling the method to upload the image tot the filesystem from imageService
        String uploadImage = imageService.uploadImage1ToFileSystem(file, productId);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);


    }


    //Get method for displaying the image of the product from the Db
    //It takes a filename path variable and returns the image on it's request body
    @GetMapping("product/image1Download/{fileName}")
    public ResponseEntity<?> downloadImage1(@PathVariable String fileName) throws IOException {
        //Get the image from the downloadImageFromFilesSystem method in the imageservice and pass it the filename
        byte[] imageData = imageService.downloadImage1FromFileSystem(fileName);

        //Find the image with that filename from the db so that we can retrieve it's image type and pass it to the response entity content type
        Optional<Image> image = imageRepository.findByFirstImageName(fileName);


        //Return responseEntity with the content type being the image type of the image in the database
        //And it's body being the image
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.get().getImage1_type()))
                .body(imageData);

    }

    //Post method to upload image of the product to the database
    //Takes a multipart file and a productId to upload the image to as a pathvariable
    //The method will return a string
    @PostMapping(value = "product/{productId}/image2Upload")
    public ResponseEntity<?> uploadImage2(@PathVariable Long productId, @RequestParam("image")MultipartFile file) throws IOException {
        //Calling the method to upload the image tot the filesystem from imageService
        String uploadImage = imageService.uploadImage2ToFileSystem(file, productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);


    }

    //Get method for displaying the image of the product from the Db
    //It takes a filename path variable and returns the image on it's request body
    @GetMapping("product/image2Download/{fileName}")
    public ResponseEntity<?> downloadImage2(@PathVariable String fileName) throws IOException {
        //Get the image from the downloadImageFromFilesSystem method in the imageservice and pass it the filename
        byte[] imageData = imageService.downloadImage2FromFileSystem(fileName);

        //Find the image with that filename from the db so that we can retrieve it's image type and pass it to the response entity content type
        Optional<Image> image = imageRepository.findBySecondImageName(fileName);


        //Return responseEntity with the content type being the image type of the image in the database
        //And it's body being the image
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.get().getImage2_type()))
                .body(imageData);

    }


    //Post method to upload image of the product to the database
    //Takes a multipart file and a productId to upload the image to as a pathvariable
    //The method will return a string
    @PostMapping(value = "product/{productId}/image3Upload")
    public ResponseEntity<?> uploadImage3(@PathVariable Long productId, @RequestParam("image")MultipartFile file) throws IOException {
        //Calling the method to upload the image tot the filesystem from imageService
        String uploadImage = imageService.uploadImage3ToFileSystem(file, productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);


    }

    //Get method for displaying the image of the product from the Db
    //It takes a filename path variable and returns the image on it's request body
    @GetMapping("product/image3Download/{fileName}")
    public ResponseEntity<?> downloadImage3(@PathVariable String fileName) throws IOException {
        //Get the image from the downloadImageFromFilesSystem method in the imageservice and pass it the filename
        byte[] imageData = imageService.downloadImage3FromFileSystem(fileName);

        //Find the image with that filename from the db so that we can retrieve it's image type and pass it to the response entity content type
        Optional<Image> image = imageRepository.findByThirdImageName(fileName);


        //Return responseEntity with the content type being the image type of the image in the database
        //And it's body being the image
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.get().getImage3_type()))
                .body(imageData);

    }






}
