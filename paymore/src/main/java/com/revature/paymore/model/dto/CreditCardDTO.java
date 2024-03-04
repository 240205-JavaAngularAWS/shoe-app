package com.revature.paymore.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class CreditCardDTO {

    private Long id;

    @NotBlank(message = "Card number cannot be blank")
    @Pattern(regexp = "^(\\d{13,19})$", message = "Invalid card number format")
    private String cardNumber;

    @NotBlank(message =  "Security code cannot be blank")
    @Pattern(regexp = "^(\\d{3,4})$", message = "Invalid security code format")
    private String securityCode;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Expiration date cannot be blank")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Invalid expiration date format")
    private String expirationDate;


    private Long userId; // Only the user ID to reference the user, not the entire User object

    // Default constructor
    public CreditCardDTO() {
    }

    // Constructor that converts a CreditCard entity to CreditCardDTO


    public CreditCardDTO(String cardNumber, String securityCode, String firstName, String lastName, String expirationDate, Long userId) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public CreditCardDTO(Long id, String cardNumber, String securityCode, String firstName, String lastName, String expirationDate, Long userId) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    // Getters and Setters
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "CreditCardDTO{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCardDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(securityCode, that.securityCode) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, securityCode, firstName, lastName, expirationDate, userId);
    }
}

