package com.revature.paymore.model.dto;

public class ReviewDTO {

    private Long id;
    private String content;
    private int rating;
    private Long productId; // Only the product ID is included to keep the DTO simple

    // Default constructor
    public ReviewDTO() {
    }

    // Constructor using fields
    public ReviewDTO(Long id, String content, int rating, Long productId) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.productId = productId;
    }

    public ReviewDTO(String content, int rating, Long productId) {
        this.content = content;
        this.rating = rating;
        this.productId = productId;
    }

    // Constructor that converts a Review entity to ReviewDTO

    public ReviewDTO(int rating, Long productId) {
        this.rating = rating;
        this.productId = productId;
    }

    // Getters and Setters
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", productId=" + productId +
                '}';
    }
}
