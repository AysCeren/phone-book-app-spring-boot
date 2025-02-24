//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.project.contactsdemo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "contact")
@Table(name = "contacts")

@Getter
@Setter
public class Contact{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "contact_name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "contact_phone",
            nullable = true,
            unique = false
    )
    private String phoneNo;

    @Column(
            name="contact_status",
            nullable = false,
            columnDefinition ="Integer default 0"
    )
    private Integer status = 1; //default 1

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;  // This is the actual entity reference
}
