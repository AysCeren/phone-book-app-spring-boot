package com.project.contactsdemo.service;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.exception.ContactNotFoundException;
import com.project.contactsdemo.repository.ContactsRepository;
import com.project.contactsdemo.repository.PersonRepository;
import com.project.contactsdemo.requestdto.ContactDTO;
import com.project.contactsdemo.requestdto.PersonDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonContactService {

    private final PersonRepository personRepository;
    public ContactsRepository contactsRepository;
    //Constructor injection
    public PersonContactService(ContactsRepository contactsRepository, PersonRepository personRepository) {
        this.contactsRepository = contactsRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(PersonDTO savePersonRequestDto) {
        Person person = fromPersonDTOToEntity(savePersonRequestDto);
       this.personRepository.save(person);
    }

    @Transactional
    public List<PersonDTO> getAllPerson() {
        //TODO: Optional Class olarak döndüreceğiz
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new ContactNotFoundException("No contacts found");
        } else {
           List<PersonDTO> requestDtoList = new ArrayList();
           for(Person person : personList) {
               requestDtoList.add(fromPersonToPersonDto(person));
           }
           return requestDtoList;
        }
    }

    @Transactional
    public void saveContact(ContactDTO saveContactRequestDto) {
        Contact contact = fromContactDTOToEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
    }



    //TODO: Work on this with mapStructure
    //TODO: Can we work with Collections
    private Person fromPersonDTOToEntity(PersonDTO requestDto) {
        return null;
    }
    //TODO: Work on this with mapStructure
    private Contact fromContactDTOToEntity(ContactDTO requestDto) {
        return null;
    }

    private PersonDTO fromPersonToPersonDto(Person person) {
        return null;
    }
    private ContactDTO fromContactToContactDTO(Contact contact) {
        return null;
    }

}
