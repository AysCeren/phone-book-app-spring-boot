//package com.project.contactsdemo.logTest;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//
//@Test
//public void givenRequest_whenFetchTaxiFareRateCard_thanOK() {
//    TestRestTemplate testRestTemplate = new TestRestTemplate();
//    TaxiRide taxiRide = new TaxiRide(true, 10l);
//    String fare = testRestTemplate.postForObject(
//            URL + "calculate/",
//            taxiRide, String.class);
//
//    assertThat(fare, equalTo("200"));
//}