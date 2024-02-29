package com.revature.paymore.service;


import com.revature.paymore.model.Review;

import com.revature.paymore.model.dto.ReviewDTO;

import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {


    ReviewRepository reviewRepository;
    ProductRepository productRepository;



    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }
    // validate the review
    public ReviewDTO addReview(Review review) throws EntityNotFoundException{
        // check if product exists
        productRepository.findById(review.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));


        reviewRepository.save(review);
        return new ReviewDTO(review);
    }

    public List<ReviewDTO> findReviewsByProductId(Long productId){
        // check to ensure Product exists.
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found.  No reviews"));
        return reviewRepository.findByProductId(productId).stream().map(ReviewDTO::new).toList();
    }


    public boolean deleteReview(long reviewId){
        return reviewRepository.findById(reviewId)
                .map(user -> { productRepository.deleteById(reviewId);
                    return true;})
                .orElse(false);
    }







}
