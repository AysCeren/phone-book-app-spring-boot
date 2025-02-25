package com.project.contactsdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PersonIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidPersonId {
    String message() default "Person ID must be a positive number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
