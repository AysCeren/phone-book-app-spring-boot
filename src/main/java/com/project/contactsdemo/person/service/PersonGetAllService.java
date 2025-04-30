package com.project.contactsdemo.person.service;

import com.project.contactsdemo.core.dto.CityResponseDTO;
import com.project.contactsdemo.core.dto.GenericDTO;
import com.project.contactsdemo.person.dto.PersonResponseDTO;
import com.project.contactsdemo.person.entity.Person;
import com.project.contactsdemo.core.exception.NoDataFoundException;
import com.project.contactsdemo.person.mapper.PersonMapper;
import com.project.contactsdemo.person.repository.PersonRepository;
import com.project.contactsdemo.core.cache.CacheService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonGetAllService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final CacheService cacheService;

    //URI Builder
    private final UriComponents uriComponents = UriComponentsBuilder
            .fromUriString("http://siciltest.gelbim.gov.tr:32158/mernis-cache/mernis-il/get-with-ilkodu")
            .queryParam("ilKodu", "{ilKodu}")
            .encode()
            .build();
    @CircuitBreaker(name = "exampleService")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenericDTO<List<PersonResponseDTO>> getAllPerson() throws NoDataFoundException {
        String key = "personResponseAll";
        String mapName = "personResponseAll";
        List<PersonResponseDTO> fromCache = (List<PersonResponseDTO>) cacheService.getFromCache(key,mapName);
        if((fromCache != null && fromCache.size() != 0)) {
            GenericDTO<List<PersonResponseDTO>> genericDTO = new GenericDTO<>(0,null);
            genericDTO.setBody(fromCache);
            return genericDTO;
        }
        List<Person> personList = new ArrayList<>(personRepository.findAll());
        if (personList.isEmpty()) {
            throw new NoDataFoundException("There is no person found");
        }
        List<PersonResponseDTO> allPerson= personList.stream()
                .map(personMapper::fromPersonToPersonResponseDto)
                .collect(Collectors.toList());
        GenericDTO<List<PersonResponseDTO>> genericDTO = new GenericDTO<>(0,null);
        genericDTO.setBody(allPerson);
        cacheService.saveToCache(allPerson, key, mapName);
        return genericDTO;
    }
}