import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestScania {
    Scania s;

    @Before
    public void before() {
        s = new Scania();
    }

    @Test
    public void testPlatformAngle() {
        //Scania s = new Scania();
        help.print("initial...");
        assertEquals(s.platformAngle(), 0, 0.001);

        s.raisePlatform(Scania.MAX_ANGLE * 2);
        help.print("Too high");
        assertEquals(s.platformAngle(), Scania.MAX_ANGLE, 0.001);

        s.lowerPlatform(Scania.MAX_ANGLE * 2);
        help.print("Too low");
        assertEquals(s.platformAngle(), 0, 0.001);

        s.raisePlatform(20);
        help.print("Just right");
        assertEquals(s.platformAngle(), 20, 0.001);

        s.lowerPlatform(10);
        help.print("lowered");
        assertEquals(s.platformAngle(), 10, 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void testNoRaisePlatform() {
        //Scania s = new Scania();
        s.startEngine();
        s.raisePlatform(1);
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine() {
        s.raisePlatform(1);
        s.startEngine();
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine2() {
        s.raisePlatform(1);
        s.incrementSpeed(1);
    }
}
