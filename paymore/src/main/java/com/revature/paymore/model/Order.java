package com.revature.paymore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.paymore.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    // OK

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    //    @DecimalMin(value = "0.01", message = "Price total must be greater than 0")
    @Column(name = "price_total")
    private double priceTotal;


    @NotNull(message = "Order status must not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private Set<OrderItem> orderItems = new HashSet<>();



    public Order(Long id, double priceTotal, Status status, LocalDateTime timestamp, User user, Set<OrderItem> orderItems) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order(double priceTotal, Status status, LocalDateTime timestamp, User user, Set<OrderItem> orderItems) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.orderItems = orderItems;
    }
    public Order(double priceTotal, Status status, LocalDateTime timestamp, User user) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Order() {
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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void removeOrderItem(OrderItem orderItem){
        this.orderItems.remove(orderItem);
        orderItem.setOrder(null);

    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order orders = (Order) o;
        return Double.compare(priceTotal, orders.priceTotal) == 0 && Objects.equals(id, orders.id) && status == orders.status && Objects.equals(timestamp, orders.timestamp) && Objects.equals(user, orders.user) && Objects.equals(orderItems, orders.orderItems);
    }




    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", priceTotal=" + priceTotal +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", products=" + orderItems +
                '}';
    }
}

