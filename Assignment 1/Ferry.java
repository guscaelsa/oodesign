import java.awt.*;

public class Ferry extends Vehicle {
    private boolean rampDown = false;

    public Ferry() {
        super(Color.ORANGE, 50);
    }

    @Override
    public double speedFactor() {
        return 1;
    }
}
