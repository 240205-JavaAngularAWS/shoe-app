package com.revature.paymore.model;

import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.model.enums.Color;
import jakarta.persistence.*;

import java.awt.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "price")
    private double price;


    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;



    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;


    @Enumerated(EnumType.STRING)
    @Column()



    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;
}
