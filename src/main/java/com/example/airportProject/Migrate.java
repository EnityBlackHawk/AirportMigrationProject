package com.example.airportProject;

import com.example.airportProject.migration.R2NoSQL;
import com.example.airportProject.model.*;
import com.example.airportProject.reflectionObjects.Database;
import com.example.airportProject.reflectionObjects.Table;
import com.example.airportProject.service.AircraftService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class Migrate {
    public static void main(String[] args) {
        var context = SpringApplication.run(R2NoSqlApplication.class, args);
       // var aircraftService = context.getBean(AircraftService.class);
       // var t = new Table(aircraftService.findAll().get(0), Aircraft.class);

        var db = new Database("air", Aircraft.class, Airline.class, Airport.class, Flight.class, Manufacturer.class);
        var r2nql = new R2NoSQL(db);
        // var l = db.referencesTables(db.getTables().stream().filter((e) -> Objects.equals(e.getName(), "Aircraft")).findFirst().orElseThrow());
        var docs = r2nql.DataConversion();
        System.out.println(docs);
    }
}
