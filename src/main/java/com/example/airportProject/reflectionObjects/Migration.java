package com.example.airportProject.reflectionObjects;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Migration {

    public Classification classification() default Classification.main;

}
