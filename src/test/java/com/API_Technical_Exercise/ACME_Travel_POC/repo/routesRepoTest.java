package com.API_Technical_Exercise.ACME_Travel_POC.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class routesRepoTest {

    @Autowired
    private routesRepo routesRepo;
    String airlineName ="";
    String airlineType = "";
    int id;

    @BeforeEach
    void setUp() {
        id = 4296;
    }

    @Test
    void shouldReturnAirlineName() {
        //when
        airlineName = routesRepo.getAirlineName(id);
        //then
        assertThat(airlineName).isNotEmpty();
    }

    @Test
    void getAirlineType() {
        //when
        airlineType = routesRepo.getAirlineType(id);

        //then
        assertThat(airlineType).isNotEmpty();
    }
}