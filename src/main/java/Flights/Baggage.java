package Flights;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Baggage implements Load, Jsonable {
    private int id;
    private int weight;
    private String weightUnit;
    private int pieces;


    //constructors
    public Baggage() {}

    public Baggage(int id, int weight, String weightUnit, int pieces){
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }


    @Override
    public int getId() {return this.id;}
    @Override
    public int getWeight() {return this.weight;}
    @Override
    public String getWeightUnit() {return this.weightUnit;}
    @Override
    public int getPieces() {return this.pieces;}
    @Override
    public void setId(int id) {this.id = id;}
    @Override
    public void setWeight(int weight) {this.weight = weight;}
    @Override
    public void setWeightUnit(String weightUnit) {this.weightUnit = weightUnit;}
    @Override
    public void setPieces(int pieces) {this.pieces = pieces;}

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
        json.put("id", this.getId());
        json.put("weight", this.getWeight());
        json.put("weightUnit", this.getWeightUnit());
        json.put("pieces", this.getPieces());
        json.toJson(writer);
    }
}
