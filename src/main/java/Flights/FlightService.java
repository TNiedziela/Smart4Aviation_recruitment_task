package Flights;

import com.github.cliftonlabs.json_simple.JsonException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;


/**
 *  The FlightService class provides functionalities to extract data of flights and cargo entities
 *
 */
public class FlightService {
    private ArrayList<Flight> flightsRegister;
    private ArrayList<WholeCargo> wholeCargos;
    public FlightService(ArrayList<Flight> flights, ArrayList<WholeCargo> wholeCargo){
        this.flightsRegister = flights;
        this.wholeCargos = wholeCargo;
    }

    public FlightService(String flightJSONPath, String cargoJSONPath) throws JsonException, IOException {
        this.flightsRegister = Parser.parseFlight(flightJSONPath);
        this.wholeCargos = Parser.parseCargo(cargoJSONPath);
    }

    public void setFlightRegister(String flightJSONPath, String cargoJSONPath) throws JsonException, IOException {
        this.flightsRegister = Parser.parseFlight(flightJSONPath);
        this.wholeCargos = Parser.parseCargo(cargoJSONPath);
    }

    // Task 1


    /**
     * The getCargoWeight method calculates sum of cargo weight for specified by flight number and date of flight.
     *
     * @param flightNumber int, Number of specified flight.
     * @param date joda.DateTime, date of flight.
     * @return double, sum of cargo on specified flight.
     */
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

    /**
     * The getBaggageWight method calculates sum of baggage weight for specified by flight number and date of flight.
     *
     * @param flightNumber int, Number of specified flight.
     * @param date joda.DateTime, date of flight.
     * @return double, sum of baggage on specified flight.
     */
    public double getBaggageWeight(int flightNumber, DateTime date){
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


    /**
     * The getTotalWeight method calculates sum of total Cargo weight for specified by flight number and date of flight.
     *
     * @param flightNumber int, Number of specified flight.
     * @param date joda.DateTime, date of flight.
     * @return double, sum of total Cargo weight on specified flight.
     */
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

    /**
     * The getDepartingNumber method calculates the number of flights departing from airport specified by IATA Airport code
     * and date when the flights took place.
     *
     * @param IATAcode String, IATA Airport code
     * @param departureDate joda.DateTime, date when flights departed from specified airport.
     * @return int, number of flights departing from airport.
     */
    public int getDepartingNumber(String IATAcode, DateTime departureDate){
        int departingNumber = 0;
        for(var flight: flightsRegister){
            if(flight.getDepartureAirportIATACode().equals(IATAcode) && departureDate.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                departingNumber++;
            }
        }
        return departingNumber;
    }

    /**
     * The getArrivingNumber method calculates the number of flights arriving to airport specified by IATA Airport code
     * and date when the flights took place.
     *
     * @param IATAcode String, IATA Airport code
     * @param arriveTime joda.DateTime, date when flights arrived to specified airport.
     * @return int, number of flights arriving to airport.
     */
    public int getArrivingNumber(String IATAcode, DateTime arriveTime){
        int arrivingNumber = 0;
        for(var flight: flightsRegister){
            if(flight.getArrivalAirportIATACode().equals(IATAcode) && arriveTime.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                arrivingNumber++;
            }
        }
        return arrivingNumber;
    }


    /**
     * The getArrivingBaggagePieces method calculates the total number of baggage pieces arriving to airport specified by IATA Airport code
     * and date when the flights took place.
     *
     * @param IATAcode String, IATA Airport code
     * @param arriveTime joda.DateTime, date when flights arrived to specified airport.
     * @return int, total number of pieces of baggage arriving to airport.
     */
    public int getArrivingBaggagePieces(String IATAcode, DateTime arriveTime){
        int arrivingPieces = 0;
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getArrivalAirportIATACode().equals(IATAcode) && arriveTime.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
                for(Baggage bag: wholeCargos.get(flightId).getBaggage()){
                    arrivingPieces += bag.getPieces();
                }
            }
        }

        return arrivingPieces;
    }

    /**
     * The getDepartingBaggagePieces method calculates the total number of baggage pieces departing from airport specified by IATA Airport code
     * and date when the flights took place.
     *
     * @param IATAcode String, IATA Airport code
     * @param departureTime joda.DateTime, date when flights departed from specified airport.
     * @return int, total number of pieces of baggage departing from airport.
     */
    public int getDepartingBaggagePieces(String IATAcode, DateTime departureTime){
        int departingPieces = 0;
        int flightId = -1;
        for(Flight flight : flightsRegister){
            if(flight.getDepartureAirportIATACode().equals(IATAcode) && departureTime.toLocalDate().isEqual(flight.getDepartureDate().toLocalDate())){
                flightId = flight.getFlightId();
                for(Baggage bag: wholeCargos.get(flightId).getBaggage()){
                    departingPieces += bag.getPieces();
                }
            }
        }

        return departingPieces;
    }

}
