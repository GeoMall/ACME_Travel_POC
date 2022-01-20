package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="airline")
public class airline implements Serializable {

    @Id
    @Column(
            name="airline_id",
            nullable = false
    )
    int airline_id;

    @Column(
            name="name"
    )
    String name;

    @Column(
            name="alias"
    )
    String alias;

    @Column(
            name="iata"
    )
    String iata;

    @Column(
            name="icao"
    )
    String icao;

    @Column(
            name="callsign"
    )
    String callsign;

    @Column(
            name="country"
    )
    String country;

    @Column(
            name="active"
    )
    boolean active;

    public airline(){}

    public airline(int airline_id, String name, String alias, String iata, String icao, String callsign, String country, boolean active) {
        this.airline_id = airline_id;
        this.name = name;
        this.alias = alias;
        this.iata = iata;
        this.icao = icao;
        this.callsign = callsign;
        this.country = country;
        this.active = active;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
