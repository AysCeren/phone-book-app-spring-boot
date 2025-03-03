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
    public GenericDTO savePerson(PersonRequestDTO savePersonRequestDto) {
        Person person = personMapper.fromPersonRequestDTOToPersonEntity(savePersonRequestDto);
        this.personRepository.save(person);
        return new GenericDTO();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    //because it is read-only method, I want it run as non-transactional
    public GenericDTO<List<ContactResponseDTO>> getAllContacts() {
        List<Contact> contactList = new ArrayList<>(contactsRepository.findAll());
        if (contactList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        List<ContactResponseDTO> allContacts =  contactList.stream()
                .filter(contact-> contact.getStatus() ==1)
                .map(contactMapper::fromContactEntityToContactResponseDTO).collect(Collectors.toList());
        GenericDTO<List<ContactResponseDTO>> genericDTO = new GenericDTO<>();
        genericDTO.setBody(allContacts);
        return genericDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO<List<PersonResponseDTO>> getAllPerson() {
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        List<PersonResponseDTO> allPerson= personList.stream().map(personMapper::fromPersonToPersonResponseDto).collect(Collectors.toList());
        GenericDTO<List<PersonResponseDTO>> genericDTO = new GenericDTO<>();
        genericDTO.setBody(allPerson);
        return genericDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO<List<ContactResponseDTO>> getAllContactsOfPerson(Long personId){
        List<Contact> contactsForPerson = new ArrayList<>(contactsRepository.findContactsByPersonId(personId));
        if (contactsForPerson.isEmpty()) {
            throw new NoDataFoundException("No contacts found for person id: " + personId);
        }
        GenericDTO<List<ContactResponseDTO>> genericDTO = new GenericDTO<>();
        genericDTO.setBody(contactsForPerson.stream().map(contactMapper::fromContactEntityToContactResponseDTO).collect(Collectors.toList()));
        return genericDTO;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO saveContact(ContactRequestDTO saveContactRequestDto) {
        //öncelikle gelen contact'ın personId'sine bakalım
        Contact contact = contactMapper.fromContactRequestDTOToContactEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
        return new GenericDTO();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO<ContactResponseDTO> updateContact(ContactRequestDTO updatedContactRequestDTO, Long contactId) {
        Optional<Contact> updatedContact = contactsRepository.findById(contactId);
        if(updatedContact.isPresent()) {
            Contact newOne = contactMapper.fromContactRequestDTOToContactEntity(updatedContactRequestDTO);
            newOne.setId(updatedContact.get().getId());
            contactsRepository.save(newOne);
            GenericDTO<ContactResponseDTO> genericDTO = new GenericDTO<>();
            genericDTO.setBody(contactMapper.fromContactEntityToContactResponseDTO(newOne));
            return genericDTO;
            //TODO: Burada neden newOne yaparak aldığımızı bulalım.
        }
        else{
            throw new NoDataFoundException("No such a contact to update");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public GenericDTO<ContactResponseDTO> deleteContact(Long contactId) {
        Optional<Contact> deletedContact = contactsRepository.findById(contactId);
        if(deletedContact.isPresent()) {
            if(deletedContact.get().getStatus() == 0) { //already deleted
                throw new NoDataFoundException("No such a contact to delete");
            }
            deletedContact.get().setStatus(0);
            contactsRepository.save(deletedContact.get());
            GenericDTO<ContactResponseDTO> genericDTO = new GenericDTO<>();
            genericDTO.setBody(contactMapper.fromContactEntityToContactResponseDTO(deletedContact.get()));
            return genericDTO;
        }else
            throw new NoDataFoundException("No such a contact to delete");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public GenericDTO<List<PersonWithContactsDTO>> getAllPersonWithContacts(){
        List<Person> personListWithContacts = personRepository.findAllWithContacts();
        if (personListWithContacts.isEmpty()) {
            throw new NoDataFoundException("No contacts found");
        }else{
            GenericDTO<List<PersonWithContactsDTO>> genericDTO = new GenericDTO<>();
            //return personListWithContacts.stream().map(personMapper::fromPersonToPersonResponseForContactDTO).collect(Collectors.toList());
            genericDTO.setBody(personListWithContacts.stream()
                    .map(person -> {
                        PersonWithContactsDTO response = personMapper.fromPersonToPersonResponseForContactDTO(person);
                        response.setMessage(response.getContacts().size() + " contacts for " + person.getFirstName() + " " + person.getLastName());
                        return response;
                    })
                    .collect(Collectors.toList()));
            return genericDTO;
        }
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
