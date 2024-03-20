package com.javaspringboot.stocks_app_rest_api.cartItem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CartItemDto {

    private Long productId;

    private String userName;

    private int units;

    private int quantity;

    private boolean status;



}
