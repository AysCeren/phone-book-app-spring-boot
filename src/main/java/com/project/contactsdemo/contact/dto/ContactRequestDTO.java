package com.project.contactsdemo.contact.dto;

import com.project.contactsdemo.core.validation.ValidName;
import com.project.contactsdemo.core.validation.ValidPersonId;
import com.project.contactsdemo.core.validation.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactRequestDTO {

    @ValidName
    @Schema(name = "name", example = "Ayse Ceren Coban")
    private String name;

    @ValidPhoneNumber
    @Schema(name = "phoneNumber", example = "+905522568471")
    private String phoneNumber;

    @ValidPersonId
    @Schema(name = "personId", example = "4", required=true)
    private Long personId;
}
