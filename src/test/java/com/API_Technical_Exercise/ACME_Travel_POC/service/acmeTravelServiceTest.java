package com.API_Technical_Exercise.ACME_Travel_POC.service;

import com.API_Technical_Exercise.ACME_Travel_POC.Exception.dataNotFoundException;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.model.routes;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.acmeTravelRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.airportRepo;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.routesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class acmeTravelServiceTest {

    @Mock
    private acmeTravelRepo acmeTravelRepo;
    @Mock
    private airportRepo airportRepo;
    @Mock
    private routesRepo routesRepo;
    private acmeTravelService acmeTravelService;

    @BeforeEach
    void setUp() {
        acmeTravelService = new acmeTravelService(acmeTravelRepo, airportRepo, routesRepo);
    }

    @Test
    void canGetFlightListByDate() {
        acmeTravelService.getFlightListByDate("2017-01-01 20:30:30");
        verify(acmeTravelRepo).getFlightListByDate("2017-01-01 20:30:30");
    }

    @Test
    void canGetDestinationAirportByName() {
        acmeTravelService.getDestinationAirportByName(0, 10, "RyanAir");
        verify(airportRepo).getDestinationAirport("RyanAir", PageRequest.of(0,10));
    }


    @Test
    void canUpdateSeatAvailabilitySuccessfully() {
        String flightId = "BA001";
        String depDate = "2017-03-15 20:30:00";

        flightList flightList =  new flightList
                ("BA001",
                        convertDate("2017-03-15 20:30:00"),
                "British Airways",
                "JFK",
                "LCY",
                "Airbus A318",
                36,
                1400.01);

        List<flightList> list =  new ArrayList<flightList>();
        list.add(flightList);

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(list).when(acmeTravelServiceSpy).getFlightListById(flightId,convertDate(depDate));
        acmeTravelServiceSpy.updateSeatAvailability(flightId, convertDate(depDate));

        verify(acmeTravelRepo).updateFlightSeatAvailability(flightList.getSeat_availability()+1,flightId,convertDate(depDate).toString());
    }

    @Test
    void canUpdateSeatAvailabilityUnsuccessfully(){
        String flightId = "BA001";
        String depDate = "2017-03-15 20:30:00";
        List<flightList> flightList = new ArrayList<flightList>();
        int seatavil = 0;

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(flightList)
                .when(acmeTravelServiceSpy)
                .getFlightListById(flightId,convertDate(depDate));

        assertThatThrownBy(() -> acmeTravelServiceSpy.updateSeatAvailability(flightId, convertDate(depDate)))
                .isInstanceOf(dataNotFoundException.class)
                .hasMessageContaining("Flight by code: " + flightId + " was not found");

        verify(acmeTravelRepo, never()).updateFlightSeatAvailability(seatavil,flightId,convertDate(depDate).toString());
    }

    @Test
    void canUpdateFlightPriceSuccessfully() {
        String flightId = "BA001";
        String depDate = "2017-03-15 20:30:00";
        double newPrice = 1350.00;

        flightList flightList =  new flightList
                ("BA001",
                        convertDate("2017-03-15 20:30:00"),
                        "British Airways",
                        "JFK",
                        "LCY",
                        "Airbus A318",
                        36,
                        1400.01);

        List<flightList> list =  new ArrayList<flightList>();
        list.add(flightList);

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(list).when(acmeTravelServiceSpy).getFlightListById(flightId,convertDate(depDate));
        acmeTravelServiceSpy.updateFlightPrice(flightId,convertDate(depDate),newPrice);

        verify(acmeTravelRepo).updateSeatPrice(newPrice,flightId,convertDate(depDate).toString());
    }

    @Test
    void canUpdateFlightPriceUnSuccessfully() {
        String flightId = "BA001";
        String depDate = "2017-03-15 20:30:00";
        double newPrice = 1350.00;
        List<flightList> list =  new ArrayList<flightList>();

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(list).when(acmeTravelServiceSpy).getFlightListById(flightId,convertDate(depDate));

        assertThatThrownBy(() -> acmeTravelServiceSpy.updateFlightPrice(flightId, convertDate(depDate),newPrice))
                .isInstanceOf(dataNotFoundException.class)
                .hasMessageContaining("Flight by code: " + flightId + " was not found");

        verify(acmeTravelRepo, never()).updateSeatPrice(newPrice,flightId,convertDate(depDate).toString());
    }

    @Test
    void canGetRouteById() {
        acmeTravelService.getRouteById(4296, 599, 353);
        verify(routesRepo).findAllById(anyList());
    }

    @Test
    void canGetFlightById(){
        acmeTravelService.getFlightListById("BA001",
                convertDate("2017-03-15 20:30:00"));
        verify(acmeTravelRepo).findAllById(anyList());
    }

    @Test
    void canInsertNewRouteSuccessfully() {
        int airlineId = 4296;
        int sourceAirportId = 599;
        int destinationAirportId = 353;
        int seatAvailabilty = 100;
        double price = 50.00;
        LocalDateTime depTime = convertDate("2017-03-15 20:30:00");

        flightList flight = new flightList();
        flight.setSeat_availability(seatAvailabilty);
        flight.setPrice(price);

        routes route = new routes();
        route.setAirline("FR");
        List<routes> routelst = new ArrayList<routes>();
        routelst.add(route);

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(routelst).when(acmeTravelServiceSpy)
                .getRouteById(airlineId,sourceAirportId,destinationAirportId);
        acmeTravelServiceSpy.insertNewRoute(flight,depTime,airlineId,sourceAirportId,destinationAirportId);

        verify(routesRepo).getAirlineName(airlineId);
        verify(routesRepo).getAirlineType(airlineId);

        ArgumentCaptor<flightList> argumentCaptor =
                ArgumentCaptor.forClass(flightList.class);

        verify(acmeTravelRepo).save(argumentCaptor.capture());
        flightList capturedFlightList = argumentCaptor.getValue();

        assertThat(capturedFlightList.getId().getFlight_code()).isEqualTo(route.getAirline()+airlineId);
        assertThat(capturedFlightList.getId().getDeparture_date()).isEqualTo(depTime.toString());
    }

    @Test
    void canInsertNewRouteUnsuccessfully() {
        int airlineId = 4296;
        int sourceAirportId = 599;
        int destinationAirportId = 353;
        String depDate = "2017-03-15 20:30:00";
        List<routes> routelst = new ArrayList<routes>();
        flightList flight = new flightList();

        acmeTravelService acmeTravelServiceSpy = Mockito.spy(acmeTravelService);
        Mockito.doReturn(routelst).when(acmeTravelServiceSpy)
                .getRouteById(airlineId,sourceAirportId,destinationAirportId);

        assertThatThrownBy(() -> acmeTravelServiceSpy
                .insertNewRoute(flight, convertDate(depDate),airlineId,sourceAirportId,destinationAirportId))
                .isInstanceOf(dataNotFoundException.class)
                .hasMessageContaining("routes with airlineId: " + airlineId + ", sourceAirportId: " + ", destinationAirportId: " + destinationAirportId + " could not be found");

        verify(routesRepo, never()).getAirlineName(airlineId);
        verify(routesRepo, never()).getAirlineType(airlineId);
        verify(acmeTravelRepo, never()).save(any());
    }

    private static LocalDateTime convertDate(String date)
    {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(Locale.US);

        return LocalDateTime.parse(date, formatter);
    }

}