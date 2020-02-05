import java.awt.*;

import static java.lang.Math.abs;

public class VolvoFH500 extends RoadVehicle {
    static final int CAR_CAPACITY = 6;
    static final int MAX_CAR_WEIGHT = 4000;

    private boolean rampDown = false;
    private VehicleStorage<RoadVehicle> storage = new VehicleStorage<>(VehicleStorage.lifoQueue(), CAR_CAPACITY, MAX_CAR_WEIGHT);

    public VolvoFH500() {
        super(2, 500, Color.BLUE, "Volvo FH500", 21000);
    }

    @Override
    public double speedFactor() {
        return 1;
    }

    public void loadVehicle(RoadVehicle v) {
        if (!rampDown) {
            throw new RuntimeException("Vehicles can only be loaded when the ramp is down");
        }
        double[] this_pos = getPos();
        double[] other_pos = v.getPos();
        double diff_x = abs(this_pos[0] - other_pos[0]);
        double diff_y = abs(this_pos[1] - other_pos[1]);
        if (diff_x > 1 || diff_y > 1) {
            throw new RuntimeException("Vehicle to be loaded is too far away");
        }

        storage.push(v);
    }

    public RoadVehicle unloadVehicle() {
        if (!rampDown) {
            throw new RuntimeException("Vehicles can only be unloaded when ramp is down");
        }
        return storage.pop();
    }

    void raiseRamp() {
        rampDown = false;
    }

    void lowerRamp() {
        if (getCurrentSpeed() > 0) {
            throw new RuntimeException("Can't lower the ramp while vehicle is in motion");
        }
        rampDown = true;
    }

    @Override
    public void startEngine() {
        if (rampDown) {
            throw new RuntimeException("Can't start engine when the ramp is down");
        }
        super.startEngine();
    }

    @Override
    protected void incrementSpeed(double amount) {
        if (rampDown) {
            throw new RuntimeException("Can't increase speed when the ramp is down");
        }
        super.incrementSpeed(amount);
    }

    @Override
    public void move() {
        super.move();
        for (RoadVehicle v : storage) {
            v.setPos(getPos());
        }
    }
}
