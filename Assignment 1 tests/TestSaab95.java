import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestSaab95 {
    private static final Saab95 instance = new Saab95();
    private static final double SPEED_DELTA = 0.000001;

    @Test
    public void testNrDoors() {
        assertEquals(instance.getNrDoors(), 2);
    }

    @Test
    public void testColor() {
        Saab95 car = new Saab95();
        help.print("starts as black?");
        assertEquals(car.getColor(), Color.red);
        Color[] colors = new Color[] {
                Color.RED,
                Color.BLUE,
                Color.CYAN,
                Color.GREEN,
                Color.MAGENTA,
                Color.ORANGE,
                Color.RED,
                Color.BLACK,
        };
        for (Color c : colors) {
            help.print("set to", c.toString() + "?");
            car.setColor(c);
            assertEquals(car.getColor(), c);
        }
    }

    @Test
    public void testSpeed() {
        Saab95 car = new Saab95();
        help.print("Startspeed 0?");
        assertEquals(car.getCurrentSpeed(), 0, SPEED_DELTA);
        help.print("Start engine...");
        car.startEngine();
        assertEquals(car.getCurrentSpeed(), 0.1, SPEED_DELTA);

        _speedInc(car, 0.2, 0.35);
        _speedInc(car, 0.75, 1.2875);
        _speedInc(car, 2.33, 4.2);
        _speedInc(car, -2.3, 1.325);
        _speedInc(car, 50, 63.825);
        _speedInc(car, 0, 63.825);
        _speedInc(car, 29, 100.075);

        _speedDec(car, 0, 100.075);
        _speedDec(car, 0.13, 99.9125);
        _speedDec(car, 14.24, 82.1125);
        _speedDec(car, -4.4, 87.6125);
        _speedDec(car, 62, 10.1125);
        _speedDec(car, 20.2, -15.1375);
    }

    @Test
    public void testEnginePower() {
        assertEquals(instance.getEnginePower(), 125, SPEED_DELTA);
    }

    @Test
    public void testTurbo() {
        Saab95 car = new Saab95();
        help.print("Starting value");
        assertEquals(car.speedFactor(), 1.25, SPEED_DELTA);
        help.print("turbo on...");
        car.setTurboOn();
        assertEquals(car.speedFactor(), 1.625, SPEED_DELTA);
        help.print("turbo off...");
        car.setTurboOff();
        assertEquals(car.speedFactor(), 1.25, SPEED_DELTA);
    }

    void _speedInc(Saab95 car, double amount, double result) {
        help.print("Increment", amount, "...");
        car.incrementSpeed(amount);
        assertEquals(car.getCurrentSpeed(), result, SPEED_DELTA);
    }

    void _speedDec(Saab95 car, double amount, double result) {
        help.print("Decrement", amount, "...");
        car.decrementSpeed(amount);
        assertEquals(car.getCurrentSpeed(), result, SPEED_DELTA);
    }
}
