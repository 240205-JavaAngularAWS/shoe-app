package com.revature.paymore.validation;

import com.revature.paymore.model.Review;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.model.enums.Gender;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReviewValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Review.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {

        Review review = (Review) target;
        if(review.getContent() == null){
            errors.rejectValue("content", "content.empty", "content can't be empty.");
        }

        if(review.getProduct() == null){
            errors.rejectValue("product", "product.not.set", "review must be associated with a product.");
        }

        if(review.getRating() < 5 || review.getRating() < 1){
            errors.rejectValue("rating", "rating.out.of.range", "rating must be a value within 1 and 5");
        }

        }









}

