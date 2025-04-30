package com.project.contactsdemo.core.validation;

import com.project.contactsdemo.core.exception.ValidationControlException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) throws ValidationControlException {
        if (name == null)
            throw new ValidationControlException("Fill the name field");  // You can allow null if needed
        if(!(name.length() >= 3 && name.length() <= 50))
                throw new ValidationControlException("Invalid name");
        return true;
    }
}