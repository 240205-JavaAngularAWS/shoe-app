package com.revature.paymore.model.dto;

import com.revature.paymore.model.CreditCard;

import java.util.Objects;

public class CreditCardDTO {

    private Long id;
    private String cardNumber;
    // Omitting security code in the DTO
    private String securityCode;
    private String firstName;
    private String lastName;
    private String expirationDate;
    private Long userId; // Only the user ID to reference the user, not the entire User object

    // Default constructor
    public CreditCardDTO() {
    }

    // Constructor that converts a CreditCard entity to CreditCardDTO
    public CreditCardDTO(CreditCard creditCard) {
        this.id = creditCard.getId();
        this.cardNumber = creditCard.getCardNumber();
        this.firstName = creditCard.getFirstName();
        this.lastName = creditCard.getLastName();
        this.expirationDate = creditCard.getExpirationDate();
        this.userId = creditCard.getUser() != null ? creditCard.getUser().getId() : null;
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

