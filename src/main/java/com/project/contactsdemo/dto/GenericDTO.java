package com.project.contactsdemo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GenericDTO<T>  {
    T body;
    int errorStatus = 0; //eğer error varsa bu exception handler'da yazılacak
    String errorMessage = null; //eğer error varsa bu exception handler'da doldurulacak
    //Amacç: daha az setter çağırmak
}
