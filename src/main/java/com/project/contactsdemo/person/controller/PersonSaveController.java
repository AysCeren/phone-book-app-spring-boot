package com.project.contactsdemo.person.controller;

import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.dto.PersonRequestDTO;
import com.project.contactsdemo.person.dto.PersonResponseDTO;
import com.project.contactsdemo.person.service.PersonSaveService;
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
@RestController
@RequestMapping({"/api"})
@RequiredArgsConstructor
public class PersonSaveController {

    private final PersonSaveService personSaveService;

    @Operation( summary= "Save/Create new person", description = "Save/Create new person by giving properties as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new person."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/savePerson"})
    public ResponseEntity<GenericDTO<PersonResponseDTO>> savePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        GenericDTO<PersonResponseDTO> genericDTO =  this.personSaveService.savePerson(personRequestDTO); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genericDTO);
    }
}
