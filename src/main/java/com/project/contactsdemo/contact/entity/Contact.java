//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.project.contactsdemo.contact.entity;
import com.project.contactsdemo.person.entity.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact")
@Getter
@Setter
@SequenceGenerator(name = "contact_id_seq", initialValue = 1, allocationSize = 1)

public class Contact{

    @Id
    @GeneratedValue(
            generator = "contact_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Schema(name = "Contact ID", example = "10" , required = true)
    private Long id;

    @Column(
            name = "contact_name",
            nullable = false
    )
    @Schema(name = "Contact full name", example = "Ayse Ceren Coban", required = true)
    private String name;

    @Column(
            name = "contact_phone",
            nullable = true,
            unique = true //bu çalışmıyor sanırım! ilginç, buna bakalım
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
