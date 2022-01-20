package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.model.flightListId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class acmeTravelRepoTest {

    @Autowired
    private acmeTravelRepo acmeTravelRepo;

    public flightList flightList;
    public  flightListId flightListId;
    List<flightListId> ids;
    String flightCode;
    LocalDateTime depDate;
    int seatAvailability;

    private static LocalDateTime convertDate(String date)
    {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(Locale.US);

        return LocalDateTime.parse(date, formatter);
    }


    @BeforeEach
     void setUp(){
        depDate = convertDate("2017-01-01 20:30:30");
        flightCode = "FR1000";
        seatAvailability = 150;

        flightList = new flightList(flightCode, depDate, "RyanAir", "JFK", "LCY", "Boeing 737-800", seatAvailability, 150.00);
        acmeTravelRepo.save(flightList);

        flightListId = new flightListId(flightCode,depDate);
        ids = new ArrayList<flightListId>();
        ids.add(flightListId);
    }

    @AfterEach
    void tearDown() {
        acmeTravelRepo.delete(flightList);
    }

    @Test
    void ItShouldCheckIfFlightExistsByDate() {
        //when
        List<flightList> returnedFlights = acmeTravelRepo.getFlightListByDate("2017-01-01 20:30:30");

        //then
        assertThat(returnedFlights
                    .stream()
                    .filter(f -> f.getId().getFlight_code().equals(flightListId.getFlight_code()))
                    .filter(f -> f.getId().getDeparture_date().equals(flightListId.getDeparture_date()))
                    .findFirst()).isPresent();
    }

    @Test
    void IsShouldCheckIfFlightSeatAvailabilityIsUpdated() {
        //when
        acmeTravelRepo.updateFlightSeatAvailability(seatAvailability++ ,flightCode, depDate.toString());

        //then
        List<flightList> updatedFlight = acmeTravelRepo.findAllById(ids);
        Optional<flightList> modifiedFlight =
                                            updatedFlight.stream()
                                                    .filter(f -> f.getId().getFlight_code().equals(flightListId.getFlight_code()))
                                                    .filter(f -> f.getId().getDeparture_date().equals(flightListId.getDeparture_date()))
                                                    .findFirst();
        //Assert that record has been updated
        assertThat(modifiedFlight.get().getSeat_availability() != flightList.getSeat_availability());
    }

    @Test
    void ItShouldCheckIfFlightSeatPriceIsUpdated() {
        //when
        acmeTravelRepo.updateSeatPrice(50.00, flightCode,depDate.toString());

        //then
        List<flightList> updatedFlight = acmeTravelRepo.findAllById(ids);
        Optional<flightList> modifiedFlight =
                                            updatedFlight.stream()
                                                .filter(f -> f.getId().getFlight_code().equals(flightListId.getFlight_code()))
                                                .filter(f -> f.getId().getDeparture_date().equals(flightListId.getDeparture_date()))
                                                .findFirst();
        assertThat(modifiedFlight.get().getPrice() != flightList.getPrice());
    }
}