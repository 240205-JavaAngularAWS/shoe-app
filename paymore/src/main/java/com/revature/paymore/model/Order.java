package com.revature.paymore.model;

import com.revature.paymore.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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

    @Column(name = "price_total")
    private double priceTotal;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "order_product", // The join table name
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Order(User user, Set<OrderItem> orderItems) {
        this.user = user;
        this.status = Status.PENDING;
        this.orderItems = orderItems;
    }

    public Order(Long id, double priceTotal, Status status, LocalDateTime timestamp, User user, Set<OrderItem> orderItems) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order(Status status, LocalDateTime timestamp, User user, Set<OrderItem> orderItems) {
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
        this.orderItems = orderItems;
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
    public int hashCode() {
        return Objects.hash(id, priceTotal, status, timestamp, user, orderItems);
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", priceTotal=" + priceTotal +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", products=" + orderItems +
                '}';
    }
}
