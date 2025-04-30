package com.project.contactsdemo.contact.controller;

import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.contact.service.DeleteContactService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class DeleteContactController {
    private final DeleteContactService deleteContactService;

    @Operation(summary = "Delete contact", description = "Safe delete for specific contact(the id will be given), then status from 1 --> 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete contact"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to delete"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to delete with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.PUT}, path = {"/deleteContact/{contactId}"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> deleteContact(@Valid @PathVariable("contactId") @Parameter(name = "contactId", description = "Id of Contact to delete", example = "1") Long contactId) {
        GenericDTO<ContactResponseDTO> genericDto = this.deleteContactService.deleteContact(contactId); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDto);
    }
}
