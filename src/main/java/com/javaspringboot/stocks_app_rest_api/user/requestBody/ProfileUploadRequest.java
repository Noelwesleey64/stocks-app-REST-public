package com.javaspringboot.stocks_app_rest_api.user.requestBody;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class ProfileUploadRequest {

    private MultipartFile file;

    private String userName;

}
