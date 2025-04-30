package com.project.contactsdemo.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.project.contactsdemo.core.exception.ValidationControlException;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, Long> {

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext context) throws ValidationControlException {
        if (personId == null) throw new ValidationControlException("Please fill the person id field");
        return personId > 0;
    }
}
