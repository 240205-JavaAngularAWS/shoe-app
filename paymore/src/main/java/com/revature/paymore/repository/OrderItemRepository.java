package com.revature.paymore.repository;

import com.revature.paymore.model.Order;
import com.revature.paymore.model.OrderItem;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.User;
import com.revature.paymore.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByProduct(Product product);

}
