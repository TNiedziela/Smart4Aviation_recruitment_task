package Flights;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *  The WholeCargo class represents Cargo entity in JSON file containing data of Cargo (including baggage and cargo)
 */

public class WholeCargo implements Jsonable{
    private int flightId;
    private ArrayList<Baggage> baggage;
    private ArrayList<Cargo> cargo;


    //constructors
    public WholeCargo(){}

    public WholeCargo(int flightId, ArrayList<Baggage> baggage, ArrayList<Cargo> cargo){
        this.flightId = flightId;
        this.baggage = baggage;
        this.cargo = cargo;
    }

    //getters
    public int getFlightId(){return this.flightId;}
    public ArrayList<Baggage> getBaggage(){return this.baggage;}
    public ArrayList<Cargo> getCargo(){return this.cargo;}

    //setters
    public void setFlightId(int flightId){this.flightId = flightId;}
    public void setBaggage(ArrayList<Baggage> baggage){this.baggage = baggage;}
    public void setCargo(ArrayList<Cargo> cargo){this.cargo = cargo;}

    /**
     *  the sumCargoWeight method calculates and returns sum of cargo weight.
     * @return double sum of cargo weight in kg
     */
    public double sumCargoWeight(){
        double wholeWeight = 0;
        for(Cargo load : cargo){
            if(load.getWeightUnit().equals("lb")){
                wholeWeight += 0.45359237 * load.getWeight();
            }
            else {
                wholeWeight += load.getWeight();
            }
        }
        return wholeWeight;
    }

    /**
     *  the sumBaggageWeight method calculates and returns sum of baggage weight.
     * @return double sum of baggage weight in kg
     */

    public double sumBaggageWeight(){
        double wholeWeight = 0;
        for(Baggage load : baggage){
            if(load.getWeightUnit().equals("lb")){
                wholeWeight += 0.45359237 * load.getWeight();
            }
            else {
                wholeWeight += load.getWeight();
            }
        }
        return wholeWeight;
    }


    /**
     *  the sumTotalWeight method calculates and returns sum of total Cargo weight, including baggage and cargo.
     * @return double sum of Cargo weight in kg
     */
    public double sumTotalWeight(){
        return sumCargoWeight() + sumBaggageWeight();
    }

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
//        json.put("baggage", this.baggage.toJson());
//        json.put("cargo", this.cargo.toJson());
        json.toJson(writer);
    }
}