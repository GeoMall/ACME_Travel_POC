package com.API_Technical_Exercise.ACME_Travel_POC;

import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/acmeTravel")
public class acmeTravelController {
    private final acmeTravelService acmeTravelService;

    public acmeTravelController(acmeTravelService acmeTravelSvc){
        this.acmeTravelService = acmeTravelSvc;
    }

    @GetMapping(value="/flights", params = "depDate")
    public ResponseEntity<List<flightList>> getFlightList
            (@RequestParam String depDate) {
        List<flightList> flightList = acmeTravelService.getFlightListByDate(depDate);
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping(value = "/destinationAirports", params = {"pageNo", "pageSize", "airlineName"})
    public ResponseEntity<List<airport>> getDestinationList
            (@RequestParam(defaultValue = "0") int pageNo,
             @RequestParam(defaultValue = "10") int pageSize,
             @RequestParam String airlineName) {
        List<airport> airportList = acmeTravelService.getDestinationAirportByName(pageNo,pageSize,airlineName);
        return new ResponseEntity<>(airportList, HttpStatus.OK);
    }

    @PatchMapping(value = "/simulateTicketPurchase", params = {"flightId", "departureDate"})
    public ResponseEntity<HttpStatus>  buyFlightSeat
            (@RequestParam String flightId,
             @RequestParam String departureDate) {

        LocalDateTime depDate = convertDate(departureDate);
        acmeTravelService.updateSeatAvailability(flightId,depDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/amendFlightPrice", params = {"flightId", "departureDate", "newFlightPrice"})
    public ResponseEntity<HttpStatus> changeTicketPrice
            (@RequestParam String flightId,
             @RequestParam String departureDate,
             @RequestParam double newFlightPrice) {

        LocalDateTime depDate = convertDate(departureDate);
        acmeTravelService.updateFlightPrice(flightId,depDate,newFlightPrice);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/insertNewFlight", params = {"airlineId", "sourceAirportId", "destinationAirportId", "departureDate", "seatAvailability", "price"})
    public ResponseEntity<flightList> insertNewFlight
            (@RequestParam int airlineId,
             @RequestParam int sourceAirportId,
             @RequestParam int destinationAirportId,
             @RequestParam String departureDate,
             @RequestParam int seatAvailability,
             @RequestParam double price) {

        LocalDateTime depdate = convertDate(departureDate);

        flightList flightList = new flightList();
        flightList.setSeat_availability(seatAvailability);
        flightList.setPrice(price);

        flightList newFlight = acmeTravelService.insertNewRoute(flightList, depdate, airlineId,sourceAirportId,destinationAirportId);

        return new ResponseEntity<>(newFlight, HttpStatus.OK);
    }

    private LocalDateTime convertDate(String date)
    {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(Locale.US);

        return LocalDateTime.parse(date, formatter);
    }
}
