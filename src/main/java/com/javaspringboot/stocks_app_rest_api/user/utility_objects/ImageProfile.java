package com.javaspringboot.stocks_app_rest_api.user.utility_objects;

import lombok.*;
import org.springframework.http.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class ImageProfile {


    private byte[] image;

    private String imageType;
}
