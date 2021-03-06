import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestVolvoFH500 {
    VolvoFH500 v;

    @Before
    public void before() {
        v = new VolvoFH500();
    }

    @Test(expected = RuntimeException.class)
    public void testNoRaisePlatform() {
        //Scania s = new Scania();
        v.startEngine();
        v.lowerRamp();
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine() {
        v.lowerRamp();
        v.startEngine();
    }

    @Test(expected = RuntimeException.class)
    public void testNoStartEngine2() {
        v.lowerRamp();
        v.incrementSpeed(1);
    }

    @Test
    public void loadOK() {
        v.lowerRamp();
        Saab95 s = new Saab95();
        s.setPos(new double[]{1, 1});
        v.load(s);
    }

    @Test(expected = RuntimeException.class)
    public void loadBadPos() {
        v.lowerRamp();
        Saab95 s = new Saab95();
        s.setPos(new double[]{2, 2});
        v.load(s);
    }

    @Test(expected = RuntimeException.class)
    public void loadBadRamp() {
        Saab95 s = new Saab95();
        s.setPos(new double[]{1, 1});
        v.load(s);
    }

    @Test
    public void unloadOK() {
        v.lowerRamp();
        Saab95 s = new Saab95();
        v.load(s);
        v.unload();
    }

    @Test(expected = RuntimeException.class)
    public void unloadBadRamp() {
        v.lowerRamp();
        Saab95 s = new Saab95();
        v.load(s);
        v.raiseRamp();
        v.unload();
    }

    @Test
    public void loadOrder() {
        v.lowerRamp();
        Saab95 s1 = new Saab95();
        Saab95 s2 = new Saab95();
        v.load(s1);
        v.load(s2);

        help.print("unloading #2");
        assertEquals(v.unload(), s2);
        help.print("unloading #1");
        assertEquals(v.unload(), s1);
    }

    @Test(expected = RuntimeException.class)
    public void testCantLoadSelf() {
        v.lowerRamp();
        VolvoFH500 v2 = new VolvoFH500();
        v.load(v2);
    }

    @Test
    public void testSamePos() {
        v.lowerRamp();
        Saab95 s = new Saab95();
        v.load(s);
        v.raiseRamp();
        v.startEngine();
        for (int i=0; i<5; ++i) {
            v.move();
            assertNotEquals(v.getPos()[1], 0);
            assertArrayEquals(v.getPos(), s.getPos(), 0.0001);
        }
    }

    @Test
    public void testFill() {
        _fill();
    }

    VolvoFH500 _fill() {
        VolvoFH500 v = new VolvoFH500();
        v.lowerRamp();
        for (int i=0; i<VolvoFH500.CAR_CAPACITY; ++i) {
            v.load(new Saab95());
        }
        return v;
    }

    @Test(expected = RuntimeException.class)
    public void testFull() {
        VolvoFH500 v = _fill();
        v.load(new Saab95());
    }
}
