package com.project.contactsdemo.person.dto;

import com.project.contactsdemo.core.validation.ValidBirthdate;
import com.project.contactsdemo.core.validation.ValidGender;
import com.project.contactsdemo.core.validation.ValidName;
import com.project.contactsdemo.core.validation.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonRequestDTO {

    @ValidName
    @Schema(name = "firstName", example = "Ayse Ceren")
    private String firstName;

    @ValidName
    @Schema(name = "lastName", example = "Coban")
    private String lastName;

    @ValidBirthdate
    @Schema(name = "birthDate", example = "dd-MM-yyyy --> 15-03-2023")
    private String birthDate;

    @Schema(name = "birthCity", example = "5")
    private String birthCity;


    @Schema(name = "gender", example = "FEMALE")
    @ValidGender
    private String gender;

    @ValidPhoneNumber
    @Schema(name = "phoneNumber", example = "+905522568471")
    private String phoneNumber;

    @Email
    @Schema(name = "email", example = "ceren@mail.com", required = false)
    private String email;
}
