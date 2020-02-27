import java.awt.*;

public abstract class Vehicle implements Movable {
    private double currentSpeed;
    private final Color color;
    protected final double enginePower;

    Mover m = new Mover();

    public Vehicle(Color color, double enginePower) {
        this.color = color;
        this.enginePower = enginePower;
        stopEngine();
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

//    public void setColor(Color clr){
//        color = clr;
//    }

    public double getEnginePower(){
        return enginePower;
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
     * Speed is always adjusted by this factor
     * */
    public abstract double speedFactor();

    /**
     * Move "forward" by the current speed
     * */
    @Override
    public void move() {
        m = m.move(getCurrentSpeed());
    }

    /**
     * Change the "forward" directing by 90° to the left
     * */
    @Override
    public void turnLeft() {
        m = m.turn(-1);
    }

    /**
     * Change the "forward" directing by 90° to the right
     * */
    @Override
    public void turnRight() {
        m = m.turn(1);
    }

    /**
     * Get current position (changed by move())
     * */
    @Override
    public double[] getPos() {
        return new double[] {m.x, m.y};
    }

    /**
     * Set current position
     * */
    public void setPos(double[] pos) {
        m = m.at(pos);
    }
}
