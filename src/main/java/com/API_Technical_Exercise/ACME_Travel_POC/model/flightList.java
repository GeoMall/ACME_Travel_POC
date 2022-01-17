package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="flights_georgem123")
@IdClass(flightListId.class)
public class flightList implements Serializable {
    @Id
    String flight_code;
    @Id
    LocalDateTime departure_date;

    String airline_name;
    String departure_airport;
    String destination_airport;
    String aircraft_type;
    int seat_availability;
    double price;

    public flightList() {}

    public flightList(String flight_code, LocalDateTime departure_date, String airline_name, String departure_airport, String destination_airport, String aircraft_type, int seat_availability, double price) {
        this.flight_code = flight_code;
        this.departure_date = departure_date;
        this.airline_name = airline_name;
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.aircraft_type = aircraft_type;
        this.seat_availability = seat_availability;
        this.price = price;
    }

    @Override
    public String toString() {
        return "flightList{" +
                "flight_code='" + flight_code + '\'' +
                ", departure_date=" + departure_date +
                ", airline_name='" + airline_name + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", destination_airport='" + destination_airport + '\'' +
                ", aircraft_type='" + aircraft_type + '\'' +
                ", seat_availability=" + seat_availability +
                ", price=" + price +
                '}';
    }

    public flightListId getId(){
        return new flightListId(flight_code,departure_date);
    }

    public void setId(String flight_code, LocalDateTime departure_date){
        this.flight_code = flight_code;
        this.departure_date = departure_date;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(String destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getAircraft_type() {
        return aircraft_type;
    }

    public void setAircraft_type(String aircraft_type) {
        this.aircraft_type = aircraft_type;
    }

    public int getSeat_availability() {
        return seat_availability;
    }

    public void setSeat_availability(int seat_availability) {
        this.seat_availability = seat_availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

