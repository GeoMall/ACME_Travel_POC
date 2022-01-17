package com.API_Technical_Exercise.ACME_Travel_POC.service;

import com.API_Technical_Exercise.ACME_Travel_POC.Exception.dataNotFoundException;
import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightListId;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.acmeTravelRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.airportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
public class acmeTravelService {
    private acmeTravelRepo acmeTravelRepo;
    private airportRepo airportRepo;

    @Autowired
    public acmeTravelService (acmeTravelRepo acmeTravelrpo, airportRepo airportRepo) {
        this.airportRepo = airportRepo;
        this.acmeTravelRepo = acmeTravelrpo;
    }

    public List<flightList> getFlightListByDate(String depDate){
        System.out.println(depDate);
        return acmeTravelRepo.getFlightListByDate(depDate);
    }

    public List<airport> getDestinationAirportByName(int pageNo, int pageSize, String airlineName){
        System.out.println(airlineName);
        PageRequest page = PageRequest.of(pageNo, pageSize);
        return airportRepo.getDestinationAirport(airlineName, page);
    }

    public List<flightList> getFlightListById(String flightId, LocalDateTime departureDate){
        flightListId flight = new flightListId(flightId,departureDate);
        List<flightListId> flightListId = new ArrayList<flightListId>();
        flightListId.add(flight);
        return acmeTravelRepo.findAllById(flightListId);
    }

    public void updateSeatAvailability(String flightId, LocalDateTime departureDate) {
        List<flightList> flightList = getFlightListById(flightId,departureDate);

        if(flightList.isEmpty())
        {
            throw new dataNotFoundException("Flight by code: " + flightId + " was not found");
        }
        int newSeatAvailability = flightList.get(0).getSeat_availability()+1;
        acmeTravelRepo.updateFlightSeatAvailability(newSeatAvailability, flightId);
    }

    public void updateFlightPrice(String flightId, LocalDateTime departureDate, double newPrice){
        List<flightList> flightList = getFlightListById(flightId,departureDate);

        if(flightList.isEmpty())
        {
            throw new dataNotFoundException("Flight by code: " + flightId + " was not found");
        }
        acmeTravelRepo.updateSeatPrice(newPrice,flightList.get(0).getId().getFlight_code());
    }

}
