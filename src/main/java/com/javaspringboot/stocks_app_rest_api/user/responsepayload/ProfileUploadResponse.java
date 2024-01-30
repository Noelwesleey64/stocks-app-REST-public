package com.javaspringboot.stocks_app_rest_api.user.responsepayload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProfileUploadResponse {

    private String message;

    private Boolean status;
}
