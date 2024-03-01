package com.revature.paymore.service;
import com.revature.paymore.exception.InvalidOrderException;
import com.revature.paymore.exception.StockException;
import com.revature.paymore.model.OrderItem;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.dto.OrderDTO;
import com.revature.paymore.model.Order;
import com.revature.paymore.model.dto.OrderItemDTO;
import com.revature.paymore.model.enums.Status;
import com.revature.paymore.repository.OrderItemRepository;
import com.revature.paymore.repository.OrderRepository;
import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository){

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public OrderItem convertOrderItemToEntity(OrderItemDTO orderItemDTO){
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found"));
        return new OrderItem(orderItemDTO.getPriceTotal(),
                orderItemDTO.getQuantity(),
                order,
                orderItemDTO.getProductId()
                );

    }




    // register new order.
    public OrderDTO registerCart(Order order){
        // check if user exists.
        userRepository.findById(order.getUser().getId())
                        .orElseThrow(() -> new EntityNotFoundException("User Not Found."));
        if(order.getStatus() != Status.PENDING){
            throw new InvalidOrderException("Invalid Status. Status must be PENDING for Carts");
        }
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);

    }


    public OrderDTO addItemToCart(OrderItemDTO orderItemDto){
        // check order exists
        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found"));
        // check order status
        if(order.getStatus() != Status.PENDING){
            throw new InvalidOrderException("Invalid Status. Status must be PENDING.");
        }
        // check product
        Product product = productRepository.findById(orderItemDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        checkStock(product.getQuantity(), orderItemDto.getQuantity());

        // create new order item
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        // save new order item
        orderItemRepository.save(orderItem);

        // add to order
        order.addOrderItem(orderItem);
        orderRepository.save(order);

        return modelMapper.map(order, OrderDTO.class);

    }

    public void checkStock(int currentStock, int itemQuantity){
        if(currentStock < itemQuantity){
            throw new StockException("Stock too low for current quantity.");
        }
        if(currentStock == 0){
            throw new StockException("Out of Stock");
        }

    }



    
    


}
