package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.service.OrderService;
import com.revature.paymore.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {



    private final OrderService orderService;
    private final ResponseHelperService responseHelperService;


    @Autowired
    public OrderController(OrderService orderService, ResponseHelperService responseHelperService) {
        this.orderService = orderService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/orders")
    public ResponseEntity<?> registerCart(@Valid @RequestBody OrderDTO orderDto, BindingResult bindingResult){
        // using DTO, so we don't have to paste the entire user object.
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        // A cart is a pending order.
        OrderDTO response = orderService.registerCart(orderDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // add product to order
    @PutMapping("/orders")
    public ResponseEntity<?> addItemToCart(@Valid @RequestBody OrderItemDTO orderItemDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        OrderDTO response = orderService.addItemToCart(orderItemDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @DeleteMapping("/orders/items/{orderItemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable long orderItemId){
        OrderDTO response = orderService.removeItemFromCart(orderItemId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }







    @PostMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> submitCartAsOrder(@PathVariable long orderId){

        // A cart is a pending order.
        OrderDTO response = orderService.submitOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    @GetMapping("/orders/users/{userId}")
    public ResponseEntity<List<OrderDTO>> findOrdersByUser(@PathVariable long userId){
        // A cart is a pending order.
        List<OrderDTO> response = orderService.findOrdersByUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/orders/items/{productId}")
    public ResponseEntity<List<OrderDTO>> findOrderItemsByProduct(@PathVariable long productId){
        // A cart is a pending order.
        List<OrderDTO> response = orderService.findOrdersByUser(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long orderId){

        boolean deleted = orderService.deleteOrder(orderId);
        if(!deleted){
            throw new BadRequestException("Product with id " + orderId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Product Successfully Deleted\"}", HttpStatus.OK);
    }






}
