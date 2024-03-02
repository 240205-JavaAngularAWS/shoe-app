package com.revature.paymore.model.dto;

import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Color;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.model.enums.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    private double size;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Must select a color")
    private Color color;

    @NotNull(message = "Must select a gender")
    private Gender gender;

    @NotNull(message = "Must select a category")
    private Category category;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private int quantity;

    @URL(message = "Invalid image URL")
    @Pattern(regexp = ".*\\.(jpg|png|jpeg|gif)$", message="Image URL must end with a valid image extension (.jpg, .png, .jpeg, .gif)")
    private String imageUrl;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private Long sellerId;

    // Constructors
    public ProductDTO() {
        // empty no args constructor
    }



    public ProductDTO(Long id, String productName, double size, double price, Color color, Gender gender, Category category, int quantity, String imageUrl, String description, Long sellerId) {
        this.id = id;
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.description = description;
        this.sellerId = sellerId;
    }

    public ProductDTO(String productName, double size, double price, Color color, Gender gender, Category category, int quantity, String imageUrl, String description, Long sellerId) {
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.description = description;
        this.sellerId = sellerId;
    }

    // Constructor that converts a Product entity into a Product DTO

    public ProductDTO(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.size = product.getSize();
        this.price = product.getPrice();
        this.color = product.getColor();
        this.gender = product.getGender();
        this.category = product.getCategory();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        this.description = product.getDescription();

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    // toString method for debugging purposes

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", color=" + color +
                ", gender=" + gender +
                ", category=" + category +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", sellerId=" + sellerId +
                '}';
    }
}