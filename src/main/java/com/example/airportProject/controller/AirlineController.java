package com.example.airportProject.controller;

import com.example.airportProject.generics.GenericController;
import com.example.airportProject.model.Airline;
import com.example.airportProject.service.AirlineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airline")
public class AirlineController extends GenericController<Airline, AirlineService, Integer> {
    public AirlineController(AirlineService service) {
        super(service);
    }
}
