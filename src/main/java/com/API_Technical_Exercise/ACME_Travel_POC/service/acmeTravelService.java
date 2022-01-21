package com.API_Technical_Exercise.ACME_Travel_POC.service;

import com.API_Technical_Exercise.ACME_Travel_POC.Exception.dataNotFoundException;
import com.API_Technical_Exercise.ACME_Travel_POC.model.*;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.acmeTravelRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.airportRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.routesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
public class acmeTravelService {
    private acmeTravelRepo acmeTravelRepo;
    private airportRepo airportRepo;
    private routesRepo routesRepo;

    @Autowired
    public acmeTravelService (acmeTravelRepo acmeTravelrpo, airportRepo airportRepo, routesRepo routesRepo) {
        this.airportRepo = airportRepo;
        this.acmeTravelRepo = acmeTravelrpo;
        this.routesRepo = routesRepo;
    }

    public List<flightList> getFlightListByDate(String depDate){
        return acmeTravelRepo.getFlightListByDate(depDate);
    }

    public List<airport> getDestinationAirportByName(int pageNo, int pageSize, String airlineName){
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
        int newSeatAvailability = flightList.get(0).getSeat_availability()-1;
        acmeTravelRepo.updateFlightSeatAvailability(newSeatAvailability, flightId, departureDate.toString());
    }

    public void updateFlightPrice(String flightId, LocalDateTime departureDate, double newPrice){
       //checking if flight exists
        List<flightList> flightList = getFlightListById(flightId,departureDate);

        if(flightList.isEmpty())
        {
            throw new dataNotFoundException("Flight by code: " + flightId + " was not found");
        }
        acmeTravelRepo.updateSeatPrice(newPrice,flightId, departureDate.toString());
    }

    public List<routes> getRouteById(int airlineId, int sourceAirportId, int destinationAirportId){
        routesId route = new routesId(airlineId, sourceAirportId, destinationAirportId);
        List<routesId> routesList = new ArrayList<routesId>();
        routesList.add(route);
        return routesRepo.findAllById(routesList);
    }

    public flightList insertNewRoute(flightList flight, LocalDateTime depTime, int airlineId, int sourceAirportId, int destinationAirportId){
        List<routes> routesList = getRouteById(airlineId, sourceAirportId, destinationAirportId);
        String flight_code;

        if (routesList.isEmpty())
        {
            throw new dataNotFoundException("routes with airlineId: " + airlineId + ", sourceAirportId: " + ", destinationAirportId: " + destinationAirportId + " could not be found");
        }

        flight.setDeparture_airport(routesList.get(0).getSource_airport());
        flight.setDestination_airport(routesList.get(0).getDestination_airport());

        flight_code = routesList.get(0).getAirline() + airlineId;
        flight.setId(flight_code,depTime);
        flight.setAirline_name(routesRepo.getAirlineName(airlineId));

        flight.setAircraft_type(routesRepo.getAirlineType(airlineId));

        acmeTravelRepo.save(flight);
        return flight;
    }


}
