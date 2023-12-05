package com.example.airportProject.reflectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
public class DocumentReference {

    private Table<?> table;
    private String value;

    public DocumentReference(Table<?> table, String value)
    {
        this.table = table;
        this.value = value;
    }

}
