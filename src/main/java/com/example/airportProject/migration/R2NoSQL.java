package com.example.airportProject.migration;

import com.example.airportProject.generics.IMigrator;
import com.example.airportProject.reflectionObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class R2NoSQL implements IMigrator {

    private Database db;

    public void run() {}

    public List<Table<?>> findTableDeep(Table<?> table)
    {
        return db.getTables().stream().filter(
                (e) -> e.getColumns().stream().anyMatch((f) -> f.isReference() && f.getType().getSimpleName().equals(table.getName()))
        ).collect(Collectors.toList());
    }

    public void DataConversion(Database db) {
        this.db = db;
        for(var table : db.getTables())
        {
            var doc = new Document(table.getName(), new ArrayList<>());
            for(var c : table.getColumns())
                doc.getPropertyList().add(
                        new DocumentProperty(c)
                );
            // goDeep;
        }

    }

    public void goDeep(Document doc, Table<?> table)
    {

    }

}
