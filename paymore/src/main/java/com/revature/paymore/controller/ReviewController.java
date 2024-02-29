package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Review;
import com.revature.paymore.model.dto.ProductDTO;
import com.revature.paymore.model.dto.ReviewDTO;
import com.revature.paymore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {


    ReviewService reviewService;


    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }



    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody Review review){

        ReviewDTO response = reviewService.addReview(review);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/reviews/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProduct(@PathVariable Long productId){
        List<ReviewDTO> response = reviewService.getReviewsByProduct(productId);
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
