package com.project.contactsdemo.core.exception;

import jakarta.validation.ConstraintDeclarationException;

public class ValidationControlException extends ConstraintDeclarationException {
    public ValidationControlException(String message) {
        super(message);
    }
}
