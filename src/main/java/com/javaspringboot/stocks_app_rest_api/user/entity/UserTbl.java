package com.javaspringboot.stocks_app_rest_api.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//Adding annotations to the class
@Entity

//lambbok will manage the all of our constructors

@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(
        //name of the table
        name = "user_tbl",

        //enforcing integrity for the email, phone and username to be unique
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email"
                ),

                 @UniqueConstraint(
                         name = "phone_no_unique",
                         columnNames = "phone_no"
                 ),

                @UniqueConstraint(
                        name = "username_unique",
                        columnNames = "user_name"
                ),
        }

)


public class UserTbl {

    //Creating  a generator to generate Unique Id starting from 1
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"

    )

    @Column(name = "user_id")
    private Long userId;



    @Column(name = "user_name", length = 255, nullable = false)
    private String userName;


    @Column(name = "first_name", length = 255)
    private String firstName;


    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "email", length = 255,
    nullable = false)
    private  String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Setter
    @Column(name = "gender")
    private String gender;

    @Setter
    @Column(name = "image_url")
    private String imagePath;

    @Column(name = "password" )
    private String password;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "is_enabled")
    private boolean isEnabled = false;

    @Column(name = "role")
    private String role;


}
