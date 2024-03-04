package com.revature.paymore.model.dto;


import java.util.Objects;

public class OrderItemDTO {


    private Long id;



    private double price;

    private int quantity;


    private Long orderId;


    private Long productId;

    public OrderItemDTO(){
        // empty constructor
    }


    public OrderItemDTO(Long id, double price, int quantity, Long orderId, Long productId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderItemDTO(double price, int quantity, Long orderId, Long productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        if (!(o instanceof OrderItemDTO that)) return false;
        return Double.compare(price, that.price) == 0 && quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, quantity, orderId, productId);
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", priceTotal=" + price +
                ", quantity=" + quantity +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
