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
 *  The Parser class provides methods that parse JSON files containing data of flights and cargo entities and maps them to
 *  ArrayLists of Flight and WholeCargo objects.
 */
public class Parser {
    /**
     *  Inner class Flight_s is almost identical to Flight class, but the departureDate is of type String.
     *  It is used to create Flight objects after mapping content of JSON file to Flight_s objects.
     *  It stems from the fact that it's hard to map JSON String to joda.DateTime using DozerBeanMapper,
     *  but it's easy to initialize joda.DateTime later with String.
     *
     */
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
     * parseFlight is used to parse JSON file containing data of Flight entities and maps them to ArrayList of Flight class objects.
     *
     *
     * @param jsonPath path to the JSON file that contains data of Flight entities
     * @return ArrayList<Flight></> ArrayList containing objects of Flight class objects that are initialized with the data from JSON file.
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
            for(Flight_s fl :flightPreRegister){
                flights.add(new Flight(fl.flightId, fl.flightNumber, fl.departureAirportIATACode, fl.arrivalAirportIATACode, new DateTime(fl.departureDate)));
            }
            return flights;
        }

    }

    /**
     * parseCargo is used to parse JSON file containing data of Cargo entities and maps them to ArrayList of WholeCargo class objects.
     *
     * @param jsonPath path to the JSON file that contains data of WholeCargo entities
     * @return ArrayList<WholeCargo></> ArrayList containing objects of WholeCargo class objects that are initialized with the data from JSON file.
     * @throws IOException
     * @throws JsonException
     */
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
