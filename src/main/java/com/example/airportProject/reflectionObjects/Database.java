package com.example.airportProject.reflectionObjects;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Database {

    private String name;
    private List<Table<?>> tables = new ArrayList<>();

    public Database(String name, Class<?> ...entities)
    {
        this.name = name;
        for (Class<?> entity : entities) {
            if(!entity.isAnnotationPresent(Entity.class))
                throw new IllegalArgumentException();
            var t = new Table<>(null, entity);
            tables.add(t);
        }
    }

}
