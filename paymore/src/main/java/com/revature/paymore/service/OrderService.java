package com.revature.paymore.service;
import com.revature.paymore.exception.EntityAlreadyExistsException;
import com.revature.paymore.exception.InvalidEntityException;
import com.revature.paymore.exception.StockException;
import com.revature.paymore.model.OrderItem;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.User;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository){

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;

    }


    // register new order.
    public OrderDTO registerCart(OrderDTO orderDto){
        // registers a pending order as a cart.
        // check if user exists.
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found."));

        // make sure User does not have a currently pending order, which means they have an active cart.
        boolean hasPendingOrder = user.getOrders().stream()
                .anyMatch(order -> order.getStatus() == Status.PENDING);

        if(hasPendingOrder){
            // remove one
            throw new EntityAlreadyExistsException("A cart is already present. Please use existing cart.");
        } else {
            Order order = new Order(orderDto.getPriceTotal(),
                    Status.PENDING,
                    null,
                    user);

            Order savedOrder = orderRepository.save(order);
            user.addOrder(savedOrder);
            userRepository.save(user);
            return modelMapper.map(order, OrderDTO.class);
        }
    }

    public void checkOrderItem(OrderItem orderItem, Product product) {
        // this method validates that the price is accurate.
        double expectedTotalPrice = product.getPrice() * orderItem.getQuantity();
        double actualTotalPrice = orderItem.getPrice();

        // Allow for a small difference to account for floating-point arithmetic issues
        final double EPSILON = 0.01;

        if (Math.abs(expectedTotalPrice - actualTotalPrice) > EPSILON) {
            // If the difference is greater than the allowed margin, adjust the price
            orderItem.setPrice(expectedTotalPrice);
            orderItemRepository.save(orderItem);
        }
    }



    @Transactional
    public OrderDTO addItemToCart(OrderItemDTO orderItemDto) {
        OrderItem orderItem;
        logger.info("Adding Item to Cart: {}", orderItemDto);
        Order order = validateAndGetOrder(orderItemDto.getOrderId());
        Product product = validateAndGetProduct(orderItemDto.getProductId());
        checkStock(product.getQuantity(), orderItemDto.getQuantity());

        // check to see if orderItem is already in cart.
        Optional<OrderItem> existingOrderItem = order.getOrderItems().stream()
                .filter(item -> Objects.equals(item.getProduct(), product.getId()))
                .findFirst();
        if(existingOrderItem.isPresent()){
            // if present, just update the quantity.
            orderItem = existingOrderItem.get();
            int newQuantity = orderItem.getQuantity() + orderItemDto.getQuantity();
            orderItem.setQuantity(newQuantity);
            orderItemRepository.save(orderItem);
        }
        else {
            orderItem = createOrderItem(orderItemDto, product);
            order.addOrderItem(orderItem);


        }
        updateOrderTotalPrice(order, orderItem);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);

        }





    private Order validateAndGetOrder(Long orderId) {
        // ensures that the order has the correct status.
        return orderRepository.findById(orderId)
                .filter(order -> order.getStatus() == Status.PENDING)
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found or Invalid Status"));
    }



    public OrderDTO removeItemFromCart(long orderItemId){
        // this removes an item from the cart completely regardless of quantity.
        // check order exists
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException("Order Item Not Found"));

        // get Order
        Order order = orderItem.getOrder();

        // remove Order Item from Order
        order.removeOrderItem(orderItem);

        // update Price total
        double updatedOrderPrice = order.getPriceTotal() - (orderItem.getQuantity() *  orderItem.getPrice());
        order.setPriceTotal(updatedOrderPrice);


        orderRepository.save(order);
        orderItemRepository.deleteById(orderItemId);

        return modelMapper.map(order, OrderDTO.class);

    }


    public void checkStock(int currentStock, int itemQuantity){
        // checks to ensure the stock and quantity are valid.
        if(currentStock < itemQuantity){
            throw new StockException("Stock too low for current quantity.");
        }
        if(currentStock == 0){
            throw new StockException("Out of Stock");
        }

    }

    public List<OrderItemDTO> findOrderItemsByProduct(long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found"));

        return orderItemRepository.findByProduct(product).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class)).toList();

    }




    public OrderDTO submitOrder(Long orderId){
        // Get order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found"));
        // check OrderStatus.  Status must be PENDING, or it cannot be submitted (A completed order has already been submitted)
        if(order.getStatus() != Status.PENDING){
            throw new InvalidEntityException("Order cannot be processed. Invalid Status.");
        }

        order.getOrderItems().forEach(orderItem -> {
            Product product = orderItem.getProduct();

            // ensure the stock quantity is valid.
            checkStock(product.getQuantity(), orderItem.getQuantity());

            // inventory management - update the stock.  Created a variable for clarity
            int updatedProductQuantity = product.getQuantity() - orderItem.getQuantity();

            product.setQuantity(updatedProductQuantity);
            productRepository.save(product);
        });

        // ensure the User has at least one credit card attached to their account.
        User user = order.getUser();
        if(user.getCreditCards().isEmpty()){
            throw new EntityNotFoundException("No valid creditcards are associated with User id " + user.getId() + ".");
        }


        // change status of order to "COMPLETED".
        order.setStatus(Status.COMPLETED);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }


    public List<OrderDTO> findOrdersByUser(long userId){
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        return orderRepository.findByUser(user).stream()
                .map(order ->  modelMapper.map(order, OrderDTO.class)).toList();



    }

    public boolean deleteOrder(long orderId){
        return orderRepository.findById(orderId)
                .map(user -> { orderRepository.deleteById(orderId);
                    return true;})
                .orElse(false);
    }


    public List<OrderItemDTO> findOrderItemsByOrder(long orderId){
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new EntityNotFoundException("Order Not Found"));
        return orderItemRepository.findByOrder(order).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class)).toList();

    }


    private Product validateAndGetProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));
    }

    private OrderItem createOrderItem(OrderItemDTO orderItemDto, Product product) {
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        orderItem.setProduct(product);
        checkStock(product.getQuantity(), orderItem.getQuantity());
        return orderItemRepository.save(orderItem);
    }

    private void updateOrderTotalPrice(Order order, OrderItem item) {
        double updatedPrice = order.getPriceTotal() + (item.getQuantity() * item.getPrice());
        order.setPriceTotal(updatedPrice);
    }



}
