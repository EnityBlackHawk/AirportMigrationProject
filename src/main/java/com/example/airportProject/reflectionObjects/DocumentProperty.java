package com.example.airportProject.reflectionObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentProperty {

    private String name;
    private Class<?> type;
    private Object value;

    public DocumentProperty(Column c)
    {
        name = c.getName();
        type = c.getType();
        value = c.getValue();
    }

}
