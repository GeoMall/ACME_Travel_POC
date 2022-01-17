package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Id;
import java.io.Serializable;

public class routesId implements Serializable {
    private int airline_id;
    private int source_airport_id;
    private int destination_airport_id;

    public routesId(int airline_id, int source_airport_id, int destination_airport_id) {
        this.airline_id = airline_id;
        this.source_airport_id = source_airport_id;
        this.destination_airport_id = destination_airport_id;
    }

    public routesId(){}

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public int getSource_airport_id() {
        return source_airport_id;
    }

    public void setSource_airport_id(int source_airport_id) {
        this.source_airport_id = source_airport_id;
    }

    public int getDestination_airport_id() {
        return destination_airport_id;
    }

    public void setDestination_airport_id(int destination_airport_id) {
        this.destination_airport_id = destination_airport_id;
    }
}
