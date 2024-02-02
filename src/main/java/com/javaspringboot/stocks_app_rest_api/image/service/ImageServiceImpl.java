package com.javaspringboot.stocks_app_rest_api.image.service;

import com.javaspringboot.stocks_app_rest_api.image.entity.Image;
import com.javaspringboot.stocks_app_rest_api.image.repository.ImageRepository;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    private final String FOLDER_PATH="D:\\JavaSpringboot\\Stocks App REST API\\FileStorage\\ProductImages\\";

    // This method takes a MultipartFile object as a parameter and uploads it's details to databse and then transfers the file to filesystem
    // It returns a String message indicating the upload status
    // It throws an IOException if there is an error while reading the file bytes
    @Override
    public String uploadImage1ToFileSystem(MultipartFile file, Long productId) throws IOException {


        //Add the name of the file to the folderPath we want to transfer the file to
        String filePath = FOLDER_PATH + file.getOriginalFilename();


        //Create a imageUrl based on the Get Request url that displays the image
        String imageUrl = "http://localhost:8080/product/image1Download/"+ file.getOriginalFilename();

        //Find a product with the product id passed to the method
        Product product = productRepository.findByProductId(productId);


        //Get the image that contains that product
        Optional<Image> image = imageRepository.findByProduct(product);



        //Check if image that matches that product is indeed in the database and update it's contentc
        if (image.isPresent()){

            image.get().setFirstImageName(file.getOriginalFilename());
            image.get().setImage1_type(file.getContentType());
            image.get().setImage1_filePath(filePath);
            image.get().setImage1_Url(imageUrl);
            imageRepository.save(image.get());

            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }
            return null;

        }else{
            //If not create a new image match the image to the product in the database

            Image newImage = new Image();
            newImage.setFirstImageName(file.getOriginalFilename());
            newImage.setImage1_type(file.getContentType());
            newImage.setImage1_filePath(filePath);
            newImage.setImage1_Url(imageUrl);
            newImage.setProduct(product);

            imageRepository.save(newImage);

            //Transfer the file to the filepath specified
            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }

            return null;
        }







    }

    // This method takes a MultipartFile object as a parameter and uploads it's details to databse and then transfers the file to filesystem
    // It returns a String message indicating the upload status
    // It throws an IOException if there is an error while reading the file bytes
    @Override
    public String uploadImage2ToFileSystem(MultipartFile file, Long productId) throws IOException {
        //Add the name of the file to the folderPath we want to transfer the file to
        String filePath = FOLDER_PATH + file.getOriginalFilename();


        //Create a imageUrl based on the Get Request url that displays the image
        String imageUrl = "http://localhost:8080/product/image1Download/"+ file.getOriginalFilename();

        //Find a product with the product id passed to the method
        Product product = productRepository.findByProductId(productId);


        //Get the image that contains that product
        Optional<Image> image = imageRepository.findByProduct(product);



        //Check if image that matches that product is indeed in the database and update it's contentc
        if (image.isPresent()){

            image.get().setSecondImageName(file.getOriginalFilename());
            image.get().setImage2_type(file.getContentType());
            image.get().setImage2_filePath(filePath);
            image.get().setImage2_Url(imageUrl);
            imageRepository.save(image.get());

            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }
            return null;

        }else{
            //If not create a new image match the image to the product in the database

            Image newImage = new Image();
            newImage.setSecondImageName(file.getOriginalFilename());
            newImage.setImage2_type(file.getContentType());
            newImage.setImage2_filePath(filePath);
            newImage.setImage2_Url(imageUrl);
            newImage.setProduct(product);

            imageRepository.save(newImage);

            //Transfer the file to the filepath specified
            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }

            return null;
        }
    }

    @Override
    public String uploadImage3ToFileSystem(MultipartFile file, Long productId) throws IOException {
        //Add the name of the file to the folderPath we want to transfer the file to
        String filePath = FOLDER_PATH + file.getOriginalFilename();


        //Create a imageUrl based on the Get Request url that displays the image
        String imageUrl = "http://localhost:8080/product/image1Download/"+ file.getOriginalFilename();

        //Find a product with the product id passed to the method
        Product product = productRepository.findByProductId(productId);


        //Get the image that contains that product
        Optional<Image> image = imageRepository.findByProduct(product);



        //Check if image that matches that product is indeed in the database and update it's contentc
        if (image.isPresent()){

            image.get().setThirdImageName(file.getOriginalFilename());
            image.get().setImage3_type(file.getContentType());
            image.get().setImage3_filePath(filePath);
            image.get().setImage3_Url(imageUrl);
            imageRepository.save(image.get());

            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }
            return null;

        }else{
            //If not create a new image match the image to the product in the database

            Image newImage = new Image();
            newImage.setThirdImageName(file.getOriginalFilename());
            newImage.setImage3_type(file.getContentType());
            newImage.setImage3_filePath(filePath);
            newImage.setImage3_Url(imageUrl);
            newImage.setProduct(product);

            imageRepository.save(newImage);

            //Transfer the file to the filepath specified
            file.transferTo((new File(filePath)));

            if (image != null){
                return "Image successfully saved: " + imageUrl;
            }

            return null;
        }
    }


    @Override
    public byte[] downloadImage1FromFileSystem(String fileName) throws IOException {

        //Method takes file name of the image in the databse
        Optional<Image> image = imageRepository.findByFirstImageName(fileName);

        //It checks if an object with that matching filename exists in the databse
        String filePath = image.get().getImage1_filePath();

        //Then it returns the image
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    //Method to download image2 from filesystem
    @Override
    public byte[] downloadImage2FromFileSystem(String fileName) throws IOException {
        //Method takes file name of the image in the databse
        Optional<Image> image = imageRepository.findBySecondImageName(fileName);

        //It checks if an object with that matching filename exists in the databse
        String filePath = image.get().getImage2_filePath();

        //Then it returns the image
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Override
    public byte[] downloadImage3FromFileSystem(String fileName) throws IOException {
        //Method takes file name of the image in the databse
        Optional<Image> image = imageRepository.findByThirdImageName(fileName);

        //It checks if an object with that matching filename exists in the databse
        String filePath = image.get().getImage3_filePath();

        //Then it returns the image
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
