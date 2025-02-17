package com.project.contactsdemo.repository;

import com.project.contactsdemo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contact,Long> {

    List<Contact> findByStatus(Integer status);

    List<Contact> findContactsById(Long personId);
    //Burada anlamadığım hangi column'un sorgusu, onu anlayamıyorum. Kontrol edelim!
}
