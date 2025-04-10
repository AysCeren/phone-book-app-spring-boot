package com.project.contactsdemo.core.mapperhelpermethods;

import com.project.contactsdemo.core.exception.NoDataFoundException;
import com.project.contactsdemo.person.entity.Person;
import com.project.contactsdemo.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersonPersonIDMapperMethods {
    private final PersonRepository personRepository;

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
