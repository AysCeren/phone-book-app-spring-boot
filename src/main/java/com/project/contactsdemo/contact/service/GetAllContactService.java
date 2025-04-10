package com.project.contactsdemo.contact.service;

import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.contact.entity.Contact;
import com.project.contactsdemo.contact.mapper.ContactMapper;
import com.project.contactsdemo.contact.repository.ContactRepository;
import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.core.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public GenericDTO<List<ContactResponseDTO>> getAllContactDTO() {

        List<Contact> contactList = contactRepository.findAll();
        if (contactList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        //Note: Burada error durumunda, Generic DTO'nun doldurulmasını Global Exception Handler'a bıraktım
        GenericDTO<List<ContactResponseDTO>> genericDTO = new GenericDTO<List<ContactResponseDTO>>(0,null);
        List<ContactResponseDTO> allContact = contactList
                .stream()
                .map(contactMapper::fromContactEntityToContactResponseDTO)
                .toList();
        genericDTO.setBody(allContact);
        return genericDTO;
    }
}
