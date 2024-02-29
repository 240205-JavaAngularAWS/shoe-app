package com.revature.paymore.validation;


import com.revature.paymore.model.Product;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {

        Product Product = (Product) target;

        if(Product.getPrice() == 0){
            errors.rejectValue("price", "price.zero", "price cannot be zero.");
        }

        if(Product.getQuantity() == 0){
            errors.rejectValue("quantity", "quantity.zero", "minimum quantity is 1");
        }


    }
}

