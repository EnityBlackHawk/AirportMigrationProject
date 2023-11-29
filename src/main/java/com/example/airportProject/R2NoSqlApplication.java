package com.example.airportProject;

import com.example.airportProject.model.Aircraft;
import com.example.airportProject.model.Airline;
import com.example.airportProject.model.Airport;
import com.example.airportProject.model.Flight;
import com.example.airportProject.service.AircraftService;
import com.example.airportProject.service.AirlineService;
import com.example.airportProject.service.AirportService;
import com.example.airportProject.service.FlightService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class R2NoSqlApplication {


    public static Airport ParseToObject(JSONObject obj)
    {
        return Airport.builder()
                .id( (String)obj.get("iata_code") )
                .city( (String)obj.get("city") )
                .country( (String)obj.get("country") )
                .name( (String)obj.get("name"))
                .build();
    }

    public static Airline ParseToAirline(JSONObject obj)
    {
        return Airline.builder()
                .id( Integer.parseInt((String) obj.get("id")) )
                .iata( (String) obj.get("iata") )
                .icao( getJsonProp(obj, String.class, "icao") )
                .name( (String) obj.get("name") )
                .build();
    }

    public static <T> T getJsonProp(JSONObject obj, Class<T> result, String ...props)
    {
        if(props.length == 1)
            return result.cast(obj.get(props[0]));
        return getJsonProp((JSONObject) obj.get(props[0]), result, Arrays.copyOfRange(props, 1, props.length));
    }

    public static void ParseFlight(JSONObject obj,
                                   FlightService flightService,
                                   AircraftService aircraftService,
                                   AirlineService airlineService,
                                   AirportService airportService)
    {

        var aircraft = aircraftService.findByRegistration(
                getJsonProp(obj, String.class, "flight", "aircraft", "registration") )
                .orElse(null);
        if(aircraft == null)
        {
            var ac = Aircraft.builder()

                    .id( getJsonProp(obj, Long.class, "flight", "identification", "row").intValue() )
                    .airline( airlineService.findByIataAndIcao(
                            getJsonProp(obj, String.class, "flight", "airline", "code", "iata"),
                            getJsonProp(obj, String.class, "flight", "airline", "code", "icao")
                    ).orElseThrow() )
                    .max_passengers(0)
                    .registration( getJsonProp(obj, String.class, "flight", "aircraft", "registration") )
                    .type( getJsonProp(obj, String.class, "flight", "aircraft", "model","text") )
                    .build();

             aircraft = aircraftService.insert(ac);
        }


        var dest_airp = airportService.findById( getJsonProp(obj, String.class, "flight", "airport", "destination", "code", "iata") ).orElse(null);

        if(dest_airp == null)
        {
            var ai = Airport.builder()
                    .id(getJsonProp(obj, String.class, "flight", "airport", "destination", "code", "iata"))
                    .name( getJsonProp(obj, String.class, "flight", "airport", "destination", "name") )
                    .city( getJsonProp(obj, String.class, "flight", "airport", "destination", "position", "region", "city") )
                    .country( getJsonProp(obj, String.class, "flight", "airport", "destination", "position", "country", "name") )
                    .build();
            dest_airp = airportService.insert(ai);
        }

        var fl = Flight.builder()
                .aircraft(aircraft)
                .airport_from( airportService.findById( "CWB" ).orElseThrow() )
                .airport_to( dest_airp )
                .gate(0)
                .arrival_time_scheduled( new Date( getJsonProp(obj, Long.class, "flight", "time", "scheduled", "arrival") * 1000 ) )
                .departure_time_scheduled( new Date( getJsonProp(obj, Long.class, "flight", "time", "scheduled", "departure") * 1000) )
                .number( getJsonProp(obj, String.class, "flight", "identification", "number", "default") )
                .build();

        flightService.insert(fl);
    }

    public static void main(String[] args) {
        var context = SpringApplication.run(R2NoSqlApplication.class, args);
        var airlineService = context.getBean(AirlineService.class);
        var flightService = context.getBean(FlightService.class);
        var airportService = context.getBean(AirportService.class);
        var aircraftService = context.getBean(AircraftService.class);
        JSONParser jsonParser = new JSONParser();
        try {
//            FileReader reader = new FileReader("/home/luan/Downloads/airports.json");
//
//            JSONArray array =  (JSONArray) jsonParser.parse(reader);
//
//            array.forEach(o -> {
//                var a = ParseToObject( (JSONObject) o);
//                repo.insert(a);
//            });

//            FileReader reader = new FileReader("/home/luan/Downloads/airlines.json");
//            JSONArray array = (JSONArray) jsonParser.parse(reader);
//
//            array.forEach(o -> {
//                var a = ParseToAirline((JSONObject) o);
//                airlineService.insert(a);
//            });

            FileReader reader = new FileReader("/run/media/luan/5d9214a9-faa4-43ed-adea-74659ea08516/R2NoSQL/flight.json");
            JSONArray array = (JSONArray) jsonParser.parse(reader);

            array.forEach(o -> {
                ParseFlight((JSONObject) o, flightService, aircraftService, airlineService, airportService);
            });


            System.out.println("Completed");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
