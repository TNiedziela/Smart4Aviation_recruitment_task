package Flights;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.joda.time.*;

/**
 *  The Flight class represents Flight entity in JSON file containing data of flights.
 */

public class Flight implements Jsonable{
    private int flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private DateTime departureDate;

    //constructors
    public Flight(){}

    public Flight(int flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, DateTime departureDate){
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

    //getters
    public int getFlightId(){return this.flightId;}
    public int getFlightNumber(){return this.flightNumber;}
    public String getDepartureAirportIATACode(){return this.departureAirportIATACode;}
    public String getArrivalAirportIATACode(){return this.arrivalAirportIATACode;}
    public DateTime getDepartureDate(){return this.departureDate;}

    //setters
    public void setFlightId(int flightId){this.flightId = flightId;}
    public void setFlightNumber(int flightNumber){this.flightNumber = flightNumber;}
    public void setDepartureAirportIATACode(String departureAirportIATACode){this.departureAirportIATACode = departureAirportIATACode;}
    public void setArrivalAirportIATACode(String arrivalAirportIATACode){this.arrivalAirportIATACode = arrivalAirportIATACode;}
    public void setDepartureDate(DateTime departureDate){this.departureDate = departureDate;}



    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("flightId", this.getFlightId());
        json.put("flightNumber", this.getFlightNumber());
        json.put("departureAirportIATACode", this.getDepartureAirportIATACode());
        json.put("arrivalAirportIATACode", this.getArrivalAirportIATACode());
        json.put("departureDate", this.getDepartureDate().toString());
        json.toJson(writer);
    }
}
