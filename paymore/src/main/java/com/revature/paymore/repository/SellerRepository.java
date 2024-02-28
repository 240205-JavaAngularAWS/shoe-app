package com.revature.paymore.repository;

import com.revature.paymore.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByUsername(String username);

    Optional<Seller> findByUsernameAndPassword(String username, String password);


}
