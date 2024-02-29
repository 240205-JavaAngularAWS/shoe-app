package com.revature.paymore.model;


import com.revature.paymore.model.enums.AddressType;
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
    private String addressText;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;



    public Address() {
    }

    public Address(Long id, String addressText, String city, String state, int zipCode, User user, AddressType addressType) {
        this.id = id;
        this.addressText = addressText;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
        this.addressType = addressType;
    }

    public Address(String addressText, String city, String state, int zipCode, User user, AddressType addressType) {
        this.addressText = addressText;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
        this.addressType = addressType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
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

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address1)) return false;
        return zipCode == address1.zipCode && Objects.equals(id, address1.id) && Objects.equals(addressText, address1.addressText) && Objects.equals(city, address1.city) && Objects.equals(state, address1.state) && Objects.equals(user, address1.user) && Objects.equals(seller, address1.seller) && addressType == address1.addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressText, city, state, zipCode, user, seller, addressType);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + addressText + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", user=" + user +
                ", seller=" + seller +
                ", addressType=" + addressType +
                '}';
    }
}
