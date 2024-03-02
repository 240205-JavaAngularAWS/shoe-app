package com.revature.paymore.model;
import com.revature.paymore.model.enums.Gender;
import com.revature.paymore.model.enums.Color;
import com.revature.paymore.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;


import java.util.Objects;

import java.util.List;



@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Column(name="product_name")
    private String productName;

    @Column(name="product_size")
    private double size;


    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(name = "price")
    private double price;

    @NotNull(message = "Must select a color")
    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @NotNull(message = "Must select a gender")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull(message = "Must select a category")
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private int quantity;

    @URL(message = "Invalid image URL")
    @Pattern(regexp = ".*\\.(jpg|png|jpeg|gif)$", message="Image URL must end with a valid image extension (.jpg, .png, .jpeg, .gif)")
    @Column(name = "image")
    private String imageUrl;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description should be between 10 and 500 characters")
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

    public void addReview(Review review){
        this.reviews.add(review);
        review.setProduct(this);

    }
    public void removeReview(Review review){
        this.reviews.remove(review);
        review.setProduct(null);

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


