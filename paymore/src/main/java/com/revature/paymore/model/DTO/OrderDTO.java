package com.revature.paymore.model.DTO;


import com.revature.paymore.model.enums.Status;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderDTO {


    private Long id;
        private double priceTotal;
        private Status status;
        private LocalDateTime timestamp;
        private Long userId;
        private Set<ProductDTO> products = new HashSet<>();


        public OrderDTO(){

        }

    public OrderDTO(Long id, double priceTotal, Status status, LocalDateTime timestamp, Long userId, Set<ProductDTO> products) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
        this.products = products;
    }

    public OrderDTO(double priceTotal, Status status, LocalDateTime timestamp, Long userId) {
        this.priceTotal = priceTotal;
        this.status = status;
        this.timestamp = timestamp;
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

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }




    @Override
    public int hashCode() {
            return Objects.hash(id, priceTotal, status, timestamp, userId, products);
        }



    }


