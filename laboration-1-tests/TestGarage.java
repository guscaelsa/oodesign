import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGarage {
    @Test(expected = RuntimeException.class)
    public void testExceedCapacity() {
        Garage<Scania> g = new Garage<>(0);
        g.checkin(0, new Scania());
    }

    @Test
    public void testInOut() {
        Garage<Scania> g = new Garage<>(1);
        Scania s = new Scania();
        g.checkin(0, s);
        assertEquals(s, g.checkout(0));
    }
}
