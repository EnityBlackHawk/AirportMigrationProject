package com.example.airportProject.service;

import com.example.airportProject.generics.GenericService;
import com.example.airportProject.model.Manufacturer;
import com.example.airportProject.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService extends GenericService<Manufacturer, Integer, ManufacturerRepository> {

    public ManufacturerService(ManufacturerRepository repository) {
        super(repository);
    }
}
