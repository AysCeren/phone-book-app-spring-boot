package com.project.contactsdemo.requestdto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactRequestDTO {
    private Long personId;
    private String name;
    private String phoneNumber;
    private Integer status;
}
