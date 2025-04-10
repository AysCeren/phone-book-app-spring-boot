package com.project.contactsdemo.person.dto;

import com.project.contactsdemo.contact.dto.ContactForPersonDTO;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
//Note: burada yazılan sıra ile mapleme işlemi yapılır
public class PersonWithContactsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String birthCity;
    private String message;
    private List<ContactForPersonDTO> contacts;
}
