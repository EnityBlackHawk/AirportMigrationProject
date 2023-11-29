package com.example.airportProject.controller;

import com.example.airportProject.generics.GenericController;
import com.example.airportProject.model.Manufacturer;
import com.example.airportProject.service.ManufacturerService;
import com.example.airportProject.utils.NotImplementedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController extends GenericController<Manufacturer, ManufacturerService, Integer> {
    public ManufacturerController(ManufacturerService service) {
        super(service);
    }

    @Override
    public List<Manufacturer> findAll() {
        throw new NotImplementedException();
    }
}
