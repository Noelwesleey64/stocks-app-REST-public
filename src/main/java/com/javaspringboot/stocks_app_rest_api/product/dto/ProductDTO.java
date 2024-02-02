package com.javaspringboot.stocks_app_rest_api.product.dto;

import jakarta.persistence.Column;
import lombok.*;

//Class whose object will be used to send the get request
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {


    private String username;

    private String productName;


    private Double price;


    private String description;


    private Integer minOrder;


    private Integer availableStock;

    private Long categoryId;
}
