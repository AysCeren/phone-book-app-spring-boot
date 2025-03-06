package com.project.contactsdemo.exception;

import jakarta.validation.ConstraintDeclarationException;

public class ValidationControlException extends ConstraintDeclarationException {
    public ValidationControlException(String message) {
        super(message);
    }
}
