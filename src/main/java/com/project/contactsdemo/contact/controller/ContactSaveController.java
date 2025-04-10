package com.project.contactsdemo.contact.controller;

import com.project.contactsdemo.contact.dto.ContactRequestDTO;
import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.contact.service.ContactSaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class ContactSaveController {
    private final ContactSaveService contactSaveService;

    @Operation(summary = "Save/Create new contact", description = "Save/Create new contact by giving properties(requestDto) as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new contact."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "404", description = "No person with given person_id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/saveContact"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> saveContact(@Valid @RequestBody ContactRequestDTO savedContactRequestDTO) {
        GenericDTO<ContactResponseDTO> genericDto = this.contactSaveService.saveContact(savedContactRequestDTO); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genericDto);
    }
}
