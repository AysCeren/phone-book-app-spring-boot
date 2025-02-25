package com.project.contactsdemo.entity;

import com.project.contactsdemo.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "person")
@Table(name = "persons")

@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Person id", example = "22", required = true)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            unique = false
    )
    @Schema(name = "Person first name", example = "Ayse Ceren", required = true)
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            unique = false
    )
    @Schema(name = "lastName", example = "Coban", required = true)
    private String lastName;

    @Column(
            name="birth_date",
            nullable =false,
            unique = false
    )
    @Schema(name = "birthDate", example = "dd-MM-yyyy --> 15-03-2023", required = true)
    private LocalDate birthDate;

    @Column(
            name = "birth_city",
            nullable = false
    )
    @Schema(name = "birthCity", example = "3", required = true)
    private String birthCity;

    @Enumerated(EnumType.STRING)
    @Column(
            name= "gender",
            nullable = false
    )
    @Schema(name = "Gender", example = "MALE or FEMALE", required = true)
    private Gender gender;

    @Column(
            name = "phone_number",
            nullable = false
    )
    @Schema(name = "phoneNumber", example = "+905522568471", required = true)
    private String phoneNumber;

    @Column(
            name= "email",
            nullable = true
    )
    @Email
    @Schema(name = "email", example = "ceren@mail.com", required = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private List<Contact> contacts; //I am leaving it default and not making it private to reach from Contact
}
