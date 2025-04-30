package com.project.contactsdemo.person.controller;

import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.dto.PersonResponseDTO;
import com.project.contactsdemo.person.service.PersonGetAllService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Validated
@RequestMapping({"/api"})
@RestController
@RequiredArgsConstructor
public class GetAllPersonController {

    private final PersonGetAllService personGetAllService;

    @Operation(summary = "Get all person", description = "Get all person from person table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No person was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.GET}, path = {"/getAllPerson"})
    public ResponseEntity<GenericDTO<List<PersonResponseDTO>>> getAllPerson() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personGetAllService.getAllPerson());
    }
}
