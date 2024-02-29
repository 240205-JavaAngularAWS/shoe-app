package com.revature.paymore.model.dto;

import com.revature.paymore.model.Order;
import com.revature.paymore.model.OrderItem;
import com.revature.paymore.model.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTO {

    private Long id;
    private double priceTotal;
    private Status status;
    private LocalDateTime timestamp;
    private Long userId;
    private Set<Long> orderItemIds; // product IDs included in the order

    // Default constructor
    public OrderDTO() {
    }

    // Constructor that converts an Order entity to OrderDTO
    public OrderDTO(Order order) {
        this.id = order.getId();
        this.priceTotal = order.getPriceTotal();
        this.status = order.getStatus();
        this.timestamp = order.getTimestamp();
        this.userId = order.getUser() != null ? order.getUser().getId() : null; // Safely handle null
        this.orderItemIds = order.getOrderItems() != null ? order.getOrderItems().stream().map(OrderItem::getId).collect(Collectors.toSet()) : null;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(Set<Long> productIds) {
        this.orderItemIds = productIds;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", priceTotal=" + priceTotal +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                ", orderItemIds=" + orderItemIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Double.compare(priceTotal, orderDTO.priceTotal) == 0 && Objects.equals(id, orderDTO.id) && status == orderDTO.status && Objects.equals(timestamp, orderDTO.timestamp) && Objects.equals(userId, orderDTO.userId) && Objects.equals(orderItemIds, orderDTO.orderItemIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceTotal, status, timestamp, userId, orderItemIds);
    }
}


