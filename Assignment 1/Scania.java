import java.awt.*;

public class Scania extends RoadVehicle {
    /**
     * Minimum angle allowed for the platform
     */
    public static final double MIN_ANGLE = 0;
    /**
     * Maximum angle allowed for the platform
     */
    public static final double MAX_ANGLE = 70;

    private double platformAngle = MIN_ANGLE;

    public Scania() {
        super(2, 90, Color.BLUE, "Scania", 17000);
    }

    @Override
    public double speedFactor() {
        return 0;
    }

    /**
     * Raise the platform by <code>amount</code>
     * <p>
     *     The angle always stops at <code>MAX_ANGLE</code>.
     * </p>
     * @param amount Angle to raise by
     */
    public void raisePlatform(double amount) {
        if (getCurrentSpeed() > 0) {
            throw new RuntimeException("Can't raise platform while vehicle is in motion");
        }
        platformAngle = checkAngle(platformAngle + amount);
    }

    /**
     * Lower the platform by <code>amount</code>
     * <p>
     *     The angle always stops at <code>MIN_ANGLE</code>.
     * </p>
     * @param amount Angle to lower by
     */
    public void lowerPlatform(double amount) {
        platformAngle = checkAngle(platformAngle - amount);
    }

    private double checkAngle(double angle) {
        if (angle < MIN_ANGLE) {
            return MIN_ANGLE;
        } else if (angle > MAX_ANGLE) {
            return MAX_ANGLE;
        } else {
            return angle;
        }
    }

    /**
     * The current angle of the platform, as a <code>double</code>.
     */
    public double platformAngle() {
        return platformAngle;
    }

    @Override
    public void startEngine() {
        if (platformAngle > 0) {
            throw new RuntimeException("Can't start engine when platform is raised");
        }
        super.startEngine();
    }

    @Override
    protected void incrementSpeed(double amount) {
        if (platformAngle > 0) {
            throw new RuntimeException("Can't increase speed when platform is raised");
        }
        super.incrementSpeed(amount);
    }
}
