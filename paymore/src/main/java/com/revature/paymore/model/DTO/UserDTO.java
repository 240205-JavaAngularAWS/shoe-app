package com.revature.paymore.model.DTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDTO {

    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    private String username;


    private String password;

    //a user might have a shipping address and billing address that are different

    private Set<AddressDTO> addresses = new HashSet<>();

    private Set<OrderDTO> orders = new HashSet<>();

    private UserDTO() {

    }

    public UserDTO(Long id, String firstName, String lastName, String email, String username, String password, Set<AddressDTO> addresses, Set<OrderDTO> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addresses = addresses;
        this.orders = orders;
    }

    public UserDTO(String firstName, String lastName, String email, String username, String password, Set<AddressDTO> addresses, Set<OrderDTO> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addresses = addresses;
        this.orders = orders;
    }

    public UserDTO(String firstName, String lastName, String email, String username, Set<AddressDTO> addresses, Set<OrderDTO> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.addresses = addresses;
        this.orders = orders;
    }
    
    


    public UserDTO(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }


    public Set<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDTO> orders) {
        this.orders = orders;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(id, userDTO.id) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(addresses, userDTO.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getUsername(), getPassword(), getAddresses());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address=" + addresses +
                '}';
    }
}
