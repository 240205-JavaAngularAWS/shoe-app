package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {


    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<OrderDTO> registerCart(@RequestBody Order order) {
        // A cart is a pending order.
        OrderDTO response = orderService.registerCart(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // add product to order
    @PutMapping("/order/item")
    public ResponseEntity<OrderDTO> addItemToCart(@RequestBody OrderItemDTO orderItemDto) {
        OrderDTO response = orderService.addItemToCart(orderItemDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    // removing order by id from DB
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long orderId) {

        boolean deleted = orderService.deleteOrder(orderId);
        if (!deleted) {
            throw new BadRequestException("Order with id " + orderId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Order Successfully Deleted\"}", HttpStatus.OK);
    }


    // removing order from cart by its Id
    @DeleteMapping("/order/{orderItemId}")
    public ResponseEntity<String> deleteOrderItemById(@PathVariable Long orderItemId) {

        boolean deleted = orderService.removeItemFromCart(orderItemId);
        if (!deleted) {
            throw new BadRequestException("OrderItem with id " + orderItemId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"OrderItem Successfully Deleted\"}", HttpStatus.OK);
    }


    // viewing order history
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }


    @GetMapping("/orders/seller/{id}")
    public ResponseEntity<List<OrderDTO>> findPlacedOrdersByUserId(@PathVariable Long userId){

        List<OrderDTO> placeOrders = orderService.findPlacedOrdersByUserId(userId);
        return new ResponseEntity<>(placeOrders, HttpStatus.OK);
    }



}
