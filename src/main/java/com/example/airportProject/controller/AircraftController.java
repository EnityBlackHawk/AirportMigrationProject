package com.example.airportProject.controller;

import com.example.airportProject.generics.GenericController;
import com.example.airportProject.model.Aircraft;
import com.example.airportProject.service.AircraftService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController extends GenericController<Aircraft, AircraftService, String> {
    public AircraftController(AircraftService service) {
        super(service);
    }
}
