package com.project.contactsdemo.person.controller;

import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.service.GetPersonWithAllContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class GetPersonWithAllContactController {
    private final GetPersonWithAllContactService getPersonWithAllContact;

    @Operation(summary = "Get all person", description = "Get one person with its all contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such person id to find its contact"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getAllContactsOfPerson/{id}")
    public ResponseEntity<GenericDTO<List<ContactResponseDTO>>> getAllContactsOfPerson(@Valid @PathVariable("id") @Parameter(name = "id", description = "Person id to find person", example = "1") Long id) {
        GenericDTO<List<ContactResponseDTO>> genericDTO = getPersonWithAllContact.getAllContactsOfPerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDTO);
    }
}
