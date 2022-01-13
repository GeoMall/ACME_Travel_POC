package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface acmeTravelRepo
        extends JpaRepository<flightList, flightListId> {

    @Query(value="SELECT * FROM airlines_sandbox.flights_georgem123 WHERE Departure_Date LIKE %:depDate%", nativeQuery = true)
    List<flightList> getFlightListByDate(String depDate);
}
