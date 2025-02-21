package com.project.contactsdemo.mapper;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.requestdto.ContactRequestDTO;
import com.project.contactsdemo.requestdto.ContactResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface ContactMapper {
    @Mapping(source = "personId", target="person", qualifiedByName = "mapPersonIdToPerson")
    @Mapping(target = "phoneNo", source = "phoneNumber")
    List<Contact> fromContactRequestDTOToContactEntity(List<ContactRequestDTO> requestDto);
    @Mapping(target = "phoneNo", source = "phoneNumber")
    Contact fromContactRequestDTOToContactEntity(ContactRequestDTO requestDto);
    @Mapping(target = "phoneNumber", source = "phoneNo")
    List<ContactResponseDTO> fromContactEntityToContactResponseDTO(List<Contact> contact);
    @Mapping(target = "phoneNumber", source = "phoneNo")
    ContactResponseDTO fromContactEntityToContactResponseDTO(Contact contact);
}
