package Flights;

/**
 * The Load interface specify what functionalities cargo classes (cargo and baggage) should have.
 */
public interface Load{
    public int getId();
    public int getWeight();
    public String getWeightUnit();
    public int getPieces();
    public void setId(int id);
    public void setWeight(int weight);
    public void setWeightUnit(String weightUnit);
    public void setPieces(int pieces);

}