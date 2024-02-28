package com.revature.paymore.model;

import jakarta.persistence.*;


import java.util.Objects;

import java.util.Set;


import java.util.Objects;


@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    public Seller() {

    }


    public Seller(Long id, String companyName, String email, String username, String password, Address address, Set<Product> products) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.products = products;
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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return Objects.equals(getId(), seller.getId()) && Objects.equals(getCompanyName(), seller.getCompanyName()) && Objects.equals(getEmail(), seller.getEmail()) && Objects.equals(getUsername(), seller.getUsername()) && Objects.equals(getPassword(), seller.getPassword()) && Objects.equals(getProducts(), seller.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompanyName(), getEmail(), getUsername(), getPassword(), getProducts());
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", products=" + products +
                '}';
    }


}
