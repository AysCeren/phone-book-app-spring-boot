//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

import java.util.List;
import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.requestdto.ContactRequestDTO;
import com.project.contactsdemo.requestdto.ContactResponseDTO;
import com.project.contactsdemo.requestdto.PersonRequestDTO;
import com.project.contactsdemo.requestdto.PersonResponseDTO;
import com.project.contactsdemo.service.PersonContactService;
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

    //Save operation with POST request
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/savePerson"}
    )//model attribute
    public ResponseEntity<PersonResponseDTO> savePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        this.personContactService.savePerson(personRequestDTO); //void, no response parameter
        return new ResponseEntity<>( HttpStatus.CREATED);

    }

    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/saveContact"}
    )
    public ResponseEntity<ContactResponseDTO> saveContact(@Valid @RequestBody ContactRequestDTO savedContactRequestDTO) {
        this.personContactService.saveContact(savedContactRequestDTO); //void, no response parameter
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/getAllPerson"}
    )
    public ResponseEntity<List<PersonResponseDTO>> getAllPerson() {
        return new ResponseEntity<>(this.personContactService.getAllPerson(), HttpStatus.OK);
    }
    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/getAllContact"}
    )
    public ResponseEntity<List<ContactResponseDTO>> getAllContact() {
        return new ResponseEntity<>(this.personContactService.getAllContacts(), HttpStatus.OK);
    }


    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/deleteContact/{contactId}"}
    )
    public ResponseEntity<ContactResponseDTO> deleteContact(@Valid @PathVariable Long contactId) {
        this.personContactService.deleteContact(contactId); //void, no response parameter
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping({"/updateContact/{id}"})
    public ResponseEntity<Contact> updateContact( @Valid @RequestBody ContactRequestDTO contactRequestDTO, @PathVariable Long id) {
        this.personContactService.updateContact(contactRequestDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/getAllPersonWithContacts")
    public ResponseEntity<List<PersonResponseDTO>> getAllPersonWithContacts() {
       return new ResponseEntity<>(personContactService.getAllPersonWithContacts(),HttpStatus.OK);
    }

    @GetMapping("/getAllContactsOfPerson/{id}")
    public ResponseEntity<List<ContactResponseDTO>> getAllContactsOfPerson(@PathVariable Long id) {
        return new ResponseEntity<>(personContactService.getAllContactsOfPerson(id),HttpStatus.OK);
    }
}
