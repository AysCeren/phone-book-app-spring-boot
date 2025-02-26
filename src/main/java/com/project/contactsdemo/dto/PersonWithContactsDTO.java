package com.project.contactsdemo.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PersonWithContactsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
    private List<ContactForPersonDTO> contacts;
}
