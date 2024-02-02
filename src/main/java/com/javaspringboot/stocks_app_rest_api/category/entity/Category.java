package com.javaspringboot.stocks_app_rest_api.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Table for storing images in the database
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "category_tbl")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

}
