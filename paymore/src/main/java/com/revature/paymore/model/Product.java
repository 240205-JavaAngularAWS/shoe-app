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

    @Column(name="product_name")
    private String productName;

    @Column(name="product_size")
    private double size;


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

    public Product() {

    }

    public Product(Long id, String productName, double size, double price, Color color, Gender gender, Category category, int quantity, String imageUrl, String description, Seller seller, List<Review> reviews) {
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
        this.seller = seller;
        this.reviews = reviews;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(size, product.size) == 0 && Double.compare(price, product.price) == 0 && quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && color == product.color && gender == product.gender && category == product.category && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(description, product.description) && Objects.equals(seller, product.seller) && Objects.equals(reviews, product.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, size, price, color, gender, category, quantity, imageUrl, description, seller, reviews);
    }


    @Override
    public String toString() {
        return "Product{" +
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
                ", seller=" + seller +
                ", reviews=" + reviews +
                '}';
    }


}


