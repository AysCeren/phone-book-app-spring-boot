package com.project.contactsdemo.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthdate {
    String message() default "Invalid birthdate. Date must be in the past, and the person must be at least 18 years old.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
