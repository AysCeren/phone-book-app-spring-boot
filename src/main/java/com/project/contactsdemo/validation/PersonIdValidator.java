package com.project.contactsdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, Long> {

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext context) {
        if (personId == null) return false;
        return personId > 0;
    }
}
