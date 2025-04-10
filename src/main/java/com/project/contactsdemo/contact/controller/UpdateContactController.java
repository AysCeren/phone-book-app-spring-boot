package com.project.contactsdemo.contact.controller;

import com.project.contactsdemo.contact.dto.ContactRequestDTO;
import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.contact.service.UpdateContactService;
import com.project.contactsdemo.core.dto.GenericDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class UpdateContactController {

    private final UpdateContactService updateContactService;

    @Operation( summary= "Update contact", description = "Update the contact(the id will be taken")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to update"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping({"/updateContact/{id}"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> updateContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO, @Valid @PathVariable("id") @Parameter(name="id", description = "Contact id to find and then delete", example = "1") Long id) {
        GenericDTO<ContactResponseDTO> dto  = new GenericDTO<>(0,null);
        GenericDTO<ContactResponseDTO> genericDto = updateContactService.updateContact(contactRequestDTO, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDto);
    }
}
