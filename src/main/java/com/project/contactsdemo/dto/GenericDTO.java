package com.project.contactsdemo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GenericDTO<T>  {
    T body;
    int errorStatus;
    String errorMessage;
}
