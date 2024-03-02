package com.revature.paymore.controller;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.service.OrderService;
import com.revature.paymore.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {



    private final OrderService orderService;
    private final ResponseHelperService responseHelperService;


    @Autowired
    public OrderController(OrderService orderService, ResponseHelperService responseHelperService) {
        this.orderService = orderService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/order")
    public ResponseEntity<?> registerCart(@Valid @RequestBody Order order, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        // A cart is a pending order.
        OrderDTO response = orderService.registerCart(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // add product to order
    @PutMapping("/order/item")
    public ResponseEntity<?> addItemToCart(@Valid @RequestBody OrderItemDTO orderItemDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        OrderDTO response = orderService.addItemToCart(orderItemDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> submitCartAsOrder(@PathVariable long orderId){

        // A cart is a pending order.
        OrderDTO response = orderService.submitOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }





}
