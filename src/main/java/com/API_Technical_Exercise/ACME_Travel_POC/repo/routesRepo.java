package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.routes;
import com.API_Technical_Exercise.ACME_Travel_POC.model.routesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface routesRepo extends JpaRepository<routes, routesId> {

    @Query(value = "SELECT distinct b.name FROM airlines_sandbox.airlines b\n" +
            "INNER JOIN  airlines_sandbox.routes a ON a.Airline_ID = b.Airline_ID WHERE b.Airline_ID = :airlineId", nativeQuery = true)
    public String getAirlineName(int airlineId);

    @Query(value = "SELECT distinct b.Model FROM airlines_sandbox.routes a\n" +
            "INNER JOIN airlines_sandbox.aircraft_type b on a.equipment = b.IATA_Code and a.Airline_ID = :airlineId", nativeQuery = true)
    public String getAirlineType(int airlineId);
}
