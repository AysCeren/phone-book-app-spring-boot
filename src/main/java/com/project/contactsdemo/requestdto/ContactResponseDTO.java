package com.project.contactsdemo.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactResponseDTO {
    private String name;
    private String phoneNumber;
    private Integer status;
}
