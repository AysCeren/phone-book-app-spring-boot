package com.project.contactsdemo.validation;

import com.project.contactsdemo.exception.ValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null)
            throw new ValidationException("Fill the name field");  // You can allow null if needed
        if(!(name.length() >= 3 && name.length() <= 50))
                throw new ValidationException("Invalid name");
        return true;
    }
}