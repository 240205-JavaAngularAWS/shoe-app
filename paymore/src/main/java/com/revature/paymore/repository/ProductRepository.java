package com.revature.paymore.repository;

import com.revature.paymore.model.Product;

import com.revature.paymore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySellerId(Long SellerId);

}
