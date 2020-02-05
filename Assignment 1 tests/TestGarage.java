import org.junit.Test;

public class TestGarage {
    @Test(expected = RuntimeException.class)
    public void testExceedCapacity() {
        Garage<Scania> g = new Garage<>(0);
        g.checkin(0, new Scania());
    }
}
