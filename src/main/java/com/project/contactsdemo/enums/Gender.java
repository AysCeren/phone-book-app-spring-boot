package com.project.contactsdemo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Gender {
    //Enum type will be String
    MALE,
    FEMALE,
    UNKNOWN;

    public static Gender getGender(String gender) {
        return Arrays.stream(Gender.values()).filter(value -> value.toString().equalsIgnoreCase(gender))
                .findFirst().orElse(null);
    }
}