package com.project.contactsdemo.person.repository;

import com.project.contactsdemo.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("Select p from Person p left join fetch p.contacts")
        //Note: left join shows all people even they do not have any contact
        //If we kept like 'join' --> it will show people who have at least one contact
    List<Person> findAllWithContacts();
}
