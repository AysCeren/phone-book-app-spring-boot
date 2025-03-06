package com.project.contactsdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Invalid gender. Allowed values: MALE, FEMALE, UNKNOWN.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
