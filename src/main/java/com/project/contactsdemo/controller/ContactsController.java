//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

import com.project.contactsdemo.entity.Contact;
//import com.example.service.ContactService;
import java.util.ArrayList;
import java.util.List;

import com.project.contactsdemo.requestdto.PersonDTO;
import com.project.contactsdemo.service.ContactsService;
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
public class ContactsController {
    ContactsService contactService;

    public ContactsController(ContactsService contactService) {
        this.contactService = contactService;
    }

    //Save operation with POST request
    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> savePerson(@RequestBody PersonDTO contactDTO) {
        this.contactService.savePerson(contactDTO);
        return new ResponseEntity(contactDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/contacts"}
    )
    public ResponseEntity<List<Contact>> getAllPerson() {
        List<Contact> contacts = new ArrayList(this.contactService.getAllPerson());
        return new ResponseEntity(contacts, HttpStatus.OK);
    }


    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/contacts/{id}"}
    )
    public ResponseEntity<Contact> getContactById(@PathVariable("id") int id) {
        Contact contact = this.contactService.getContactById(id);
        return new ResponseEntity(contact, HttpStatus.FOUND);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/contacts/{name}"}
    )
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable("name") String name) {
        List<Contact> contacts = new ArrayList(this.contactService.getContactsByName(name));
        return new ResponseEntity(contacts, HttpStatus.FOUND);
    }



    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/contacts/{id}"}
    )
    public ResponseEntity<Contact> deleteById(@PathVariable("id") int id) {
        this.contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/contacts"}
    )
    public ResponseEntity<Contact> deleteAllContacts() {
        this.contactService.deleteAllContacts();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping({"/contacts/{id}"})
    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/contacts/{id}"}
    )
    public ResponseEntity<Contact> updateContact(@PathVariable("id") int id, @RequestBody Contacts contact) {
        this.contactService.updateContact(contact, id);
        return new ResponseEntity(contact, HttpStatus.OK);
    }
}
