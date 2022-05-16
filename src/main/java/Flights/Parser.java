package Flights;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 *  The Parser class provides methods that parse JSON files
 */
public class Parser {
    public static class Flight_s{
        private int flightId;
        private int flightNumber;
        private String departureAirportIATACode;
        private String arrivalAirportIATACode;
        private String departureDate;
        public void setFlightId(int flightId){this.flightId = flightId;}
        public void setFlightNumber(int flightNumber){this.flightNumber = flightNumber;}
        public void setDepartureAirportIATACode(String departureAirportIATACode){this.departureAirportIATACode = departureAirportIATACode;}
        public void setArrivalAirportIATACode(String arrivalAirportIATACode){this.arrivalAirportIATACode = arrivalAirportIATACode;}
        public void setDepartureDate(String departureDate){this.departureDate = departureDate;}

    }

    /**
     * parseFlight is used to parse JSON flight with arrays containing Flight Entities.
     *
     *
     * @param jsonPath path to the JSON file that contains data of Flight entities
     * @return ArrayList<Flight></> ArrayList containing objects of Flight class that are initialized with the date from JSON file.
     * @throws IOException
     * @throws JsonException
     */
    public static ArrayList<Flight> parseFlight(String jsonPath) throws IOException, JsonException {
        try (FileReader fileReader = new FileReader((jsonPath))) {

            // Working .json to Array<Flights.Flight> code
            JsonArray deserialized = Jsoner.deserializeMany(fileReader);
            // need dozer to copy object to staff, json_simple no api for this?
            Mapper mapper = new DozerBeanMapper();

            JsonArray o = (JsonArray) deserialized.get(0);

            ArrayList<Flight> flights = new ArrayList<>();
            ArrayList<Flight_s> flightPreRegister = new ArrayList<>();
            flightPreRegister= o.stream()
                    .map(x -> mapper.map(x, Flight_s.class)).collect(Collectors.toCollection(ArrayList::new));
            for(var fl :flightPreRegister){
                flights.add(new Flight(fl.flightId, fl.flightNumber, fl.departureAirportIATACode, fl.arrivalAirportIATACode, new DateTime(fl.departureDate)));
            }
            return flights;
        }

    }

    public static ArrayList<WholeCargo> parseCargo(String jsonPath) throws IOException, JsonException{
        try (FileReader fileReader = new FileReader((jsonPath))) {
            //working .json to Array<Flights.Cargo> code
            JsonArray deserialized = Jsoner.deserializeMany(fileReader);
            Mapper mapper = new DozerBeanMapper();
            JsonArray o = (JsonArray) deserialized.get(0);
            return o.stream()
                    .map(x -> mapper.map(x, WholeCargo.class)).collect(Collectors.toCollection(ArrayList::new));
        }
    }
}
