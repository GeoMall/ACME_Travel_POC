package com.API_Technical_Exercise.ACME_Travel_POC.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class flightListId implements Serializable {
    private String flight_code;
    private LocalDateTime departure_date;

    public flightListId(String flight_code, LocalDateTime departure_date) {
        this.flight_code = flight_code;
        this.departure_date = departure_date;
    }

    private flightListId() {
    }

    public String getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(String flight_code) {
        this.flight_code = flight_code;
    }

    public LocalDateTime getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDateTime departure_date) {
        this.departure_date = departure_date;
    }
}
