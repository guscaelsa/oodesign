import java.awt.*;

public class Ferry extends Vehicle implements Carrier {
    static final int CAR_CAPACITY = 20;
    static final int MAX_CAR_WEIGHT = 18000;
    private boolean rampDown = false;
    VehicleStorage<RoadVehicle> storage = new VehicleStorage<>(
            VehicleStorage.fifoQueue(), CAR_CAPACITY, MAX_CAR_WEIGHT);

    public Ferry() {
        super(Color.ORANGE, 50);
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
    public void loadVehicle(RoadVehicle v) {
        loadCheck(v);
        storage.push(v);
    }

    @Override
    public RoadVehicle unloadVehicle() {
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
