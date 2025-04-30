package com.project.contactsdemo.core.mapperhelpermethods;

import com.project.contactsdemo.core.dto.CityResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
@Configuration
@RequiredArgsConstructor
public class BirthCityConverter {

    private final RestTemplate restTemplate;
    //URI Builder
    private final UriComponents uriComponents = UriComponentsBuilder
            .fromUriString("http://siciltest.gelbim.gov.tr:32158/mernis-cache/mernis-il/get-with-ilkodu")
            .queryParam("ilKodu", "{ilKodu}")
            .encode()
            .build();
    @Named("birthCityName")
    @CircuitBreaker(name = "restTemplateService") //TODO: fallBackMethod'u nereye nasıl tanımlayacağını sor
    public  String birthCityName(String birthCity) {
        URI uri = uriComponents.expand( birthCity).toUri();
        CityResponseDTO cityResponseDTO = restTemplate.getForObject(uri, CityResponseDTO.class); //neye döneceğini burada class formatında belirtiriz.
        return Objects.requireNonNull(cityResponseDTO).getIlAdi();
    }

}
