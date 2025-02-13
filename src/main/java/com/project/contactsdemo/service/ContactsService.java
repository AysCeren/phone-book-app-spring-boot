package com.project.contactsdemo.service;

import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.exception.ContactNotFoundException;
import com.project.contactsdemo.repository.ContactsRepository;
import com.project.contactsdemo.requestdto.RequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsService {

    public ContactsRepository contactsRepository;
    //Constructor injection
    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Transactional
    public void savePerson(RequestDto savePersonRequestDto) {
        Person person = convertToEntity(savePersonRequestDto);
        this.contactsRepository.save(person);
    }

    @Transactional
    public List<RequestDto> getAllPerson() {
        List<Person> personList = new ArrayList();
        //TODO: Optional Class olarak döndüreceğiz
        personList.addAll(this.contactsRepository.findByStatus(true)); //TODO: How does this **** work?
        if (personList.isEmpty()) {
            throw new ContactNotFoundException("No contacts found");
        } else {
           List<RequestDto> requestDtoList = new ArrayList();
           for(Person person : personList) {
               requestDtoList.add(convertToRequestDto(person));
           }
           return requestDtoList;
        }
    }

    





    //TODO: Work on this with mapStructure
    private Person convertToEntity(RequestDto requestDto) {
        return null;
    }

    private RequestDto convertToRequestDto(Person person) {
        return null;
    }

}
