package com.project.contactsdemo.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonResponseDTO {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthCity;
}
