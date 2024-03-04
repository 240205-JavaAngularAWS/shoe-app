package com.revature.paymore.model.dto;

import com.revature.paymore.model.Address;

// Assuming you have a simplified AddressDTO, you might want to use it instead of the Address entity
// import com.revature.paymore.model.dto.AddressDTO;

import java.util.Objects;
import java.util.Set;

public class SellerDTO {
    private Long id;
    private String companyName;
    private String email;
    private String username;

    private AddressDTO address;

    private Set<ProductDTO> products;



    public SellerDTO() {
    }

    public SellerDTO(String companyName, String email, String username, AddressDTO address, Set<ProductDTO> products) {
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.address = address;
        this.products = products;
    }



    public SellerDTO(Long id, String companyName, String email, String username, AddressDTO address, Set<ProductDTO> products) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.address = address;
        this.products = products;
    }

    // Standard getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellerDTO sellerDto)) return false;
        return Objects.equals(id, sellerDto.id) &&
                Objects.equals(companyName, sellerDto.companyName) &&
                Objects.equals(email, sellerDto.email) &&
                Objects.equals(username, sellerDto.username) &&
                Objects.equals(address, sellerDto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, email, username, address);
    }

    @Override
    public String toString() {
        return "SellerDTO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", address=" + address +
                '}';
    }


}