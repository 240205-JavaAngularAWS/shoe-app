package com.revature.paymore.service;

import com.revature.paymore.exception.InvalidProductException;
import com.revature.paymore.exception.InvalidReviewException;
import com.revature.paymore.exception.InvalidSellerException;
import com.revature.paymore.model.Address;
import com.revature.paymore.model.Product;
import com.revature.paymore.model.Review;
import com.revature.paymore.model.Seller;
import com.revature.paymore.validation.AddressValidator;
import com.revature.paymore.validation.ProductValidator;
import com.revature.paymore.validation.ReviewValidator;
import com.revature.paymore.validation.SellerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


@Service
public class ValidationService{

    private final SellerValidator sellerValidator;

    private final ProductValidator productValidator;

    private final AddressValidator addressValidator;

    private final ReviewValidator reviewValidator;


    @Autowired
    public ValidationService(SellerValidator sellerValidator, ProductValidator productValidator, AddressValidator addressValidator, ReviewValidator reviewValidator) {
        this.sellerValidator = sellerValidator;
        this.productValidator = productValidator;
        this.addressValidator = addressValidator;
        this.reviewValidator = reviewValidator;
    }

    public void validateSeller(Seller seller){
        Errors errors = new BeanPropertyBindingResult(seller, "seller");
        sellerValidator.validate(seller, errors);
        if (errors.hasErrors()) {
            throw new InvalidSellerException("Seller is Invalid.", errors);
        }
    }

    public void validateProduct(Product product){
        Errors errors = new BeanPropertyBindingResult(product, "product");
        productValidator.validate(product, errors);
        if (errors.hasErrors()) {
            throw new InvalidProductException("Product is Invalid.", errors);
        }
    }

    public void validateAddress(Address address){
        Errors errors = new BeanPropertyBindingResult(address, "address");
        productValidator.validate(address, errors);
        if (errors.hasErrors()) {
            throw new InvalidProductException("Product is Invalid.", errors);
        }
    }

    public void validateReview(Review review){
        Errors errors = new BeanPropertyBindingResult(review, "address");
        reviewValidator.validate(review, errors);
        if (errors.hasErrors()) {
            throw new InvalidReviewException("Review is Invalid.", errors);
        }
    }

}
