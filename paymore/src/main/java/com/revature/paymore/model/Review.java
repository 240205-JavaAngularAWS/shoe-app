package com.revature.paymore.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Review() {
    }

    public Review(Long id, String content, Product product) {
        this.id = id;
        this.content = content;
        this.product = product;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return rating == review.rating && Objects.equals(id, review.id) && Objects.equals(content, review.content) && Objects.equals(product, review.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, rating, product);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content=" + content +
                ", products=" + product +
                '}';
    }
}
