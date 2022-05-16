package Flights;

import com.github.cliftonlabs.json_simple.JsonException;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;

public class FlightService {
    private ArrayList<Flight> flightsRegister;
    private ArrayList<WholeCargo> wholeCargos;
    public FlightService(ArrayList<Flight> flights, ArrayList<WholeCargo> wholeCargo){
        this.flightsRegister = flights;
        this.wholeCargos = wholeCargo;
    }

    public void setFlightRegister(String flightJSONpath, String cargoJSONpath) throws JsonException, IOException {
        this.flightsRegister = Parser.parseFlight(flightJSONpath);
        this.wholeCargos = Parser.parseCargo(cargoJSONpath);
    }

    // Task 1

    public double getCargoWeight(int flightNumber, DateTime date){
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getFlightNumber() == flightNumber && date.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
            }
        }
        if (flightId== -1){
            return 0;
        }
        else{
            return wholeCargos.get(flightId).sumCargoWeight();
        }
    }

    public double getBaggageWight(int flightNumber, DateTime date){
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getFlightNumber() == flightNumber && date.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
            }
        }
        if (flightId== -1){
            return 0;
        }
        else{
            return wholeCargos.get(flightId).sumBaggageWeight();
        }
    }
    public double getTotalWeight(int flightNumber, DateTime date){
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getFlightNumber() == flightNumber && date.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
            }
        }
        if (flightId== -1){
            return 0;
        }
        else{
            return wholeCargos.get(flightId).sumTotalWeight();
        }
    }


    // Task 2

    public int getDepartingNumber(String IATAcode, DateTime departureDate){
        int departingNumber = 0;
        for(var flight: flightsRegister){
            if(flight.getDepartureAirportIATACode().equals(IATAcode) && departureDate.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                departingNumber++;
            }
        }
        return departingNumber;
    }

    public int getArrivingNumber(String IATAcode, DateTime departureDate){
        int arrivingNumber = 0;
        for(var flight: flightsRegister){
            if(flight.getArrivalAirportIATACode().equals(IATAcode) && departureDate.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                arrivingNumber++;
            }
        }
        return arrivingNumber;
    }

    public int getArrivingBaggagePieces(String IATAcode, DateTime arriveTime){
        int arrivingPieces = 0;
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getArrivalAirportIATACode().equals(IATAcode) && arriveTime.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
            }
        }
        for(Baggage bag: wholeCargos.get(flightId).getBaggage()){
            arrivingPieces += bag.getPieces();
        }
        return arrivingPieces;
    }

    public int getDepartingBaggagePieces(String IATAcode, DateTime departureTime){
        int departingPieces = 0;
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getDepartureAirportIATACode().equals(IATAcode) && departureTime.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
            }
        }
        for(Baggage bag: wholeCargos.get(flightId).getBaggage()){
            departingPieces += bag.getPieces();
        }
        return departingPieces;
    }

}
