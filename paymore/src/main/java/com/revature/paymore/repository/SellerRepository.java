package com.revature.paymore.repository;

import com.revature.paymore.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
