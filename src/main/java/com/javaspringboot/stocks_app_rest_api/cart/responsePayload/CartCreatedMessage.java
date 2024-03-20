package com.javaspringboot.stocks_app_rest_api.cart.responsePayload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CartCreatedMessage {

    private String message;

    private Boolean status;

}
