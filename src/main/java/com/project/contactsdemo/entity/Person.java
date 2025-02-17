package com.project.contactsdemo.entity;

import com.project.contactsdemo.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "person")
@Table(name = "persons")

@Getter
@Setter
public class Person {
    @Id
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            unique = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            unique = false
    )
    private String lastName;

    @Column(
            name="birth_date",
            nullable =false,
            unique = false
    )
    private LocalDate birthDate;

    @Column(
            name = "birth_city",
            nullable = false
    )
    private String birthCity;

    @Enumerated(EnumType.STRING)
    @Column(
            name= "gender",
            nullable = false
    )
    private Gender gender;

    @Column(
            name = "phone_number",
            nullable = false,
            length = 15 //Note: Applies only if a string-valued column is used.
    )
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private List<Contact> contacts; //I am leaving it default and not making it private to reach from Contact
}
