import java.awt.*;

/**
 * A Volvo 240
 * */
public class Volvo240 extends Vehicle {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, Color.BLACK, "Volvo240");
        enginePower = 100;
    }

    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}
