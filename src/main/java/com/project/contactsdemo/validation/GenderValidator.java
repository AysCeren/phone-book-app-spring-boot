package com.project.contactsdemo.validation;

import com.project.contactsdemo.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<ValidGender, Gender> {
    @Override
    public boolean isValid(Gender gender, ConstraintValidatorContext constraintValidatorContext) {
        if (gender == null) {
            return false; // Or true if null is allowed
        }
        return Arrays.stream(Gender.values())
                .anyMatch(validGender -> validGender.name().equalsIgnoreCase(gender.toString()));
    }
}
