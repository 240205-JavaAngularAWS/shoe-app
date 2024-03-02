package com.revature.paymore.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    // OK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "user_address",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id")
//    )
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Assuming 'user' is the correct field name in CreditCard class
    private Set<CreditCard> creditCards = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;


    public User() {

    }

    public User(Long id, String firstName, String lastName, String email, String username, String password, Set<Address> addresses, Set<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addresses = addresses;
        this.orders = orders;
    }


    public User(String firstName, String lastName, String email, String username, String password, Set<Address> addresses, Set<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.addresses = addresses;
        this.orders = orders;
    }


    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;

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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    // helper functions
    public void addAddress(Address address){
        this.addresses.add(address);
        address.setUser(this);

    }
    public void removeAddress(Address address){
        this.addresses.remove(address);
        address.setUser(null);

    }



    public void addOrder(Order order){
        this.orders.add(order);
        order.setUser(this);

    }
    public void removeOrder(Order order){
        this.orders.remove(order);
        order.setUser(null);

    }

    public void addCreditCard(CreditCard creditCard){
        this.creditCards.add(creditCard);
        creditCard.setUser(this);

    }
    public void removeCreditCard(CreditCard creditCard){
        this.creditCards.remove(creditCard);
        creditCard.setUser(null);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(addresses, user.addresses) && Objects.equals(creditCards, user.creditCards) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, password, addresses, creditCards, orders);
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
                ", addresses=" + addresses +
                ", creditCards=" + creditCards +
                ", orders=" + orders +
                '}';
    }
}
