import static java.lang.Math.abs;

/**
 * Helper "class" for <code>Vehicle</code>s that transport <code>RoadVehicle<code>s
 * <p>
 *     Has a ramp which stops vehicles from being unloaded when it is up, and
 *     must be up for the <code>Carrier</code> to be able to move. Vehicles
 *     also can't be loaded if they are too far away.
 * </p>
 * <p>
 *     Has a bunch of <code>*Check()</code> functions that implementers should call
 *     at the appropriate time, and will throw an exception if the check fails.
 * </p>
 */
public interface Carrier extends Movable, Transporter<RoadVehicle> {
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

    /**
     * Set the position of all vehicles in <code>storage</code> to be equal to the <code>Carrier</code>'s position
     */
    default void repositionAll(VehicleStorage<RoadVehicle> storage) {
        for (RoadVehicle v : storage) {
            v.setPos(getPos());
        }
    }

    /**
     * <code>true</code> if the ramp is in the "down" position, else <code>false</code>.
     */
    boolean isRampDown();

    /**
     * Set the ramp to the "up" position
     */
    void raiseRamp();

    /**
     * Set the ramp to the "down" position
     */
    void lowerRamp();
}
