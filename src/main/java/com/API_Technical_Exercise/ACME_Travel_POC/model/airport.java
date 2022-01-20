package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="airports")
public class airport implements Serializable {
    @Id
    @Column(
            name="airport_id",
            nullable = false
    )
    int airport_ID;

    @Column(
            name="name"
    )
    String name;

    @Column(
            name="city"
    )
    String city;

    @Column(
            name="country"
    )
    String country;

    @Column(
            name="iata"
    )
    String iata;

    @Column(
            name="icao"
    )
    String icao;

    @Column(
            name="latitude"
    )
    double latitude;

    @Column(
            name="longitude"
    )
    double longitude;

    @Column(
            name="altitude"
    )
    int altitude;

    @Column(
            name="timezone"
    )
    int timezone;

    @Column(
            name="dst"
    )
    char dst;

    @Column(
            name="tz_timezone"
    )
    String tz_timezone;

    @Column(
            name="type"
    )
    String type;

    @Column(
            name="source"
    )
    String source;

    public airport(){ }

    public airport(int airport_ID, String name, String city, String country, String iata, String icao, double latitude, double longitude, int altitude, int timezone, char dst, String tz_timezone, String type, String source) {
        this.airport_ID = airport_ID;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata = iata;
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timezone = timezone;
        this.dst = dst;
        this.tz_timezone = tz_timezone;
        this.type = type;
        this.source = source;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public char getDst() {
        return dst;
    }

    public void setDst(char dst) {
        this.dst = dst;
    }

    public String getTz_timezone() {
        return tz_timezone;
    }

    public void setTz_timezone(String tz_timezone) {
        this.tz_timezone = tz_timezone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAirport_ID() {
        return airport_ID;
    }

    public void setAirport_ID(int airport_ID) {
        this.airport_ID = airport_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
