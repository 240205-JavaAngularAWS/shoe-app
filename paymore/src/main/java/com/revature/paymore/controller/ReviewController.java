package com.revature.paymore.controller;
import com.revature.paymore.exception.BadRequestException;
import com.revature.paymore.model.dto.ReviewDTO;
import com.revature.paymore.service.ResponseHelperService;
import com.revature.paymore.service.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ReviewController {


    private final ReviewService reviewService;
    private final ResponseHelperService responseHelperService;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);


    @Autowired
    public ReviewController(ReviewService reviewService, ResponseHelperService responseHelperService) {
        this.reviewService = reviewService;
        this.responseHelperService = responseHelperService;
    }


    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@Valid @RequestBody ReviewDTO reviewDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // using DTO so we don't have to use the entire Product object in schema.
            return responseHelperService.getBindingErrors(bindingResult);
        }
        logger.info(reviewDto.toString());

        ReviewDTO response = reviewService.addReview(reviewDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/reviews/all")
    public ResponseEntity<List<ReviewDTO>> findAllReviews(){
        List<ReviewDTO> response = reviewService.findAllReviews();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/reviews/filterBy")
    public ResponseEntity<List<ReviewDTO>> findReviews(@RequestParam(name = "productId") long productId,
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
