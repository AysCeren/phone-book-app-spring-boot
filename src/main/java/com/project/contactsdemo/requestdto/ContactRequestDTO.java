package com.project.contactsdemo.requestdto;

import com.project.contactsdemo.validation.CorrectNumber;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactRequestDTO {
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @NotBlank
    @CorrectNumber
    private String phoneNumber;
    @NotNull
    private Long personId;
}
