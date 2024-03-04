package com.revature.paymore.service;


import com.revature.paymore.controller.AddressController;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Review;

import com.revature.paymore.model.dto.ReviewDTO;

import com.revature.paymore.repository.ProductRepository;
import com.revature.paymore.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {


    ReviewRepository reviewRepository;
    ProductRepository productRepository;

    ValidationService validationService;

    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);



    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, ValidationService validationService) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ReviewDTO addReview(ReviewDTO reviewDto) throws EntityNotFoundException{
       if(reviewDto == null){
           throw new NullPointerException("Object cannot be empty.");
       }
       logger.info(reviewDto.toString());
        // convert DTO to review.
        Review review = new Review();

        // validate review object
        validationService.validateReview(review);

        // check if product exists
        Product product = productRepository.findById(review.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException(" This product does not have any Seller "));

        // get local timestamp
        review.setReviewDate(LocalDateTime.now());


        // add review to product.
        product.addReview(review);

        reviewRepository.save(review);
        productRepository.save(product);

        return modelMapper.map(review, ReviewDTO.class);
    }

    public List<ReviewDTO> findReviewsByProductId(Long productId){
        // check to ensure Product exists.
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found.  No reviews"));
        return reviewRepository.findByProductId(productId).stream().map(review -> modelMapper.map(review, ReviewDTO.class)).toList();
    }


    public boolean deleteReview(long reviewId){
        // get review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found."));

        // get associated product
        Product product = review.getProduct();

        // remove review from product.
        product.removeReview(review);
        productRepository.save(product);

        reviewRepository.deleteById(reviewId);

        // if no exception is thrown, this method will return true.
        // It will never return false, but just throw an exception.
        return true;
    }


    public List<ReviewDTO> findReviewsByProductAndRating(long productId, int rating){
        // check to ensure Product exists.
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));
        // get reviews from repository
        List<Review> reviews = reviewRepository.findByProductAndRating(product, rating);
        // convert them to DTOs.
        return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).toList();
    }




}
