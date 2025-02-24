package com.project.contactsdemo.requestdto;

import com.project.contactsdemo.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonRequestDTO {
    @NotBlank(message = "First name is mandatory!")
    private String firstName;
    @NotBlank(message = "Last name is mandatory!")
    private String lastName;
    @NotNull
    private String birthDate;
    private String birthCity;
    private Gender gender;
    //@Pattern(regexp = "^\\\\+\\\\d{12}$", message = "Please enter a valid phone number, which contains only numbers!")
    @NotBlank(message = "Phone number is mandatory!")
    private String phoneNumber;
    @Email
    private String email;
}
