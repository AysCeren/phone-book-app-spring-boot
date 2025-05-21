package com.project.contactsdemo.contact.mapper;

import com.project.contactsdemo.contact.dto.ContactRequestDTO;
import com.project.contactsdemo.contact.dto.ContactResponseDTO;
import com.project.contactsdemo.contact.entity.Contact;
import com.project.contactsdemo.core.mapperhelpermethods.PersonPersonIDMapperMethods;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T11:28:14+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.2.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    private final PersonPersonIDMapperMethods personPersonIDMapperMethods;

    @Autowired
    public ContactMapperImpl(PersonPersonIDMapperMethods personPersonIDMapperMethods) {

        this.personPersonIDMapperMethods = personPersonIDMapperMethods;
    }

    @Override
    public Contact fromContactRequestDTOToContactEntity(ContactRequestDTO requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setPerson( personPersonIDMapperMethods.mapPersonIdToPerson( requestDto.getPersonId() ) );
        contact.setPhoneNo( requestDto.getPhoneNumber() );
        contact.setName( requestDto.getName() );

        return contact;
    }

    @Override
    public List<ContactResponseDTO> fromContactEntityToContactResponseDTO(List<Contact> contact) {
        if ( contact == null ) {
            return null;
        }

        List<ContactResponseDTO> list = new ArrayList<ContactResponseDTO>( contact.size() );
        for ( Contact contact1 : contact ) {
            list.add( fromContactEntityToContactResponseDTO( contact1 ) );
        }

        return list;
    }

    @Override
    public ContactResponseDTO fromContactEntityToContactResponseDTO(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();

        contactResponseDTO.setPersonId( personPersonIDMapperMethods.mapPersonToPersonId( contact.getPerson() ) );
        contactResponseDTO.setPhoneNumber( contact.getPhoneNo() );
        contactResponseDTO.setId( contact.getId() );
        contactResponseDTO.setName( contact.getName() );

        return contactResponseDTO;
    }
}
