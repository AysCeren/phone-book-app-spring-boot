package com.project.contactsdemo.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    //private Integer status; //gözüküyorsa bir olacağı için kesin göstermek gereksiz
    private Long personId;
}
