package com.revature.paymore.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;


    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;


    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private int zipCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;



    public Address() {
    }

    public Address(Long id, String address, String city, String state, int zipCode, User user) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Address(String address, String city, String state, int zipCode, User user) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address1)) return false;
        return zipCode == address1.zipCode && Objects.equals(id, address1.id) && Objects.equals(address, address1.address) && Objects.equals(city, address1.city) && Objects.equals(state, address1.state) && Objects.equals(user, address1.user) && Objects.equals(seller, address1.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, state, zipCode, user, seller);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", user=" + user +
                ", seller=" + seller +
                '}';
    }
}
