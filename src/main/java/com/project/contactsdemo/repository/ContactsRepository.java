package com.project.contactsdemo.repository;

import com.project.contactsdemo.entity.Contact;
import com.project.contactsdemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ContactsRepository extends JpaRepository<Contact,Long> {

    Collection<? extends Person> findByStatus(boolean status);
}
