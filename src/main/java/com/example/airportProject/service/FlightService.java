package com.example.airportProject.service;

import com.example.airportProject.generics.GenericService;
import com.example.airportProject.model.Flight;
import com.example.airportProject.repositories.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends GenericService<Flight, String, FlightRepository> {
    public FlightService(FlightRepository repository) {
        super(repository);
    }
}
