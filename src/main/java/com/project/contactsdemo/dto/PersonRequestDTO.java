package com.project.contactsdemo.dto;

import com.project.contactsdemo.enums.Gender;
import com.project.contactsdemo.validation.ValidBirthdate;
import com.project.contactsdemo.validation.ValidGender;
import com.project.contactsdemo.validation.ValidName;
import com.project.contactsdemo.validation.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
