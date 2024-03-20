package com.javaspringboot.stocks_app_rest_api.image.entity;


import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )


    private Long imageId;

    private String image1_Url;

    private String image2_Url;

    private String image3_Url;

    @Column(name = "first_image_name")
    private String firstImageName;

    @Column(name = "second_image_name")
    private String secondImageName;

    @Column(name = "third_image_name")
    private String thirdImageName;

    private String image1_type;

    private String image2_type;

    private String image3_type;


    private String image1_filePath;

    private String image2_filePath;

    private String image3_filePath;


    @OneToOne(

    )

    @JoinColumn(
            //the name of the column to be created in Product Table
            name = "product_id",

            //the name of the column from Image entity that Product Reference to while creating relationship
            referencedColumnName = "product_id"
    )
    private Product product;

}
