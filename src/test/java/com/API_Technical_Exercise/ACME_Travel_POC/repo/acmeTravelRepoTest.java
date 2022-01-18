package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.* ;

class acmeTravelRepoTest {

    @Autowired
    acmeTravelRepo acmeTravelRepo;

    private LocalDateTime convertDate(String date)
    {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(Locale.US);

        return LocalDateTime.parse(date, formatter);
    }

    @Test
    void getFlightListByDate() {
        //given
        LocalDateTime depDate = convertDate("2017-01-01 20:30:30");
        flightList flightList = new flightList("FR1000", depDate, "RyanAir", "JFK", "LCY", "Boeing 737-800", 150, 150.00);
        acmeTravelRepo.save(flightList);

        //when
        List<flightList> returnedFlights = acmeTravelRepo.getFlightListByDate("2017-01-01 20:30:30");

        //then
        assertThat(returnedFlights.contains(flightList)).isTrue();
    }

    @Test
    void updateFlightSeatAvailability() {
    }

    @Test
    void updateSeatPrice() {
    }
}