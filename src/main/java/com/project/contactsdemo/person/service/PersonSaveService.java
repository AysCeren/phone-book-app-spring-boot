package com.project.contactsdemo.person.service;

import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.dto.PersonRequestDTO;
import com.project.contactsdemo.person.dto.PersonResponseDTO;
import com.project.contactsdemo.person.entity.Person;
import com.project.contactsdemo.person.mapper.PersonMapper;
import com.project.contactsdemo.person.repository.PersonRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class PersonSaveService {
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    //URI Builder
    private final UriComponents uriComponents = UriComponentsBuilder
            .fromUriString("http://siciltest.gelbim.gov.tr:32158/mernis-cache/mernis-il/get-with-ilkodu")
            .queryParam("ilKodu", "{ilKodu}")
            .encode()
            .build();

    @CircuitBreaker(name = "exampleService")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO<PersonResponseDTO> savePerson(PersonRequestDTO savePersonRequestDto) {
        Person person = personMapper.fromPersonRequestDTOToPersonEntity(savePersonRequestDto);
        this.personRepository.save(person);
        PersonResponseDTO personResponseDTO = personMapper.fromPersonToPersonResponseDto(person);
        GenericDTO<PersonResponseDTO> genericDTO = new GenericDTO<>(0,null);
        genericDTO.setBody(personResponseDTO);
        return genericDTO;
    }
}
