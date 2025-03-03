package com.project.contactsdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, Long> {

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext context) {
        if (personId == null) throw new ValidationException("Please fill the person id field");
        return personId > 0;
    }
}
