package com.project.contactsdemo.person.controller;

import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.dto.PersonWithContactsDTO;
import com.project.contactsdemo.person.service.GetAllPersonWithAllContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class GetAllPersonWithAllContactController {

    private final GetAllPersonWithAllContactService getAllPersonWithAllContactService;

    @Operation(summary = "Get all person with contact", description = "Get all person with their contacts from person and contact table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(path = "/getAllPersonWithContacts")
    public ResponseEntity<GenericDTO<List<PersonWithContactsDTO>>> getAllPersonWithContacts() {
        GenericDTO<List<PersonWithContactsDTO>> dto = getAllPersonWithAllContactService.getAllPersonWithContacts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
}
