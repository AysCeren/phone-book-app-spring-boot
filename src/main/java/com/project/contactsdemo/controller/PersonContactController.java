//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

import com.project.contactsdemo.entity.Contact;
//import com.example.service.ContactService;
import java.util.ArrayList;
import java.util.List;

import com.project.contactsdemo.requestdto.ContactDTO;
import com.project.contactsdemo.requestdto.PersonDTO;
import com.project.contactsdemo.service.PersonContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class PersonContactController {

    public PersonContactService personContactService;
    //Constructor injection (DI)
    public PersonContactController(PersonContactService personContactService) {
        this.personContactService = personContactService;
    }

    //Save operation with POST request
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> savePerson(@RequestBody PersonDTO personDTO) {
        this.personContactService.savePerson(personDTO);
        return new ResponseEntity(personDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> saveContact(@RequestBody ContactDTO savedContactDTO) {
        this.personContactService.saveContact(savedContactDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/contacts"}
    )
    public ResponseEntity<List<Contact>> getAllPerson() {
        List<Contact> contacts = new ArrayList(this.personContactService.getAllPerson());
        return new ResponseEntity(contacts, HttpStatus.OK);
    }


    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> deleteContact(@RequestBody ContactDTO contactDTO) {
        this.personContactService.deleteContact(contactDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping({"/contacts/"})
    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> updateContact( @RequestBody ContactDTO contactDTO) {
        this.personContactService.updateContact(contactDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO: Missing methods: getAllContacts(personId) and getAllPersonWithContacts()
}
