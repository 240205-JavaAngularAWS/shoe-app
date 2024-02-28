package com.revature.paymore.service;
import com.revature.paymore.model.DTO.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    UserRepository userRepository;

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
