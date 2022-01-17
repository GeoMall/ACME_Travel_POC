package com.API_Technical_Exercise.ACME_Travel_POC;

import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/acmeTravel")
public class acmeTravelController {
    private final acmeTravelService acmeTravelService;

    public acmeTravelController(acmeTravelService acmeTravelSvc){
        this.acmeTravelService = acmeTravelSvc;
    }

    @GetMapping(value="/flights", params = "depDate")
    public ResponseEntity<List<flightList>> getFlightList
            (@RequestParam String depDate)
    {
        List<flightList> flightList = acmeTravelService.getFlightListByDate(depDate);
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping(value = "/destinationAirports", params = {"pageNo", "pageSize", "airlineName"})
    public ResponseEntity<List<airport>> getDestinationList
            (@RequestParam(defaultValue = "0") int pageNo,
             @RequestParam(defaultValue = "10") int pageSize,
             @RequestParam String airlineName)
    {
        List<airport> airportList = new ArrayList<airport>();
        airportList = acmeTravelService.getDestinationAirportByName(pageNo,pageSize,airlineName);
        return new ResponseEntity<>(airportList, HttpStatus.OK);
    }
}
