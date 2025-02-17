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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "contact_name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "contact_phone",
            length = 15,
            nullable = true,
            unique = false
    )
    private String phoneNo;

    @Column(
            name="contact_status",
            nullable = false,
            columnDefinition ="Integer default 0"
    )
    private Integer status;

}
