package com.example.airportProject.model;

import com.example.airportProject.reflectionObjects.Classification;
import com.example.airportProject.reflectionObjects.Migration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Migration(classification = Classification.main)
public class Aircraft {

    @Id
    private int id;
    private String type;

    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;

    @ManyToOne
    @JoinColumn
    private Manufacturer manufacturer;

    private String registration;
    private int max_passengers;

}
