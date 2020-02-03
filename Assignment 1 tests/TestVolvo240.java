import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestVolvo240 {
    private static final Volvo240 instance = new Volvo240();
    private static final double SPEED_DELTA = 0.000001;

    @Test
    public void testNrDoors() {
        assertEquals(instance.getNrDoors(), 4);
    }

    @Test
    public void testColor() {
        Volvo240 car = new Volvo240();
        help.print("starts as black?");
        assertEquals(car.getColor(), Color.black);
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
        Volvo240 car = new Volvo240();
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
        _speedInc(car, 29, 100);

        _speedDec(car, 0, 100);
        _speedDec(car, 0.13, 99.8375);
        _speedDec(car, 14.24, 82.0375);
        _speedDec(car, -4.4, 87.5375);
        _speedDec(car, 62, 10.0375);
        _speedDec(car, 20.2, 0);
    }

    @Test
    public void testEnginePower() {
        assertEquals(instance.getEnginePower(), 100, SPEED_DELTA);
    }

    void _speedInc(Volvo240 car, double amount, double result) {
        help.print("Increment", amount, "...");
        car.gas(amount);
        assertEquals(car.getCurrentSpeed(), result, SPEED_DELTA);
    }

    void _speedDec(Volvo240 car, double amount, double result) {
        help.print("Decrement", amount, "...");
        car.brake(amount);
        assertEquals(car.getCurrentSpeed(), result, SPEED_DELTA);
    }
}
