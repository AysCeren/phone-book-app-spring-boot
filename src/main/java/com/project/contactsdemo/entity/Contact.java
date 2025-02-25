//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.project.contactsdemo.entity;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "Contact ID", example = "10" , required = true)
    private Long id;

    @Column(
            name = "contact_name",
            nullable = false,
            unique = true
    )
    @Schema(name = "Contact full name", example = "Ayse Ceren Coban", required = true)
    private String name;

    @Column(
            name = "contact_phone",
            nullable = true,
            unique = false
    )
    @Schema(name = "Contact phone", example = "+905522568471", required = true)
    private String phoneNo;

    @Column(
            name="contact_status",
            nullable = false,
            columnDefinition ="Integer default 0"
    )
    @Schema(name = "Contact status", example = "0", required = false)
    private Integer status = 1; //default 1

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;  // This is the actual entity reference
}
