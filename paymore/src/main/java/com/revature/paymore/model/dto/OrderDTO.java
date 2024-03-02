package com.revature.paymore.model.dto;
import com.revature.paymore.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


public class OrderDTO {

    private Long id;
    private double priceTotal;

    @NotNull(message = "Order status must not be null")
    private Status status;
    private LocalDateTime timestamp;
    private Long userId;


    // Default constructor
    public OrderDTO() {
    }

    public OrderDTO(Long id, double priceTotal, Status status, LocalDateTime timestamp, Long userId) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public OrderDTO(double priceTotal, Status status, LocalDateTime timestamp, Long userId) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
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


    // toString method for debugging purposes
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


