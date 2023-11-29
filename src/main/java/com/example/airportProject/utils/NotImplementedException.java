package com.example.airportProject.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "Endpoint not implemented")
public class NotImplementedException extends RuntimeException {

    public NotImplementedException()
    {
        super("Endpoint not implemented");
    }

}
