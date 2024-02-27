package com.revature.paymore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "price")
    private double price;

    public enum Color {
        WHITE, BLACK, RED, BLUE, YELLOW, ORANGE, GREEN, PURPLE, PINK, MULTI
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    public enum Gender {
        MENS, WOMENS
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    public enum Category {

    }
    @Enumerated(EnumType.STRING)
    @Column()



    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;
}
