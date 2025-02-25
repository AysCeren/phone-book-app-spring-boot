package com.project.contactsdemo.dto;

import com.project.contactsdemo.validation.ValidName;
import com.project.contactsdemo.validation.ValidPersonId;
import com.project.contactsdemo.validation.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
