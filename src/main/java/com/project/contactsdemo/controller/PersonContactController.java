//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.project.contactsdemo.controller;

//import com.example.service.ContactService;
import java.util.ArrayList;
import java.util.List;

        import com.project.contactsdemo.entity.Contact;
        import com.project.contactsdemo.requestdto.ContactRequestDTO;
import com.project.contactsdemo.requestdto.PersonRequestDTO;
import com.project.contactsdemo.service.PersonContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            path = {"/contacts1"}
    )//model attribute
    public ResponseEntity<Contact> savePerson(@RequestBody PersonRequestDTO personRequestDTO) {
        this.personContactService.savePerson(personRequestDTO);
        return new ResponseEntity(personRequestDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            path = {"/contacts2"}
    )
    public ResponseEntity<Contact> saveContact(@RequestBody ContactRequestDTO savedContactRequestDTO) {
        this.personContactService.saveContact(savedContactRequestDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/contacts3"}
    )
    public ResponseEntity<List<Contact>> getAllPerson() {
        List<Contact> contacts = new ArrayList(this.personContactService.getAllPerson());
        return new ResponseEntity(contacts, HttpStatus.OK);
    }


    @RequestMapping(
            method = {RequestMethod.DELETE},
            path = {"/contacts4"}
    )
    public ResponseEntity<Contact> deleteContact(@RequestBody ContactRequestDTO contactRequestDTO) {
        this.personContactService.deleteContact(contactRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping({"/contacts/"})
    /*
    @RequestMapping(
            method = {RequestMethod.PUT},
            path = {"/contacts5"}
    )

     */
    public ResponseEntity<Contact> updateContact( @RequestBody ContactRequestDTO contactRequestDTO) {
        this.personContactService.updateContact(contactRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    //TODO: Missing methods: getAllContacts(personId) and getAllPersonWithContacts()
    public ResponseEntity<List<Contact>> getAllContact(Long personId) {
        List<Contact> contacts = new ArrayList(this.personContactService.getAllPerson());
    }
    */

}
