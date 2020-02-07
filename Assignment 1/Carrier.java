import static java.lang.Math.abs;

public interface Carrier extends Movable, Transporter<RoadVehicle> {
    boolean isRampDown();

    default void loadCheck(RoadVehicle v) {
        if (!isRampDown()) {
            throw new RuntimeException("Vehicles can only be loaded when the ramp is down");
        }
        if (!positionCheck(this, v)) {
            throw new RuntimeException("Vehicle to be loaded is too far away");
        }
    }

    default void unloadCheck() {
        if (!isRampDown()) {
            throw new RuntimeException("Vehicles can only be unloaded when ramp is down");
        }
    }

    default void lowerCheck() {
        if (getCurrentSpeed() > 0) {
            throw new RuntimeException("Can't lower the ramp while vehicle is in motion");
        }
    }

    default boolean positionCheck(Carrier self, RoadVehicle v) {
        double[] this_pos = self.getPos();
        double[] other_pos = v.getPos();
        double diff_x = abs(this_pos[0] - other_pos[0]);
        double diff_y = abs(this_pos[1] - other_pos[1]);
        return diff_x <= 1 && diff_y <= 1;
    }

    default void startupCheck() {
        if (isRampDown()) {
            throw new RuntimeException("Can't start engine when the ramp is down");
        }
    }

    default void speedupCheck() {
        if (isRampDown()) {
            throw new RuntimeException("Can't increase speed when the ramp is down");
        }
    }

    default void repositionAll(VehicleStorage<RoadVehicle> storage) {
        for (RoadVehicle v : storage) {
            v.setPos(getPos());
        }
    }

    void raiseRamp();

    void lowerRamp();
}
