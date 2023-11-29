package com.example.airportProject.reflectionObjects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@Builder
@AllArgsConstructor
public class Column {

    private String name;
    private Class<?> type;
    private boolean isReference;
    private Object value;

}
