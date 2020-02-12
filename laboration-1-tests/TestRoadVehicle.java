import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestRoadVehicle {
    class Car extends RoadVehicle {

        public Car() {
            super(1, 100, Color.BLUE, "TestCar", 1);
        }

        @Override
        public double speedFactor() {
            return 1;
        }

        @Override
        public double getCurrentSpeed() {
            return 1;
        }
    }


    @Test
    public void testMove() {
        Car c = new Car();
        forward(c, 0, 1);
        right(c, 1, 1);
        left(c,1, 2);
        forward(c, 1, 3);
        left(c, 0, 3);
        forward(c, -1, 3);
        left(c, -1, 2);
        right(c, -2, 2);
        left(c, -2, 1);
        left(c, -1, 1);
        right(c, -1, 0);
        forward(c, -1, -1);
        left(c, 0, -1);
        left(c, 0, 0);
    }

    void forward(Car c, double x, double y) {
        help.print("Moving forward from", Arrays.toString(c.getPos()));
        c.move();
        assertArrayEquals(new double[]{x, y}, c.getPos(), 0.001);
    }
    void left(Car c, double x, double y) {
        help.print("Moving forward from", Arrays.toString(c.getPos()));
        c.turnLeft();
        c.move();
        assertArrayEquals(new double[]{x, y}, c.getPos(), 0.001);
    }
    void right(Car c, double x, double y) {
        help.print("Moving forward from", Arrays.toString(c.getPos()));
        c.turnRight();
        c.move();
        assertArrayEquals(new double[]{x, y}, c.getPos(), 0.001);
    }

    @Test
    public void testGasOk() {
        Car c = new Car();
        c.gas(0);
        c.gas(0.5);
        c.gas(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGasTooLow() {
        Car c = new Car();
        c.gas(-0.5);
    }
    @Test(expected = RuntimeException.class)
    public void testGasTooHigh() {
        Car c = new Car();
        c.gas(1.5);
    }

    @Test
    public void testBrakeOk() {
        Car c = new Car();
        c.brake(0);
        c.brake(0.5);
        c.brake(1);
    }
    @Test(expected = RuntimeException.class)
    public void testBrakeTooLow() {
        Car c = new Car();
        c.brake(-0.5);
    }
    @Test(expected = RuntimeException.class)
    public void testBrakeTooHigh() {
        Car c = new Car();
        c.brake(1.5);
    }
}
