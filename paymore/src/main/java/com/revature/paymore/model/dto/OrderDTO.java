package com.revature.paymore.model.dto;
import com.revature.paymore.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class OrderDTO {

    private Long id;
    private double priceTotal;

    @NotNull(message = "Order status must not be null")
    private Status status;
    private LocalDateTime timestamp;
    private Long userId;

    private Set<OrderItemDTO> orderItems = new HashSet<>();


    public OrderDTO(Long id, double priceTotal, Status status, LocalDateTime timestamp, Long userId, Set<OrderItemDTO> orderItems) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    // Default constructor
    public OrderDTO() {
    }



    public OrderDTO(double priceTotal, Status status, LocalDateTime timestamp, Long userId, Set<OrderItemDTO> orderItems) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
        this.orderItems = orderItems;
    }
    public OrderDTO(double priceTotal, Status status, Long userId) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.userId = userId;
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

    public Set<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", priceTotal=" + priceTotal +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Double.compare(priceTotal, orderDTO.priceTotal) == 0 && Objects.equals(id, orderDTO.id) && status == orderDTO.status && Objects.equals(timestamp, orderDTO.timestamp) && Objects.equals(userId, orderDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceTotal, status, timestamp, userId);
    }


}


