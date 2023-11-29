package com.example.airportProject.service;

import com.example.airportProject.generics.GenericService;
import com.example.airportProject.model.Airport;
import com.example.airportProject.repositories.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends GenericService<Airport, String, AirportRepository> {

    public AirportService(AirportRepository repository) {
        super(repository);
    }
}
