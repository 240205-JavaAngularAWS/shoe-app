package com.revature.paymore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "creditcard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditcard_id")
    private Long id;

    @NotBlank(message = "Card number cannot be blank")
    @Pattern(regexp = "^(\\d{13,19})$", message = "Invalid card number format")
    @Column(name = "card_number")
    private String cardNumber;

    @NotBlank(message = "Security code cannot be blank")
    @Pattern(regexp = "^(\\d{3,4})$", message = "Invalid security code format")
    @Column(name = "security_code")
    private String securityCode;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Expiration date cannot be blank")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Invalid expiration date format")
    @Column(name = "expiration_date")
    private String expirationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public CreditCard(Long id, String cardNumber, String securityCode, String firstName, String lastName, String expirationDate, User user, Address address) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.user = user;
        this.address = address;
    }

    public CreditCard(String cardNumber, String securityCode, String firstName, String lastName, String expirationDate, User user, Address address) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.user = user;
        this.address = address;
    }

    // Getters and setters
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

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



}
