package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.enums.Status;
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


    @GetMapping("/orders/all")
    public ResponseEntity<List<OrderDTO>> findAllOrders(){

        List<OrderDTO> response = orderService.findAllOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






    @PostMapping("/orders/submit")
    public ResponseEntity<OrderDTO> submitCartAsOrder(@RequestParam(name = "orderId") Long orderId){
        OrderDTO response = orderService.submitOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    @GetMapping("/orders/users/filterBy")
    public ResponseEntity<List<OrderDTO>> findOrdersByUserId(@RequestParam(name = "userId") Long userId){
        // Get Orders by User Id
        List<OrderDTO> response = orderService.findOrdersByUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    @GetMapping("/orders/filterBy")
    public ResponseEntity<List<OrderDTO>> findOrdersByUserIdAndStatus(@RequestParam(name = "userId") Long userId,
                                                                      @RequestParam(name = "status") String status){
        // Get Orders by User Id and Status.  Status must be all caps.
        Status convertedStatus = Status.valueOf(status.toUpperCase());
        if(convertedStatus != Status.COMPLETED && convertedStatus != Status.PENDING){
            throw new BadRequestException("Please format the enum as an all caps string of the available choices.");
        }
        List<OrderDTO> response = orderService.findOrdersByUserAndStatus(userId, convertedStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/orders/items/filterBy")
    public ResponseEntity<List<OrderItemDTO>> findOrderItemsByProductId(@RequestParam(name = "productId") Long productId){
        // A cart is a pending order.
        List<OrderItemDTO> response = orderService.findOrderItemsByProductId(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    // maybe could create a find order items by product id and order status?
    // could make an enum field in order items for if product is shipped or unshipped status



    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long orderId){

        boolean deleted = orderService.deleteOrder(orderId);
        if(!deleted){
            throw new BadRequestException("Product with id " + orderId + " was not found.");
        }
        return new ResponseEntity<>("{\"message\":\"Product Successfully Deleted\"}", HttpStatus.OK);
    }






}

