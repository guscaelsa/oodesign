import java.awt.*;

import static java.lang.Math.abs;

public class VolvoFH500 extends RoadVehicle implements Carrier {
    static final int CAR_CAPACITY = 6;
    static final int MAX_CAR_WEIGHT = 4000;

    private boolean rampDown = false;
    private VehicleStorage<RoadVehicle> storage = new VehicleStorage<>(
            VehicleStorage.lifoQueue(), CAR_CAPACITY, MAX_CAR_WEIGHT);

    public VolvoFH500() {
        super(2, 500, Color.BLUE, "Volvo FH500", 21000);
    }

    @Override
    public double speedFactor() {
        return 1;
    }

    @Override
    public boolean isRampDown() {
        return rampDown;
    }

    @Override
    public void load(RoadVehicle v) {
        loadCheck(v);
        storage.push(v);
    }

    @Override
    public RoadVehicle unload() {
        unloadCheck();
        return storage.pop();
    }

    @Override
    public void raiseRamp() {
        rampDown = false;
    }

    @Override
    public void lowerRamp() {
        lowerCheck();
        rampDown = true;
    }

    @Override
    public void startEngine() {
        startupCheck();
        super.startEngine();
    }

    @Override
    protected void incrementSpeed(double amount) {
        speedupCheck();
        super.incrementSpeed(amount);
    }

    @Override
    public void move() {
        super.move();
        repositionAll(storage);
    }
}
