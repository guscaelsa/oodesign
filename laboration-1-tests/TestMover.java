import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMover {
    @Test
    public void testTurnRight() {
        Mover m = new Mover();
        assertEquals(m.direction(), Direction.NORTH);
        turnAndCheck(m, 1, Direction.EAST);
        turnAndCheck(m, 1, Direction.SOUTH);
        turnAndCheck(m, 1, Direction.WEST);
        turnAndCheck(m, 1, Direction.NORTH);
        turnAndCheck(m, 2, Direction.SOUTH);
        turnAndCheck(m, 3, Direction.EAST);
        turnAndCheck(m, 8, Direction.EAST);
    }

    @Test
    public void testTurnLeft() {
        Mover m = new Mover();
        assertEquals(m.direction(), Direction.NORTH);
        turnAndCheck(m, -1, Direction.WEST);
        turnAndCheck(m, -1, Direction.SOUTH);
        turnAndCheck(m, -1, Direction.EAST);
        turnAndCheck(m, -1, Direction.NORTH);
        turnAndCheck(m, -2, Direction.SOUTH);
        turnAndCheck(m, -3, Direction.WEST);
        turnAndCheck(m, -8, Direction.WEST);
    }

    void turnAndCheck(Mover m, int amount, Direction expected_dir) {
        help.print("Amount:", amount, "Start:", m.direction());
        m = m.turn(amount);
        assertEquals(expected_dir, m.direction());
    }
}
