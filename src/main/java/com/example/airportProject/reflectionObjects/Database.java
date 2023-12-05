package com.example.airportProject.reflectionObjects;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Table<?>> referencesTables(Table<?> table)
    {
        var refTablesNames = table.getColumns().stream().filter(Column::isReference).map((e) -> e.getType().getSimpleName()).toList();
        return getTables().stream().filter((e) ->  refTablesNames.contains(e.getName())).toList();
    }

    public List<Table<?>> findTableDeep(Table<?> table)
    {
        return this.getTables().stream().filter(
                (e) -> e.getColumns().stream().anyMatch((f) -> f.isReference() && f.getType().getSimpleName().equals(table.getName()))
        ).collect(Collectors.toList());
    }

}
