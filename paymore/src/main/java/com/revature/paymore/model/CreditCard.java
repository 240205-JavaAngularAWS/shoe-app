package com.revature.paymore.model;


import jakarta.persistence.*;

@Entity
@Table(name = "creditcard")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditcard_id")
    private Long id;


    @Column(name = "card_number")
    private String cardNumber;


    @Column(name = "security_code")
    private long securityCode;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "expiration_date")
    private String expirationDate;

    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard() {
    }

    public CreditCard(Long id, String cardNumber, String firstName, String lastName, String expirationDate, User user) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(long securityCode) {
        this.securityCode = securityCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
