import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFerry {
    Ferry f;

    @Before
    public void before() {
        f = new Ferry();
    }

    @Test(expected = RuntimeException.class)
    public void testNoRaisePlatform() {
        //Scania s = new Scania();
        f.startEngine();
        f.lowerRamp();
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine() {
        f.lowerRamp();
        f.startEngine();
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine2() {
        f.lowerRamp();
        f.incrementSpeed(1);
    }

    @Test
    public void loadOK() {
        f.lowerRamp();
        Saab95 s = new Saab95();
        s.setPos(new double[]{1, 1});
        f.loadVehicle(s);
    }

    @Test(expected = RuntimeException.class)
    public void loadBadPos() {
        f.lowerRamp();
        Saab95 s = new Saab95();
        s.setPos(new double[]{2, 2});
        f.loadVehicle(s);
    }

    @Test(expected = RuntimeException.class)
    public void loadBadRamp() {
        Saab95 s = new Saab95();
        s.setPos(new double[]{1, 1});
        f.loadVehicle(s);
    }

    @Test
    public void unloadOK() {
        f.lowerRamp();
        Saab95 s = new Saab95();
        f.loadVehicle(s);
        f.unloadVehicle();
    }

    @Test(expected = RuntimeException.class)
    public void unloadBadRamp() {
        f.lowerRamp();
        Saab95 s = new Saab95();
        f.loadVehicle(s);
        f.raiseRamp();
        f.unloadVehicle();
    }

    @Test
    public void loadOrder() {
        f.lowerRamp();
        Saab95 s1 = new Saab95();
        Saab95 s2 = new Saab95();
        f.loadVehicle(s1);
        f.loadVehicle(s2);

        help.print("unloading #2");
        assertEquals(f.unloadVehicle(), s1);
        help.print("unloading #1");
        assertEquals(f.unloadVehicle(), s2);
    }

    @Test(expected = RuntimeException.class)
    public void testCantLoadHeavy() {
        f.lowerRamp();
        VolvoFH500 v = new VolvoFH500();
        f.loadVehicle(v);
    }

    @Test
    public void testSamePos() {
        f.lowerRamp();
        Saab95 s = new Saab95();
        f.loadVehicle(s);
        f.raiseRamp();
        f.startEngine();
        for (int i=0; i<5; ++i) {
            f.move();
            assertNotEquals(f.getPos()[1], 0);
            assertArrayEquals(f.getPos(), s.getPos(), 0.0001);
        }
    }

    @Test
    public void testFill() {
        _fill();
    }

    Ferry _fill() {
        Ferry f = new Ferry();
        f.lowerRamp();
        for (int i=0; i<Ferry.CAR_CAPACITY; ++i) {
            f.loadVehicle(new Saab95());
        }
        return f;
    }

    @Test(expected = RuntimeException.class)
    public void testFull() {
        Ferry f = _fill();
        f.loadVehicle(new Saab95());
    }

}
