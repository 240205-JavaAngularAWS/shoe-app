package com.revature.paymore.service;
import com.revature.paymore.model.DTO.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.User;
import com.revature.paymore.repository.OrderRepository;
import com.revature.paymore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }




    // register new order.
    public OrderDTO registerOrder(Order order){
        // check if user exists.
        orderRepository.save(order);
        return new OrderDTO(order);

    }
    
    


}
