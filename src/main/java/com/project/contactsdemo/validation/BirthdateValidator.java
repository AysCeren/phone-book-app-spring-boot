package com.project.contactsdemo.validation;

import com.project.contactsdemo.exception.ValidationControlException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BirthdateValidator implements ConstraintValidator<ValidBirthdate, String> {
    private static final int MIN_AGE = 18; // Change this if needed
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public boolean isValid(String birthDateStr, ConstraintValidatorContext context) throws ValidationControlException {
        if (birthDateStr == null || birthDateStr.trim().isEmpty()) {
            throw new ValidationControlException("Birthdate cannot be empty"); // Birthdate cannot be null or empty
        }
        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, FORMATTER);
            LocalDate today = LocalDate.now();

            // Ensure the birthdate is in the past and the person are at least 18 years old
           if(birthDate.isAfter(today))
               throw new ValidationControlException("Birthdate must be after today");
           else if(Period.between(birthDate, today).getYears() >= MIN_AGE)
               throw new ValidationControlException("Birthdate must be between " + MIN_AGE);
            return true;
        } catch (DateTimeParseException e) {
            throw new ValidationControlException("Please enter valid date, control your month and year"); // Invalid date format
        }
    }
}
