package com.revature.paymore.service;

import com.revature.paymore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }




}
