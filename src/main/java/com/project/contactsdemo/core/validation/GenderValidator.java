package com.project.contactsdemo.core.validation;

import com.project.contactsdemo.core.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.project.contactsdemo.core.exception.ValidationControlException;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {
    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        if (Gender.getGender(gender) == null) {
           throw new ValidationControlException("Gender has three options --> MALE, FEMALE, UNKNOWN"); // Or true if null is allowed
        }
        return true;
    }
}
