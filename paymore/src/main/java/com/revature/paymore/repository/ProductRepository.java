package com.revature.paymore.repository;

import com.revature.paymore.model.Product;

import com.revature.paymore.model.Review;
import com.revature.paymore.model.Seller;
import com.revature.paymore.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // changed from sellerId to seller
    List<Product> findBySeller(Seller seller);

    List<Product> findByCategory(Category category);

    List<Product> findBySellerAndCategory(Seller seller, Category category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(concat('%', ?1, '%'))")
    List<Product> findByProductNameContainingIgnoreCase(String keyword);

}
