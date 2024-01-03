package com.javaspringboot.stocks_app_rest_api.user.responsepayload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse {
    public String message;

    public Boolean status;

    public String userName;

    public RegisterResponse(String message, Boolean status, String userName) {
        this.message = message;
        this.status = status;
        this.userName = userName;
    }



}
