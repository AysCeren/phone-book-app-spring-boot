package com.project.contactsdemo.contact.repository;

import com.project.contactsdemo.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("contacts")
@Repository
//crud repo
public interface ContactRepository extends JpaRepository<Contact,Long> {
    @Query("SELECT p.contacts FROM Person p WHERE p.id = :id")
    List<Contact> findContactsByPersonId(@Param("id") Long id);
}
