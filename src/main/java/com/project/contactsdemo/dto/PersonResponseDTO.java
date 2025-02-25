package com.project.contactsdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class PersonResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthCity;
}
