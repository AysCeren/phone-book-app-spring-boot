package com.project.contactsdemo.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GenericDTO<T>  {
    private T body;
    private int errorStatus; //eğer error varsa bu exception handler'da yazılacak
    private String errorMessage; //eğer error varsa bu exception handler'da doldurulacak

    public GenericDTO(int errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }

    public GenericDTO(T body, int errorStatus) {
        this.body = body;
        this.errorStatus = errorStatus;
    }
}
