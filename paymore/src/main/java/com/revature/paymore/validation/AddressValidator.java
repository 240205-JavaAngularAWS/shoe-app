package com.revature.paymore.validation;

import com.revature.paymore.model.Address;
import com.revature.paymore.model.enums.AddressType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {

        Address address = (Address) target;
        if (address.getCity() == null || address.getCity().isEmpty()) {
            errors.rejectValue("city", "city.empty", "City can't be empty.");
        }

        if (address.getState() == null || address.getState().isEmpty()) {
            errors.rejectValue("state", "state.empty", "State can't be empty.");
        }

        if (address.getZipCode() <= 0) { // Assuming zipCode is an integer. Adjust if it's a String.
            errors.rejectValue("zipCode", "zipCode.invalid", "Zip Code must be valid.");
        }

        if (address.getAddressText() == null || address.getAddressText().isEmpty()) {
            errors.rejectValue("addressText", "addressText.empty", "Address Text can't be empty.");
        }

        if (address.getAddressType() != AddressType.BILLING && address.getAddressType() != AddressType.SHIPPING && address.getAddressType() != AddressType.SELLER) {
            errors.rejectValue("addressType", "addressType.invalid", "Address Type must be of type BILLING, SHIPPING, or SELLER.");
        }
    }
}
