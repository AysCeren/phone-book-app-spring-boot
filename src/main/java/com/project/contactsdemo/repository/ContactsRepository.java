package com.project.contactsdemo.repository;

import com.project.contactsdemo.entity.Contact;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("contacts")
@Repository
public interface ContactsRepository extends JpaRepository<Contact,Long> {
    //List<Contact> findBy(Long personId);
    @Query("Select c from contact c join person p on p.contacts")
    List<Contact> findAllContactsForPerson(Long id);
}
