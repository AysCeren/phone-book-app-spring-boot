package com.project.contactsdemo.mapper;

import com.project.contactsdemo.dto.PersonWithContactsDTO;
import com.project.contactsdemo.entity.Person;
import com.project.contactsdemo.dto.PersonRequestDTO;
import com.project.contactsdemo.dto.PersonResponseDTO;
import com.project.contactsdemo.service.PersonContactService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(uses = PersonContactService.class)
public interface PersonMapper {
    //This method takes requestDTO and maps them to Person Entity
    //Note: We do not need to use Mapping, because names are the same.
    @Mapping(target = "birthDate", source = "birthDate")
    List<Person> fromPersonRequestDTOToPersonEntity(List<PersonRequestDTO> requestDto);
    @Mapping (target = "birthDate", source = "birthDate",  qualifiedByName = "stringToLocalDate")
    Person fromPersonRequestDTOToPersonEntity(PersonRequestDTO requestDto);
    //This method takes Person Entity and maps them to Person ResponseDTO
    @Mapping (target = "birthDate", source = "birthDate")
    List<PersonResponseDTO> fromPersonToPersonResponseDto(List<Person> person);
    @Mapping (target = "birthDate", source = "birthDate", qualifiedByName = "LocalDateToString")
    @Mapping(target="birthCity", source="birthCity", qualifiedByName = "birthCityName")
    PersonResponseDTO fromPersonToPersonResponseDto(Person person);
    @Mapping(target="birthCity", source="birthCity", qualifiedByName = "birthCityName")
    PersonWithContactsDTO fromPersonToPersonResponseForContactDTO(Person person);

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String birthDate) { //it will automatically be used by "fromPersonRequestDTOToPersonEntity"
        return birthDate != null ? LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
    }
    @Named("LocalDateToString")
    default String LocalDateToString(LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = birthDate.format(formatter);System.out.println(text);
        return text;
    }
}
