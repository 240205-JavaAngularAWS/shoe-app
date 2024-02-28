package com.revature.paymore.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {
    // OK

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Lob // Used for longer text
    @Column(name = "content")
    private String content;


    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Assuming reviews are linked to users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "review_date")
    private LocalDateTime reviewDate = LocalDateTime.now(); // Automatically set the review date

    public Review() {
    }

    // Constructor without id for new review creation
    public Review(String content, int rating, Product product, User user) {
        this.content = content;
        this.rating = rating;
        this.product = product;
        this.user = user;
    }

    public Review(Long id, String content, int rating, Product product, User user, LocalDateTime reviewDate) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.product = product;
        this.user = user;
        this.reviewDate = reviewDate;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return rating == review.rating && Objects.equals(id, review.id) && Objects.equals(content, review.content) && Objects.equals(product, review.product) && Objects.equals(user, review.user) && Objects.equals(reviewDate, review.reviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, rating, product, user, reviewDate);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                ", user=" + user +
                ", reviewDate=" + reviewDate +
                '}';
    }


}

