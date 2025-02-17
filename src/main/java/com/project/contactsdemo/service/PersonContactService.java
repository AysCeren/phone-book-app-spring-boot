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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonContactService {

    private final PersonRepository personRepository;
    private final ContactsRepository contactsRepository;
    private final PersonMapper personMapper;
    private final ContactMapper contactMapper;

    //Constructor injection for all
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
        return personMapper.fromPersonToPersonResponseDto(personList);
    }

//    public List<Contact> getAllContact(Long personId){
//       List<Contact> contactList = contactsRepository.findContactsById(personId);
//    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveContact(ContactRequestDTO saveContactRequestDto) {
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateContact(ContactRequestDTO updatedContactRequestDTO) {
        Contact updatedContact = getContact(updatedContactRequestDTO);
        this.contactsRepository.save(updatedContact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(ContactRequestDTO deletedContactRequestDTO) {
        Contact deletedContact = getContact(deletedContactRequestDTO);
        deletedContact.setStatus(0); //0: deleted, 1: exist
        contactsRepository.save(deletedContact);
    }

    public Contact getContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(contactRequestDTO);
        assert contact != null; //TODO: Burada gelen dto'nun boş olup olmadığı kontrol edilecek mi?
        Optional<Contact> deletedContact = contactsRepository.findById(contact.getId());
        if (deletedContact.isEmpty()) {
            throw new TrainingException("No contact found");
        }
        return contact;
    }
}
