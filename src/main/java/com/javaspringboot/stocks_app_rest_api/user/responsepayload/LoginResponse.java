package com.javaspringboot.stocks_app_rest_api.user.responsepayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class LoginResponse {
    public String message;

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public  Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



}
