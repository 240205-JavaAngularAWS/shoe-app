package com.revature.paymore.controller;
import com.revature.paymore.model.DTO.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.service.AddressService;
import com.revature.paymore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {


    @Autowired
    private OrderService orderService;




    @PostMapping("/order")
    public ResponseEntity<?> registerOrder(@RequestBody Order order){
        OrderDTO response = orderService.registerOrder(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



}
