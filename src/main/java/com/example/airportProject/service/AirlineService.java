package com.example.airportProject.service;

import com.example.airportProject.generics.GenericService;
import com.example.airportProject.model.Airline;
import com.example.airportProject.repositories.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineService extends GenericService<Airline, Integer, AirlineRepository> {

    public AirlineService(AirlineRepository repository) {
        super(repository);
    }

    public Optional<Airline> findByIataAndIcao(String iata, String icao)
    {
        return repository.findByIataAndIcao(iata, icao);
    }

}
