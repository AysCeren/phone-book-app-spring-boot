package com.project.contactsdemo.repository;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Qualifier("person")
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("Select p from person p join fetch p.contacts")
    List<Person> findAllWithContacts();
}
