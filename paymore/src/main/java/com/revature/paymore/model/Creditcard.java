package com.revature.paymore.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "creditcard")
public class Creditcard {

    private long id;

    private long cardNumber;

    private long securityCode;

    private String firstName;

    private String lastName;

    private String expirationDate;

    private long user;


}
