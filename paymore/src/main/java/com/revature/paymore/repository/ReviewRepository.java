package com.revature.paymore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository<Review> extends JpaRepository<Review, Long> {
}
