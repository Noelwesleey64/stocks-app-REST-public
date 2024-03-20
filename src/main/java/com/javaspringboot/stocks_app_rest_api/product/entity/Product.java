package com.javaspringboot.stocks_app_rest_api.product.entity;

import com.javaspringboot.stocks_app_rest_api.category.entity.Category;
import com.javaspringboot.stocks_app_rest_api.image.entity.Image;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

//Entity class for our products table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double price;


    @Lob
    @Column(name = "product_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "minimum_order")
    private Integer minOrder;

    @Column(name = "available_stock")
    private Integer availableStock;

    private Date createdTime;



    //Creating a many to one relationship with the usertbl
    //Many products can have one user
    @ManyToOne(
            cascade = CascadeType.ALL
    )

    //Setting the column to refer to the usertbl
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private UserTbl user;

    //Creating a many to one relationship with the usertbl
    //Many products can have one category
    @ManyToOne(

    )

    //Setting the column to refer to the Category
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryId"
    )
    private Category category;





    public Product(String productName, Double price, String description, Integer minOrder, Integer availableStock, UserTbl user, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.minOrder = minOrder;
        this.availableStock = availableStock;
        this.createdTime = this.getCreatedTime();
        this.user = user;
        this.category = category;
    }

    public Date getCreatedTime(){
        //creates a Calendar object that represents the current date and time in the default time zone and locale.
        Calendar calendar = Calendar.getInstance();

        //sets the calendar’s time value to the current system time in milliseconds.
        calendar.setTimeInMillis(new Date().getTime());

        //The calendar.getTime() method returns a Date object,
        // and the getTime() method of the Date object returns the time value in milliseconds.
        return new Date(calendar.getTime().getTime());
    }
}
