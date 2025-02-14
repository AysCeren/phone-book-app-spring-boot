package com.project.contactsdemo.requestdto;

import com.project.contactsdemo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthCity;
    private Gender gender;
    private String phoneNumber;
}
