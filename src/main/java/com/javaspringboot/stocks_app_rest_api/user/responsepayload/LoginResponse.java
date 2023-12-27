package com.javaspringboot.stocks_app_rest_api.user.responsepayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {
    String message;
    Boolean status;

}
