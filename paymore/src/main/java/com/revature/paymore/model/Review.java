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
    private double content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;


    public Review() {
    }

    public Review(Long id, double content, Product products) {
        this.id = id;
        this.content = content;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Double.compare(content, review.content) == 0 && Objects.equals(id, review.id) && Objects.equals(products, review.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, products);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content=" + content +
                ", products=" + products +
                '}';
    }
}
