package com.project.contactsdemo.validation;

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
    public boolean isValid(String birthDateStr, ConstraintValidatorContext context) {
        if (birthDateStr == null || birthDateStr.trim().isEmpty()) {
            return false; // Birthdate cannot be null or empty
        }

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, FORMATTER);
            LocalDate today = LocalDate.now();

            // Ensure the birthdate is in the past and the person are at least 18 years old
            return !birthDate.isAfter(today) && Period.between(birthDate, today).getYears() >= MIN_AGE;
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }
}
