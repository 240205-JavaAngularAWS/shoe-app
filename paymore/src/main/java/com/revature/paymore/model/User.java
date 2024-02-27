package com.revature.paymore.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    //a user might have a shipping address and billing address that are different

    private Address address;
    @Column(name = "creditcards")
    private Set<Creditcard> creditcards = new HashSet<>();

    private List<Order> orders;

    private User() {

    }


}
