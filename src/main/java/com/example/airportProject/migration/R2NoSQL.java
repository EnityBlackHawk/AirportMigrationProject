package com.example.airportProject.migration;

import com.example.airportProject.generics.Migrator;
import com.example.airportProject.reflectionObjects.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class R2NoSQL extends Migrator {


    public R2NoSQL(Database db) {
        super(db);
    }

    public void run() {}


    public List<Document> DataConversion() {
        var docs = new ArrayList<Document>();
        for(var table : db.getTables())
        {
            var doc = new Document(table.getName(), new ArrayList<>());
            for(var c : table.getColumns())
                doc.getPropertyList().add(
                        new DocumentProperty(c)
                );
            goDeep(doc, table);
            docs.add(doc);
        }
        return docs;
    }

    public void goDeep(Document doc, Table<?> table)
    {
        var tables = db.referencesTables(table);
        for(var table2 : tables)
        {
            switch (table2.getClassification())
            {
                case common:
                case subclass:
                    for(var c : table2.getColumns()) {
                        doc.getPropertyList().add(
                                new DocumentProperty(c)
                        );
                    }
                    goDeep(doc, table2);
            }

        }
        goUp(doc, table);
    }

    public void goUp(Document doc, Table<?> table)
    {
        var tables = db.referencesTables(table);

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
                    goUp(doc, table2);
                case subclass:
                    reference_list.add(table);
                    referenced_list.add(table2);
                    goDeep(doc, table2);

                case relationship:
                    break;

                case main:
                    reference_list.add(table);
                    referenced_list.add(table2);
            }
        }
        doc.setReference_list(
                reference_list.stream().map((e) -> new DocumentReference(e, null)).toList()
        );
        doc.setReferenced_list(
                referenced_list.stream().map((e) -> new DocumentReference(e, null)).toList()
        );
    }

}
