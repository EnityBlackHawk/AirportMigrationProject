package com.example.airportProject.model;

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
@Migration
public class Airport {

    @Id
    private String id;
    private String name;
    private String city;
    private String country;

}
