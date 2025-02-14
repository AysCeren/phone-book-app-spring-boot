package com.project.contactsdemo.requestdto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
//TODO: Neden Getter ve Setter koymadığımın açıklaması:
public class ContactDTO {
    private Long personId;
    private String name;
    private String phoneNumber;
    private Integer status;
}
