package com.project.contactsdemo.mapper;

import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.requestdto.PersonRequestDTO;
import com.project.contactsdemo.requestdto.PersonResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonMapper {
    //This method takes requestDTO and maps them to Person Entity
    //Note: We do not need to use Mapping, because names are the same.
    @Mapping(target = "birthDate", source = "birthDate",  dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<Person> fromPersonRequestDTOToPersonEntity(List<PersonRequestDTO> requestDto);
    @Mapping (target = "birthDate", source = "birthDate",  dateFormat = "yyyy-MM-dd HH:mm:ss")
    Person fromPersonRequestDTOToPersonEntity(PersonRequestDTO requestDto);
    //This method takes Person Entity and maps them to Person ResponseDTO
    @Mapping (target = "birthDate", source = "birthDate",  dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<PersonResponseDTO> fromPersonToPersonResponseDto(List<Person> person);
    @Mapping (target = "birthDate", source = "birthDate",  dateFormat = "yyyy-MM-dd HH:mm:ss")
    PersonResponseDTO fromPersonToPersonResponseDto(Person person);
}
