package com.project.contactsdemo.service;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.exception.TrainingException;
import com.project.contactsdemo.repository.ContactsRepository;
import com.project.contactsdemo.repository.PersonRepository;
import com.project.contactsdemo.requestdto.ContactRequestDTO;
import com.project.contactsdemo.requestdto.PersonRequestDTO;
import com.project.contactsdemo.mapper.*;
//import jakarta.transaction.Transactional;
import com.project.contactsdemo.requestdto.PersonResponseDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonContactService {

    private final PersonRepository personRepository;
    private final ContactsRepository contactsRepository;
    private final PersonMapper personMapper;
    private final ContactMapper contactMapper;

    //Constructor injection for all
    @Lazy
    public PersonContactService(ContactsRepository contactsRepository, PersonRepository personRepository,PersonMapper personMapper, ContactMapper contactMapper) {
        this.contactsRepository = contactsRepository;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.contactMapper = contactMapper;
    }

    //TODO: Transactional'lara yalıtım ve yayılma ekleyelim.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePerson(PersonRequestDTO savePersonRequestDto) {
        Person person = personMapper.fromPersonRequestDTOToPersonEntity(savePersonRequestDto);
        this.personRepository.save(person);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    //because it is read-only method, I want it run as non-transactional
    public List<PersonResponseDTO> getAllPerson() {
        //TODO: Optional Class olarak döndüreceğiz
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new TrainingException("No contacts found");
        }
        return personList.stream().map(personMapper::fromPersonToPersonResponseDto).collect(Collectors.toList());
    }

    public List<Contact> getAllContact(Long personId){
        /*Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            List<Contact> contactList = person.get().getContacts();
            if (contactList.isEmpty()) {
                throw new TrainingException("No contacts found for " + personId);
            }
            return contactList;
        }
        throw new TrainingException("No such a person id with: " + personId);*/
        /*
        if(!personRepository.existsById(personId)){
            throw new TrainingException("No such a person with id "+personId);
        }
        List<Contact> contactList = new ArrayList<>(contactsRepository.findByPersonId(personId));
        if (contactList.isEmpty()) {
            throw new TrainingException("No contacts found");
        }
        return contactList;

         */
        List<Contact> contactsForPerson = new ArrayList<>(contactsRepository.findAllContactsForPerson(personId));
        if (contactsForPerson.isEmpty()) {
            throw new TrainingException("No contacts found");
        }
        return contactsForPerson;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveContact(ContactRequestDTO saveContactRequestDto) {
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateContact(ContactRequestDTO updatedContactRequestDTO) {
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(updatedContactRequestDTO);
        //check for the existence
        if(!contactsRepository.existsById(contact.getId())){
            throw new TrainingException("No such a contact to update");
        }
        else{
            contact.setName(updatedContactRequestDTO.getName());
            contact.setPhoneNo(updatedContactRequestDTO.getPhoneNumber());
            contactsRepository.save(contact);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(ContactRequestDTO deletedContactRequestDTO) {
        Contact deletedContact = contactMapper.fromContactRequestDTOToContactEntity(deletedContactRequestDTO);
        if(!contactsRepository.existsById(deletedContact.getId())){
            throw new TrainingException("No such a contact to delete");
        }else {
            deletedContact.setStatus(0); //0: deleted, 1: exist
            contactsRepository.save(deletedContact);
        }
    }

    public List<Person> getAllPersonWithContacts(){
        List<Person> personListWithContacts = new ArrayList<>(personRepository.findAllWithContacts());
        if (personListWithContacts.isEmpty()) {
            throw new TrainingException("No contacts found");
        }else
            return personListWithContacts;
    }
}
