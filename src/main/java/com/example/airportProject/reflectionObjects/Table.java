package com.example.airportProject.reflectionObjects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Table<T> {

    private String name;
    private List<Column> columns;


    public static boolean IsAnnotationPresent(Class<?> obj, Class<? extends Annotation> ...annotationLoop)
    {
       for(var a : annotationLoop)
       {
           if(obj.isAnnotationPresent(a))
               return true;
       }
       return false;
    }

    public Table(T obj, Class<T> entityClass)
    {
        if(!entityClass.isAnnotationPresent(Entity.class)) throw new IllegalArgumentException();

        columns = new ArrayList<>();
        name = entityClass.getSimpleName();

        var jColumns = entityClass.getDeclaredFields();
        for(var c  : jColumns)
        {
            c.setAccessible(true);
            var cname = c.getName();
            var cref = IsAnnotationPresent(c.getClass(), ManyToMany.class, ManyToOne.class, OneToMany.class, OneToOne.class);

            try {
                columns.add(
                        Column.builder().name(cname).type(c.getType()).isReference(cref).value(obj != null ? c.get(obj) : null).build()
                );
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
