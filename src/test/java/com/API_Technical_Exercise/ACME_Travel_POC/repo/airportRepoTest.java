package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import com.API_Technical_Exercise.ACME_Travel_POC.model.airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class airportRepoTest {

    @Autowired
    private airportRepo airportRepo;

    List<airport> airportList;
    PageRequest page;
    final int pageNo = 0;
    final int pageSize = 10;
    String airlineName;

    Optional<airport>  testDesAirport;
    @BeforeEach
    void setUp() {
        airportList = new ArrayList<airport>();
        page = PageRequest.of(pageNo, pageSize);
        airlineName = "RyanAir";

        testDesAirport = airportRepo.findById(607);
    }

    @Test
    void ItShouldCheckIfDestinationAirportIsReturnedPageable() {
        //when
        airportList = airportRepo.getDestinationAirport(airlineName, page);
        String airportName = testDesAirport.get().getName();
        //then
        //Pageable, return size should be 10
        assertThat(airportList.size() == 10).isTrue();
        //list should contain item with id 607
        assertThat(
                airportList
                        .stream()
                        .filter(a -> a.getName().equals(airportName))
                        .findFirst()
                ).isPresent();
    }
}