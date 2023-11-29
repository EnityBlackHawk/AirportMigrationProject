package com.example.airportProject.controller;

import com.example.airportProject.generics.GenericController;
import com.example.airportProject.model.Airport;
import com.example.airportProject.service.AirportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airport")
public class AirportController extends GenericController<Airport, AirportService, String> {
    public AirportController(AirportService service) {
        super(service);
    }
}
