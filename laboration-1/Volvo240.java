import java.awt.*;

/**
 * A Volvo 240
 * */
public class Volvo240 extends RoadVehicle {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, Color.BLACK, "Volvo240", 1305);
    }

    public Volvo240(int x, int y) {
        this();
        setPos(new double[]{x, y});
    }

    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}
