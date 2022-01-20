package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity(name="routes")
@IdClass(routesId.class)
public class routes implements Serializable {
    @Id
    @Column(
            name="airline_id",
            nullable = false
    )
    int airline_id;

    @Id
    @Column(
            name="source_airport_id",
            nullable = false
    )
    int source_airport_id;

    @Id
    @Column(
            name="destination_airport_id",
            nullable = false
    )
    int destination_airport_id;

    @Column(
            name="airline",
            nullable = false
    )
    String airline;

    @Column(
            name="source_airport",
            nullable = false
    )
    String source_airport;

    @Column(
            name="destination_airport",
            nullable = false
    )
    String destination_airport;

    @Column(
            name="codeshare",
            nullable = true
    )
    String codeshare;

    @Column(
            name="stops",
            nullable = true
    )
    int stops;

    @Column(
            name="equipment",
            nullable = true
    )
    String equipment;

    public routes(){}

    public routes(int airline_id, int source_airport_id, int destination_airport_id, String airline, String source_airport, String destination_airport, String codeshare, int stops, String equipment) {
        this.airline_id = airline_id;
        this.source_airport_id = source_airport_id;
        this.destination_airport_id = destination_airport_id;
        this.airline = airline;
        this.source_airport = source_airport;
        this.destination_airport = destination_airport;
        this.codeshare = codeshare;
        this.stops = stops;
        this.equipment = equipment;
    }

    public routesId getId()
    {
        return new routesId(airline_id, source_airport_id, destination_airport_id);
    }

    public void setId(int airline_id, int source_airport_id, int destination_airport_id)
    {
        this.airline_id = airline_id;
        this.source_airport_id = source_airport_id;
        this.destination_airport_id = destination_airport_id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSource_airport() {
        return source_airport;
    }

    public void setSource_airport(String source_airport) {
        this.source_airport = source_airport;
    }

    public String getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(String destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getCodeshare() {
        return codeshare;
    }

    public void setCodeshare(String codeshare) {
        this.codeshare = codeshare;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
