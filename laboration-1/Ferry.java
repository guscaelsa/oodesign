import java.awt.*;

/**
 * A Ferry
 * <p>
 *     Has a ramp; when the ramp is up, cars can't be loaded and when the ramp
 *     is down, the ferry can't move.
 * </p>
 * <p>
 *     Can carry 20 cars, each weighing at most 18000 (kg).
 * </p>
 * <p>
 *     Car storage is first-in first-out.
 * </p>
 */
public class Ferry extends Vehicle implements Carrier {
    static final int CAR_CAPACITY = 20;
    static final int MAX_CAR_WEIGHT = 18000;
    private boolean rampDown = false;
    private VehicleStorage<RoadVehicle> storage = new VehicleStorage<>(
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
