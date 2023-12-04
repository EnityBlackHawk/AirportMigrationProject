package com.example.airportProject.generics;

import com.example.airportProject.reflectionObjects.Database;

public abstract class Migrator {

    protected Database db;

    protected Migrator(Database db)
    {
        this.db = db;
    }

    public abstract void run();

}
