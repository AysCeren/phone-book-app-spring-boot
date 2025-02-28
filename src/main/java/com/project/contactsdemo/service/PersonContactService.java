package com.project.contactsdemo.service;

import com.project.contactsdemo.dto.*;
import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.exception.NoDataFoundException;
import com.project.contactsdemo.repository.ContactRepository;
import com.project.contactsdemo.repository.PersonRepository;
import com.project.contactsdemo.mapper.*;
//import jakarta.transaction.Transactional;
import org.mapstruct.Named;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonContactService{

    private final PersonRepository personRepository;
    private final ContactRepository contactsRepository;
    private final PersonMapper personMapper;
    private final ContactMapper contactMapper;

    //Constructor injection for all
    @Lazy
    public PersonContactService(ContactRepository contactsRepository, PersonRepository personRepository, PersonMapper personMapper, ContactMapper contactMapper) {
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
    public GenericDTO<List<ContactResponseDTO>> getAllContacts() {
        List<Contact> contactList = new ArrayList<>(contactsRepository.findAll());
        if (contactList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        List<ContactResponseDTO> x =  contactList.stream()
                .filter(contact-> contact.getStatus() ==1)
                .map(contactMapper::fromContactEntityToContactResponseDTO).collect(Collectors.toList());
        GenericDTO<ContactResponseDTO> genericDTO = new GenericDTO<>();
        genericDTO.setBody((ContactResponseDTO) x);
        genericDTO.setErrorStatus(0);
        genericDTO.setErrorMessage(null);
        return genericDTO;
    }
    public List<PersonResponseDTO> getAllPerson() {
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        //for-each ile tarama
        return personList.stream().map(personMapper::fromPersonToPersonResponseDto).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ContactResponseDTO> getAllContactsOfPerson(Long personId){
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
        List<Contact> contactsForPerson = new ArrayList<>(contactsRepository.findContactsByPersonId(personId));
        if (contactsForPerson.isEmpty()) {
            throw new NoDataFoundException("No contacts found for person id: " + personId);
        }
        return contactsForPerson.stream().map(contactMapper::fromContactEntityToContactResponseDTO).collect(Collectors.toList());
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveContact(ContactRequestDTO saveContactRequestDto) {
        //öncelikle gelen contact'ın personId'sine bakalım
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateContact(ContactRequestDTO updatedContactRequestDTO, Long contactId) {
        Optional<Contact> updatedContact = contactsRepository.findById(contactId);
        if(updatedContact.isPresent()) {
            Contact newOne = contactMapper.fromContactRequestDTOToContactEntity(updatedContactRequestDTO);
            newOne.setId(updatedContact.get().getId());
            contactsRepository.save(newOne);
        }
        else{
            throw new NoDataFoundException("No such a contact to update");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(Long contactId) {
        Optional<Contact> deletedContact = contactsRepository.findById(contactId);
        if(deletedContact.isPresent()) {
            deletedContact.get().setStatus(0);
            contactsRepository.save(deletedContact.get());
        }else
            throw new NoDataFoundException("No such a contact to delete");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PersonWithContactsDTO> getAllPersonWithContacts(){
        List<Person> personListWithContacts = personRepository.findAllWithContacts();
        if (personListWithContacts.isEmpty()) {
            throw new NoDataFoundException("No contacts found");
        }else
            //return personListWithContacts.stream().map(personMapper::fromPersonToPersonResponseForContactDTO).collect(Collectors.toList());
            return personListWithContacts.stream()
                    .map(person -> {
                        PersonWithContactsDTO response = personMapper.fromPersonToPersonResponseForContactDTO(person);
                        response.setMessage(response.getContacts().size() + " contacts for " + person.getFirstName() + " " + person.getLastName());
                        return response;
                    })
                    .collect(Collectors.toList());
    }

    @Named("mapPersonIdToPerson")
    public Person mapPersonIdToPerson(Long personId) {
        if (personId == null) {
            return null;
        }
        return personRepository.findById(personId).orElseThrow(() ->
                new NoDataFoundException("No such a person: " + personId)); //bunu RunTimeException'dan NoData'ya çevirdim. Önemli!
    }
    @Named("mapPersonToPersonId")
    public Long mapPersonToPersonId(Person person) {
        //person'un null olma durumu yok!
        return person.getId();
    }
}
