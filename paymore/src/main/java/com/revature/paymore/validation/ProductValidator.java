package com.revature.paymore.validation;


import com.revature.paymore.model.Product;
import com.revature.paymore.model.enums.Category;
import com.revature.paymore.model.enums.Gender;
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

        Product product = (Product) target;
        if(product.getProductName() == null){
            errors.rejectValue("product_name", "product_name.empty", "product name can't be empty.");
        }

        if(product.getPrice() == 0){
            errors.rejectValue("price", "price.zero", "price cannot be zero.");
        }

        if(product.getQuantity() == 0){
            errors.rejectValue("quantity", "quantity.zero", "minimum quantity is 1");
        }

        if (product.getPrice() <= 0 || product.getQuantity() <= 0) {
            errors.rejectValue("price", "price.quantity.invalid", "Product's price or quantity is not valid");
        }

        if(product.getSize() < 5 || product.getSize() > 16){
            errors.rejectValue("size", "size.range.invalid", "size must be between 5 and 16");
        }

        if(product.getSize() % 0.5 != 0){
            errors.rejectValue("size", "size.decimal.invalid", "size must be multiple of 0.5");
        }

        if (product.getGender() != Gender.MENS && product.getGender() != Gender.WOMENS) {
            errors.rejectValue("gender", "gender.invalid", "Invalid gender specified");
        }

        if (product.getCategory() != Category.ATHLETIC && product.getCategory() != Category.CASUAL && product.getCategory() != Category.DRESS) {
            errors.rejectValue("category", "category.invalid", "Invalid category specified");
        }

        // TODO: May need to be expanded if additional enum options are added to category







    }
}

