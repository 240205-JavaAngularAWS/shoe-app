package com.revature.paymore.model.dto;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddressDTO {

    private Long id;

    @NotBlank(message = "Address cannot be blank")
    private String addressText;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 2, message = "State must be a two-letter abbreviation")
    private String state;

    @NotBlank(message = "zip code cannot be blank")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid ZIP code format")
    private String zipCode;
    private Long userId;
    private Long sellerId;

    private AddressType addressType;


    public AddressDTO() {
    }



    public AddressDTO(Long id, String addressText, String city, String state, String zipCode, Long userId, Long sellerId, AddressType addressType) {
        this.id = id;
        this.addressText = addressText;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
        this.sellerId = sellerId;
        this.addressType = addressType;
    }

    public AddressDTO(String addressText, String city, String state, String zipCode, Long userId, Long sellerId, AddressType addressType) {
        this.addressText = addressText;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
        this.sellerId = sellerId;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
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
                ", address='" + addressText + '\'' +
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
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(id, that.id) && Objects.equals(addressText, that.addressText) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(userId, that.userId) && Objects.equals(sellerId, that.sellerId) && addressType == that.addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressText, city, state, zipCode, userId, sellerId, addressType);
    }
}
