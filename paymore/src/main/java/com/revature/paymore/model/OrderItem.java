package com.revature.paymore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.util.Objects;


@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;


    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(name = "price_total")
    private double price;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private int quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @Column(name = "product_id")
    private Long productId;

    public OrderItem(){
        // empty constructor
    }


    public OrderItem(Long id, double price, int quantity, Order order, Long productId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.productId = productId;
    }

    public OrderItem(double price, int quantity, Order order, Long productId) {
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.productId = productId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Double.compare(price, orderItem.price) == 0 && quantity == orderItem.quantity && Objects.equals(id, orderItem.id) && Objects.equals(order, orderItem.order) && Objects.equals(productId, orderItem.productId);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, priceTotal, quantity, order, productId);
//    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", priceTotal=" + price +
                ", quantity=" + quantity +
                ", order=" + order +
                ", productId=" + productId +
                '}';
    }
}
