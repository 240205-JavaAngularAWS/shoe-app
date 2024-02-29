package com.revature.paymore.model.dto;

import com.revature.paymore.model.Seller;
import com.revature.paymore.model.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class SellerDTO {

    private Long id;
    private String companyName;
    private String email;
    private String username;
    private String password;
    private Set<Long> productIds;


    public SellerDTO(Long id, String companyName, String email, String username, String password, Set<Long> productIds) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.productIds = productIds;
    }

    public SellerDTO(String companyName, String email, String username, String password, Set<Long> productIds) {
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.productIds = productIds;
    }
// Create SellerDTO from seller
    public SellerDTO(Seller seller) {
        this.id = seller.getId();
        this.companyName = seller.getCompanyName();
        this.email = seller.getEmail();
        this.username = seller.getUsername();
        this.productIds = seller.getProducts().stream().map(Product::getId).collect(Collectors.toSet());
    }


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
