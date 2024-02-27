package com.revature.paymore.model;

import com.revature.paymore.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "price_total")
    private double priceTotal;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public Order() {
    }

    public Order(Long id, double priceTotal, Status status, LocalDateTime timestamp, User user, Set<Product> products) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.products = products;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (o == null || getClass() != o.getClass()) return false;
        Order orders = (Order) o;
        return Double.compare(priceTotal, orders.priceTotal) == 0 && Objects.equals(id, orders.id) && status == orders.status && Objects.equals(timestamp, orders.timestamp) && Objects.equals(user, orders.user) && Objects.equals(products, orders.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceTotal, status, timestamp, user, products);
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", priceTotal=" + priceTotal +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
