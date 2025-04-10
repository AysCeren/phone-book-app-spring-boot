package com.project.contactsdemo.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor

@Data
    public class PersonResponseDTO implements Serializable {
        private Long id;
        private String firstName;
        private String lastName;
        private String birthDate;
        private String birthCity; //TODO: RestTemplate sonrası burayı şehir adı olarak basacağız.
}