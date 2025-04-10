package com.project.contactsdemo.core.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.project.contactsdemo.core.exception.ValidationControlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private final Logger log = LoggerFactory.getLogger(ValidPhoneNumber.class);

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        if (phoneNumber == null) return true; // Allow null values -->belki de bu null durumunu kontrole gerek yoktur
        try {
            Phonenumber.PhoneNumber phone = phoneNumberUtil.parse(phoneNumber, "TR"); // Change default country as needed
            return phoneNumberUtil.isValidNumber(phone);
        } catch (NumberParseException e) {
            log.error(e.getMessage());
            throw new ValidationControlException("Invalid phone number");
        }
    }
}
