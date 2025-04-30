package com.project.contactsdemo.contact.dto;

import lombok.Data;

@Data
public class ContactForPersonDTO {
    private Long id;
    private String name;
    private String phoneNo;
    //private Integer status; //gözüküyorsa bir olacağı için kesin göstermek gereksiz
}