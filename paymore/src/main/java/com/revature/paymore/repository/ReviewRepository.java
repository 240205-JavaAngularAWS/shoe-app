package com.revature.paymore.repository;


import com.revature.paymore.model.Product;
import com.revature.paymore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByProductId(long productId);

    List<Review> findByProductAndRating(Product product, int rating);


}
