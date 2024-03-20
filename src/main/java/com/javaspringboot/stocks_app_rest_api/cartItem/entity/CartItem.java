package com.javaspringboot.stocks_app_rest_api.cartItem.entity;

import com.javaspringboot.stocks_app_rest_api.cart.entity.Cart;
import com.javaspringboot.stocks_app_rest_api.product.entity.Product;
import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

    @Id
    @SequenceGenerator(
            name = "cart_item_sequence",
            sequenceName = "cart_item_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_sequence"
    )
    @Column( name = "cart_item_id")
    private Long cartItemId;

    //Should be many to many
    @OneToOne()
    @JoinColumn(
            //the name of the column to be created in CartItemTbl
            name = "product_id",

            //the name of the column from Product entity that the CartItemTbl Should Reference to while creating relationship
            referencedColumnName = "product_id"
    )

    private Product product;

    @Column(name = "quantity")
    private int Quantity;

    @ManyToOne()
    @JoinColumn(
            //the name of the column to be created in CartItemTbl
            name = "user_id",

            //the name of the column from UserTbl entity that should Reference to while creating relationship
            referencedColumnName = "user_id"
    )

    private UserTbl user;

    @ManyToOne()
    @JoinColumn(
            //the name of the column to be created in CartItemTbl
            name = "cart_id",

            //the name of the column from UserTbl entity that should Reference to while creating relationship
            referencedColumnName = "cart_id"
    )
    private Cart cart;

    @Column(name = "status")
    private boolean status;


    private int units;







}
