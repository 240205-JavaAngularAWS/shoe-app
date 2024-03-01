package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.Review;
import com.revature.paymore.model.dto.ReviewDTO;
import com.revature.paymore.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {


    ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);


    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDto){
        logger.info(reviewDto.toString());
        ReviewDTO response = reviewService.addReview(reviewDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviews(@RequestParam(name = "productid") long productId,
                                                       @RequestParam(name = "rating", required = false) Integer rating) {
        List<ReviewDTO> response;
        if (rating != null) {
            // Call the service method that requires both productId and rating
            response = reviewService.findReviewsByProductAndRating(productId, rating);
        } else {
            // Call the service method that only requires productId
            response = reviewService.findReviewsByProductId(productId);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if(!isDeleted){
            throw new BadRequestException("Review with id " + reviewId + " was not found.");
        }

        return new ResponseEntity<>("{\"message\":\"Review Successfully Deleted\"}", HttpStatus.OK);
    }



}
