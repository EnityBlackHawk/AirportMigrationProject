package com.example.airportProject.model;

import com.example.airportProject.reflectionObjects.Classification;
import com.example.airportProject.reflectionObjects.Migration;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Migration(classification = Classification.subclass)
public class Airline {

    @Id
    private int id;
    private String name;
    private String iata;
    private String icao;

}
