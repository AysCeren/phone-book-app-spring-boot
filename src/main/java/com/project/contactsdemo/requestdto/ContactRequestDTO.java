package com.project.contactsdemo.requestdto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class ContactRequestDTO {
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    @Pattern(regexp = "[0-9]", message = "Please enter a valid phone number, which contains only numbers!")
    @Size(min = 10, max = 11, message = "Please enter a valid phone number, which contains only numbers!")
    private String phoneNumber;
    @NotNull
    private Long personId;
}
