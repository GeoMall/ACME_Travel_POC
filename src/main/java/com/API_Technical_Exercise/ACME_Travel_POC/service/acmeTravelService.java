package com.API_Technical_Exercise.ACME_Travel_POC.service;

import com.API_Technical_Exercise.ACME_Travel_POC.model.flightList;
import com.API_Technical_Exercise.ACME_Travel_POC.repo.acmeTravelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class acmeTravelService {
    private acmeTravelRepo acmeTravelRepo;

    @Autowired
    public acmeTravelService (acmeTravelRepo acmeTravelrpo) {
        this.acmeTravelRepo = acmeTravelrpo;
    }

    public List<flightList> getFlightListByDate(String depDate){
        System.out.println(depDate);
        return acmeTravelRepo.getFlightListByDate(depDate);
    }
}
