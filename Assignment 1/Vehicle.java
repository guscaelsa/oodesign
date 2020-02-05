import java.awt.*;

/**
 * Base class for Vehicles
 * */
public abstract class Vehicle implements Movable {
    private int nrDoors;
    protected double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    public final int weight; // kg

    private Mover m = new Mover();

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, int weight) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.weight = weight;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Sets current speed to 0.1
     * */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets current speed to 0
     * */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Speed is always adjusted by this factor
     * */
    public abstract double speedFactor();

    /**
     * Increase speed by amount * speedFactor(), to maximum enginePower
     * */
    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Reduce speed by amount * speedFactor(), to minimum 0
     * */
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
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

    /**
     * Move "forward" by the current speed
     * */
    @Override
    public void move() {
        m.move(getCurrentSpeed());
    }

    /**
     * Change the "forward" directing by 90° to the left
     * */
    @Override
    public void turnLeft() {
        m.turn(-1);
    }

    /**
     * Change the "forward" directing by 90° to the right
     * */
    @Override
    public void turnRight() {
        m.turn(1);
    }

    /**
     * Get current position (changed by move())
     * */
    @Override
    public double[] getPos() {
        return new double[] {m.x, m.y};
    }

    /**
     * Get current position (changed by move())
     * */
    public void setPos(double[] pos) {
        m.x = pos[0];
        m.y = pos[1];
    }
}
