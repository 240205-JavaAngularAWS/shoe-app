package com.revature.paymore.model.dto;

import com.revature.paymore.model.User;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.Address;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    // Addresses and orders could be represented by their IDs or simplified DTOs
    private Set<Long> addressIds;
    private Set<Long> orderIds;

    public UserDTO() {
    }

    // Constructor to directly initialize fields
    public UserDTO(Long id, String firstName, String lastName, String email, String username, Set<Long> addressIds, Set<Long> orderIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.addressIds = addressIds;
        this.orderIds = orderIds;
    }

    // Constructor to convert User entity to UserDTO
    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.addressIds = user.getAddresses().stream().map(Address::getId).collect(Collectors.toSet());
        this.orderIds = user.getOrders().stream().map(Order::getId).collect(Collectors.toSet());
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

    public Set<Long> getAddressIds() {
        return addressIds;
    }

    public void setAddressIds(Set<Long> addressIds) {
        this.addressIds = addressIds;
    }

    public Set<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Long> orderIds) {
        this.orderIds = orderIds;
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
                ", addressIds=" + addressIds +
                ", orderIds=" + orderIds +
                '}';
    }
}
