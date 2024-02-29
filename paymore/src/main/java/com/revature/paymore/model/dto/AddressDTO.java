package com.revature.paymore.model.dto;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.enums.AddressType;

import java.util.Objects;

public class AddressDTO {

    private Long id;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private Long userId;
    private Long sellerId;

    private AddressType addressType;


    public AddressDTO() {
    }

    public AddressDTO(Long id, String address, String city, String state, int zipCode, Long userId, Long sellerId) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
        this.sellerId = sellerId;
    }

    // Constructor that accepts an Address entity
    public AddressDTO(Address address) {
        this.id = address.getId();
        this.address = address.getAddressText();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.userId = address.getUser() != null ? address.getUser().getId() : null;
        this.sellerId = address.getSeller() != null ? address.getSeller().getId() : null;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", userId=" + userId +
                ", sellerId=" + sellerId +
                ", addressType=" + addressType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO that)) return false;
        return zipCode == that.zipCode && Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(userId, that.userId) && Objects.equals(sellerId, that.sellerId) && addressType == that.addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, state, zipCode, userId, sellerId, addressType);
    }
}
