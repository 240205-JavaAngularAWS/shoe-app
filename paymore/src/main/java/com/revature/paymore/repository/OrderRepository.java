package com.revature.paymore.repository;

import com.revature.paymore.model.Order;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.User;
import com.revature.paymore.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.revature.paymore.model.enums.Status.PENDING;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByUserAndStatus(User user, Status status);

}
