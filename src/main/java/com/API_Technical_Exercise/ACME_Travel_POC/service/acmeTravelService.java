package com.API_Technical_Exercise.ACME_Travel_POC.service;

import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.acmeTravelRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.airportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

}
