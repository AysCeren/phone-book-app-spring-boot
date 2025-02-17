package com.project.contactsdemo.requestdto;

import com.project.contactsdemo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthCity;
    private Gender gender;
    private String phoneNumber;
}
