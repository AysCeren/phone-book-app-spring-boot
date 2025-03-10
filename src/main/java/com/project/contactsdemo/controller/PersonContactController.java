//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

import java.util.List;

import com.project.contactsdemo.dto.*;
import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.service.PersonContactService;
//import com.project.contactsdemo.validation.ValidContactInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping({"/api"})
public class PersonContactController {

    private final PersonContactService personContactService;
    //Constructor injection (DI)
    public PersonContactController(PersonContactService personContactService) {
        this.personContactService = personContactService;
    }

    @GetMapping(path = "/restTemplateControl/{ilKodu}")
    public ResponseEntity<GenericDTO<String>> getPersonWithId(@Valid @PathVariable("ilKodu") @Parameter(name="ilKodu", example = "6") String ilKodu) {
        GenericDTO<String> gDTO = personContactService.getPersonWithId(ilKodu);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gDTO);
    }
    @Operation( summary= "Save/Create new person", description = "Save/Create new person by giving properties as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new person."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/savePerson"})
    public ResponseEntity<GenericDTO<PersonResponseDTO>> savePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        GenericDTO<PersonResponseDTO> genericDTO =  this.personContactService.savePerson(personRequestDTO); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genericDTO);
    }

    @Operation( summary= "Save/Create new contact", description = "Save/Create new contact by giving properties(requestDto) as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new contact."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "404", description = "No person with given person_id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/saveContact"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> saveContact(@Valid @RequestBody ContactRequestDTO savedContactRequestDTO) {
        GenericDTO<ContactResponseDTO> genericDto = this.personContactService.saveContact(savedContactRequestDTO); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genericDto);
    }

    @Operation( summary= "Get all person", description = "Get all person from person table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No person was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.GET}, path = {"/getAllPerson"})
    public ResponseEntity<GenericDTO<List<PersonResponseDTO>>> getAllPerson() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personContactService.getAllPerson()); //body içerisinde döndüm generic DTO'yu
    }

    @Operation( summary= "Get all contact", description = "Get all contact from contact table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all contact"),
            @ApiResponse(responseCode = "404", description = "Not found - No contact was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping( method = {RequestMethod.GET},path = {"/getAllContact"})
    public ResponseEntity<GenericDTO<List<ContactResponseDTO>>> getAllContact() { //? ne gelirse gelsin //<> //Void: type'ı belli ama void typeinde
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personContactService.getAllContacts());
    }

    @Operation( summary= "Delete contact", description = "Safe delete for specific contact(the id will be given), then status from 1 --> 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete contact"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to delete"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to delete with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.PUT},path = {"/deleteContact/{contactId}"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> deleteContact(@Valid @PathVariable("contactId") @Parameter(name="contactId", description = "Id of Contact to delete", example = "1") Long contactId) {
        GenericDTO<ContactResponseDTO> genericDto = this.personContactService.deleteContact(contactId); //void, no response parameter
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDto);
    }

    @Operation( summary= "Update contact", description = "Update the contact(the id will be taken")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to update"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping({"/updateContact/{id}"})
    public ResponseEntity<GenericDTO<ContactResponseDTO>> updateContact( @Valid @RequestBody ContactRequestDTO contactRequestDTO, @Valid @PathVariable("id") @Parameter(name="id", description = "Contact id to find and then delete", example = "1") Long id) {
        GenericDTO<ContactResponseDTO> dto  = new GenericDTO<>(0,null);
        GenericDTO<ContactResponseDTO> genericDto = personContactService.updateContact(contactRequestDTO, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDto);
    }

    @Operation( summary= "Get all person with contact", description = "Get all person with their contacts from person and contact table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(path = "/getAllPersonWithContacts")
    public ResponseEntity<GenericDTO<List<PersonWithContactsDTO>>> getAllPersonWithContacts() {
       GenericDTO<List<PersonWithContactsDTO>> dto = personContactService.getAllPersonWithContacts();
        return ResponseEntity
               .status(HttpStatus.OK)
                .body(dto);
    }

    @Operation( summary= "Get all person", description = "Get one person with its all contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such person id to find its contact"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getAllContactsOfPerson/{id}")
    public ResponseEntity<GenericDTO<List<ContactResponseDTO>>> getAllContactsOfPerson(@Valid @PathVariable("id") @Parameter(name="id", description = "Person id to find person", example = "1") Long id) {
        GenericDTO<List<ContactResponseDTO>> genericDTO = personContactService.getAllContactsOfPerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericDTO);
    }
}
