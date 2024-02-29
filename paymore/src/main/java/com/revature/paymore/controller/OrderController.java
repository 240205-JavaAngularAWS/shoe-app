package com.revature.paymore.controller;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {



    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<OrderDTO> registerCart(@RequestBody Order order){
        // A cart is a pending order.
        OrderDTO response = orderService.registerCart(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // add product to order
    @PutMapping("/order/item")
    public ResponseEntity<OrderDTO> addItemToCart(@RequestBody OrderItemDTO orderItemDto){
        OrderDTO response = orderService.addItemToCart(orderItemDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }





}
