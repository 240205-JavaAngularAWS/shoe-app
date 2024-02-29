package com.revature.paymore.model;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.model.enums.Color;
import com.revature.paymore.model.enums.Category;
import jakarta.persistence.*;



import java.util.Objects;

import java.util.List;





@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "price")
    private double price;


    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;



    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product() {
    }

    public Product(Long id, double price, Color color, Gender gender, Category category, int quantity, String imageUrl, String description, Seller seller, List<Review> reviews, List<Order> orders) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.description = description;
        this.seller = seller;
        this.reviews = reviews;
        this.orders = orders;
    }


    public Product(double price, Color color, Gender gender, Category category, int quantity, String imageUrl, String description, Seller seller, List<Review> reviews, List<Order> orders) {
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.description = description;
        this.seller = seller;
        this.reviews = reviews;
        this.orders = orders;
    }

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Double.compare(getPrice(), product.getPrice()) == 0 && getQuantity() == product.getQuantity() && Objects.equals(getId(), product.getId()) && getColor() == product.getColor() && getGender() == product.getGender() && getCategory() == product.getCategory() && Objects.equals(getImageUrl(), product.getImageUrl()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getSeller(), product.getSeller()) && Objects.equals(getReviews(), product.getReviews()) && Objects.equals(getOrders(), product.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getColor(), getGender(), getCategory(), getQuantity(), getImageUrl(), getDescription(), getSeller(), getReviews(), getOrders());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", color=" + color +
                ", gender=" + gender +
                ", category=" + category +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", seller=" + seller +
                ", reviews=" + reviews +
                ", orders=" + orders +
                '}';
    }
}
