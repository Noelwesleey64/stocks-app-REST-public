package com.javaspringboot.stocks_app_rest_api.cart.entity;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )

    @Column(name = "cart_id")
    private long cartId;


    private double total;

    //OneToOne relationship between cart and user
    @OneToOne()

    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private UserTbl user;






}
