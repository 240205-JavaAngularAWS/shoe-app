package com.revature.paymore.model.DTO;

import java.util.Set;
import java.util.Set;

public class SellerDTO {

    private Long id;
    private String companyName;
    private String email;
    private String username;
    private String password;
    private Set<ProductDTO> products;



    public SellerDTO(Long id, String companyName, String email, String username, String password, Set<ProductDTO> products) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.products = products;
    }

    public SellerDTO(Long id, String companyName, String email, String username, String password) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public SellerDTO(Long id, String companyName, String email, String username) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
    }

    public SellerDTO(String companyName, String email, String username) {
        this.companyName = companyName;
        this.email = email;
        this.username = username;
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

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
