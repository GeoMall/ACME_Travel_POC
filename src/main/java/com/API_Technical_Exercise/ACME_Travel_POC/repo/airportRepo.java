package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface airportRepo extends PagingAndSortingRepository<airport,Integer>
{
    @Query(value="Select distinct a.Airport_ID, a.Name, a.City, a.Country, a.IATA, a.ICAO, a.Latitude, a.Longitude, a.Altitude, a.Timezone, a.DST, a.Tz_Timezone, a.Type, a.Source\n" +
            "FROM airlines_sandbox.airports a INNER JOIN airlines_sandbox.routes b ON a.airport_id = b.destination_airport_id\n" +
            "INNER JOIN airlines_sandbox.airlines c ON c.airline_id = b.airline_ID WHERE c.name = :airlineName ORDER BY name ASC /*:pageable*/", nativeQuery = true)
    List<airport> getDestinationAirport(String airlineName, PageRequest page);
}
