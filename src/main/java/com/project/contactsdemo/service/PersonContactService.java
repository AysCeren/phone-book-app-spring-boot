package com.project.contactsdemo.service;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.exception.TrainingException;
import com.project.contactsdemo.repository.ContactsRepository;
import com.project.contactsdemo.repository.PersonRepository;
import com.project.contactsdemo.requestdto.ContactDTO;
import com.project.contactsdemo.requestdto.PersonDTO;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonContactService {

    public PersonRepository personRepository;
    public ContactsRepository contactsRepository;

    //Constructor injection
    public PersonContactService(ContactsRepository contactsRepository, PersonRepository personRepository) {
        this.contactsRepository = contactsRepository;
        this.personRepository = personRepository;
    }

    //TODO: Transactional'lara yalıtım ve yayılma ekleyelim.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePerson(PersonDTO savePersonRequestDto) {
        Person person = fromPersonDTOToEntity(savePersonRequestDto);
       this.personRepository.save(person);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    //because it is read-only method, I want it run as non-transactional
    public List<PersonDTO> getAllPerson() {
        //TODO: Optional Class olarak döndüreceğiz
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new TrainingException("No contacts found");
        } else {
           List<PersonDTO> requestDtoList = new ArrayList();
           for(Person person : personList) {
               requestDtoList.add(fromPersonToPersonDto(person));
           }
           return requestDtoList;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveContact(ContactDTO saveContactRequestDto) {
        Contact contact = fromContactDTOToEntity(saveContactRequestDto);
        this.contactsRepository.save(contact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateContact(ContactDTO updatedContactDTO) {
        Contact updatedContact = getContact(updatedContactDTO);
        this.contactsRepository.save(updatedContact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(ContactDTO deletedContactDTO) {
        Contact deletedContact = getContact(deletedContactDTO);
        deletedContact.setStatus(0); //0: deleted, 1: exist
        contactsRepository.save(deletedContact);
    }

    public Contact getContact(ContactDTO contactDTO) {
        Contact contact = fromContactDTOToEntity(contactDTO);
        assert contact != null; //TODO: Burada gelen dto'nun boş olup olmadığı kontrol edilecek mi?
        Optional<Contact> deletedContact = contactsRepository.findById(contact.getId());
        if (deletedContact.isEmpty()) {
            throw new TrainingException("No contact found");
        }
        else{
            return contact;
        }
    }
    /*
    @Transactional
    public List<ContactDTO> getAllContacts(Long personId) {
        //TODO: Optional Class olarak döndüreceğiz
        List<Contact> contactList = new ArrayList<>(contactsRepository.findByPersonId(personId));
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
     */
    //TODO: Work on this with mapStructure
    //TODO: Can we work with Collections?
    private Person fromPersonDTOToEntity(PersonDTO requestDto) {
        return null;
    }
    //TODO: Work on this with mapStructure
    private Contact fromContactDTOToEntity(ContactDTO requestDto) {
        return null;
    }

    //TODO: Eğer burada ResponseDTO diye ayrı bir DTO class oluşturacaksa Entity'yi yanıt olarak o class'a döndüreceğiz (bu iki method için)
    private PersonDTO fromPersonToPersonDto(Person person) {
        return null;
    }
    private ContactDTO fromContactToContactDTO(Contact contact) {
        return null;
    }

}
