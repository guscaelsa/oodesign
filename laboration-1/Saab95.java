import java.awt.*;

/**
 * A Saab 95
 * */
public class Saab95 extends RoadVehicle {
    private boolean turboOn;

    public Saab95(){
        super(2, 125, Color.RED, "Saab95", 2110);
	    turboOn = false;
    }

    public Saab95(int x, int y) {
        this();
        setPos(new double[]{x, y});
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

}
