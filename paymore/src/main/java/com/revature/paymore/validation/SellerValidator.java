package com.revature.paymore.validation;

import com.revature.paymore.model.Seller;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SellerValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Seller.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {

        Seller seller = (Seller) target;

        if(seller.getUsername() == null){
            errors.rejectValue("username", "username.empty", "username shouldn't be empty");
        }

        if(seller.getEmail() == null){
            errors.rejectValue("email", "email.empty", "email shouldn't be empty");
        }

        if(seller.getPassword() == null){
            errors.rejectValue("password", "password.empty", "password shouldn't be empty");
        }

        if(seller.getPassword().length() < 4){
            errors.rejectValue("password", "password.too.short", "password is too short.");
        }

        if(seller.getAddress() == null){
            errors.rejectValue("address", "address.empty", "Address shouldn't be empty.");
        }

    }
}

