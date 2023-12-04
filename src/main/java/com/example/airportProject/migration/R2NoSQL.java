package com.example.airportProject.migration;

import com.example.airportProject.generics.Migrator;
import com.example.airportProject.reflectionObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class R2NoSQL extends Migrator {


    public R2NoSQL(Database db) {
        super(db);
    }

    public void run() {}


    public void DataConversion() {
        for(var table : db.getTables())
        {
            var doc = new Document(table.getName(), new ArrayList<>());
            for(var c : table.getColumns())
                doc.getPropertyList().add(
                        new DocumentProperty(c)
                );
            goDeep(doc, table);
        }

    }

    public void goDeep(Document doc, Table<?> table)
    {
        var tables = db.findTableDeep(table);
        for(var table2 : tables)
        {
            switch (table2.getClassification())
            {
                case subclass:
                case main:
                    for(var c : table2.getColumns())
                        doc.getPropertyList().add(
                                new DocumentProperty(c)
                        );
                    goDeep(doc, table2);
            }

        }
    }

    public void goUp(Document doc, Table<?> table)
    {
        var tables = db.findTableDeep(table);

        List<Table<?>> reference_list = new ArrayList<>();
        List<Table<?>> referenced_list = new ArrayList<>();

        for(var table2 : tables)
        {
            switch (table2.getClassification())
            {
                case common:
                    for(var c : table2.getColumns())
                    {
                        doc.getPropertyList().add(new DocumentProperty(c));
                    }
                case subclass:
                    reference_list.add(table);
                    referenced_list.add(table2);
                    break;

                case main:
                    reference_list.add(table);
                    referenced_list.add(table2);
            }
        }
    }

}
