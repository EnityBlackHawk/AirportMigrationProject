package com.example.airportProject.controller;

import com.example.airportProject.generics.GenericController;
import com.example.airportProject.model.Flight;
import com.example.airportProject.service.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flight")
public class FlightController extends GenericController<Flight, FlightService, String> {
    public FlightController(FlightService service) {
        super(service);
    }
}
