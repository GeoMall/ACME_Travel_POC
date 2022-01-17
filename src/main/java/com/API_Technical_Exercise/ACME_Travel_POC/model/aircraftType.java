package com.API_Technical_Exercise.ACME_Travel_POC.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="aircraftType")
public class aircraftType implements Serializable {
    @Id
    String icao_code;
    String iata_code;
    String model;

    public aircraftType(String icao_code, String iata_code, String model) {
        this.icao_code = icao_code;
        this.iata_code = iata_code;
        this.model = model;
    }

    public aircraftType(){}

    public String getIcao_code() {
        return icao_code;
    }

    public void setIcao_code(String icao_code) {
        this.icao_code = icao_code;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
