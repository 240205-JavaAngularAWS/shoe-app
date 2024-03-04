package com.revature.paymore.model.dto;

import com.revature.paymore.model.CreditCard;
import com.revature.paymore.model.User;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;

    private Set<AddressDTO> addresses;

    private Set<CreditCardDTO> creditCards;

    private Set<OrderDTO> orders;


    public UserDTO() {
    }


    public UserDTO(Long id, String firstName, String lastName, String email, String username, Set<AddressDTO> addresses, Set<CreditCardDTO> creditCards, Set<OrderDTO> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.addresses = addresses;
        this.creditCards = creditCards;
        this.orders = orders;
    }


    public UserDTO(String firstName, String lastName, String email, String username, Set<AddressDTO> addresses, Set<CreditCardDTO> creditCards, Set<OrderDTO> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.addresses = addresses;
        this.creditCards = creditCards;
        this.orders = orders;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public Set<CreditCardDTO> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCardDTO> creditCards) {
        this.creditCards = creditCards;
    }

    public Set<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDTO> orders) {
        this.orders = orders;
    }


    // toString method for debugging purposes
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
