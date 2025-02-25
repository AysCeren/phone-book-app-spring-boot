package com.project.contactsdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) return false;  // You can allow null if needed
        return name.length() >= 3 && name.length() <= 50;
    }
}