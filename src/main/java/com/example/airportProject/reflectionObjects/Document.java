package com.example.airportProject.reflectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Document {


    private String name;
    private List<DocumentReference> reference_list = new ArrayList<>();
    private List<DocumentReference> referenced_list = new ArrayList<>();
    private List<DocumentProperty> propertyList;

    public Document(String name, List<DocumentProperty> propertyList)
    {
        this.name = name;
        this.propertyList = propertyList;
    }

}
