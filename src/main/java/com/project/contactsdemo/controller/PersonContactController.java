//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

import java.util.List;
import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.dto.ContactRequestDTO;
import com.project.contactsdemo.dto.ContactResponseDTO;
import com.project.contactsdemo.dto.PersonRequestDTO;
import com.project.contactsdemo.dto.PersonResponseDTO;
import com.project.contactsdemo.service.PersonContactService;
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

    @Operation( summary= "Save/Create new person", description = "Save/Create new person by giving properties as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new person."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/savePerson"})
    public ResponseEntity<PersonResponseDTO> savePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        this.personContactService.savePerson(personRequestDTO); //void, no response parameter
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    @Operation( summary= "Save/Create new contact", description = "Save/Create new contact by giving properties(requestDto) as JSON, working with POST!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved new contact."),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid data enter"),
            @ApiResponse(responseCode = "404", description = "No person with given person_id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.POST}, path = {"/saveContact"})
    public ResponseEntity<ContactResponseDTO> saveContact(@Valid @RequestBody ContactRequestDTO savedContactRequestDTO) {
        this.personContactService.saveContact(savedContactRequestDTO); //void, no response parameter
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation( summary= "Get all person", description = "Get all person from person table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No person was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    //TODO: Runtime errorlar için ApiResponse yazılacaklar!
    @RequestMapping(method = {RequestMethod.GET}, path = {"/getAllPerson"})
    public ResponseEntity<List<PersonResponseDTO>> getAllPerson() {
        return new ResponseEntity<>(this.personContactService.getAllPerson(), HttpStatus.OK);
    }

    @Operation( summary= "Get all contact", description = "Get all contact from contact table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all contact"),
            @ApiResponse(responseCode = "404", description = "Not found - No contact was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping( method = {RequestMethod.GET},path = {"/getAllContact"})
    public ResponseEntity<List<ContactResponseDTO>> getAllContact() {
        return new ResponseEntity<>(this.personContactService.getAllContacts(), HttpStatus.OK);
    }

    @Operation( summary= "Delete contact", description = "Safe delete for specific contact(the id will be given), then status from 1 --> 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete contact"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to delete"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to delete with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(method = {RequestMethod.PUT},path = {"/deleteContact/{contactId}"})
    public ResponseEntity<ContactResponseDTO> deleteContact(@Valid @PathVariable("contactId") @Parameter(name="contactId", description = "Id of Contact to delete", example = "1") Long contactId) {
        this.personContactService.deleteContact(contactId); //void, no response parameter
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary= "Update contact", description = "Update the contact(the id will be taken")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such contact id to update"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping({"/updateContact/{id}"})
    public ResponseEntity<Contact> updateContact( @Valid @RequestBody ContactRequestDTO contactRequestDTO, @Valid @PathVariable("id") @Parameter(name="id", description = "Contact id to find and then delete", example = "1") Long id) {
        this.personContactService.updateContact(contactRequestDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary= "Get all person with contact", description = "Get all person with their contacts from person and contact table, working with GET!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(path = "/getAllPersonWithContacts")
    public ResponseEntity<List<PersonResponseDTO>> getAllPersonWithContacts() {
       return new ResponseEntity<>(personContactService.getAllPersonWithContacts(),HttpStatus.OK);
    }

    @Operation( summary= "Get all person", description = "Get one person with its all contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all person"),
            @ApiResponse(responseCode = "400", description = "Bad Request - No such person id to find its contact"),
            @ApiResponse(responseCode = "404", description = "Not found - No such contact to update with the given id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/getAllContactsOfPerson/{id}")
    public ResponseEntity<List<ContactResponseDTO>> getAllContactsOfPerson(@Valid @PathVariable("id") @Parameter(name="id", description = "Person id to find person", example = "1") Long id) {
        return new ResponseEntity<>(personContactService.getAllContactsOfPerson(id),HttpStatus.OK);
    }
}
