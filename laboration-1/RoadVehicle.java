import java.awt.*;

/**
 * Base class for Vehicles
 * */
public abstract class RoadVehicle extends Vehicle implements Movable {
    private int nrDoors;
    private String modelName;
    public final int weight; // kg

    public RoadVehicle(int nrDoors, double enginePower, Color color, String modelName, int weight) {
        super(color, enginePower);
        this.nrDoors = nrDoors;
        this.modelName = modelName;
        this.weight = weight;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Speed up
     *
     * @param amount: double between 0 and 1
     * */
    public void gas(double amount){
        if (amount < 0) {
            throw new RuntimeException("Cannot gas by less than 0");
        } else if (amount > 1) {
            throw new RuntimeException("Cannot gas by more than 1");
        }
        incrementSpeed(amount);
    }

    /**
     * Slow down
     *
     * @param amount: double between 0 and 1
     * */
    public void brake(double amount){
        if (amount < 0) {
            throw new RuntimeException("Cannot brake by less than 0");
        } else if (amount > 1) {
            throw new RuntimeException("Cannot brake by more than 1");
        }
        decrementSpeed(amount);
    }
}
