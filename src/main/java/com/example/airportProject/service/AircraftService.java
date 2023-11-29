package com.example.airportProject.service;

import com.example.airportProject.generics.GenericService;
import com.example.airportProject.model.Aircraft;
import com.example.airportProject.repositories.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AircraftService extends GenericService<Aircraft, String, AircraftRepository> {


    public AircraftService(AircraftRepository repository) {
        super(repository);
    }

    public Optional<Aircraft> findByRegistration(String registration)
    {
        return repository.findByRegistration(registration);
    }
}
