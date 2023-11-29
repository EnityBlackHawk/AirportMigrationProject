package com.example.airportProject.reflectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Document {

    private String name;
    private List<DocumentProperty> propertyList;

}
