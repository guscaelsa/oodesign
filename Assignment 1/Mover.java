/**
 * Helper class for Movable objects.
 * */
public class Mover {
    /**
     * Current <i>x</i> position
     * */
    public double x = 0;
    /**
     * Current <i>y</i> position
     * */
    public double y = 0;

    static final private int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final private int DIR_MOD = deltas.length;
    private int dir = 0;

    /**
     * Turn some whole number of steps "steps"
     *<p>
     *     Positive means clockwise, negative means counter-clockwise.
     *</p>
     * */
    void turn(int amount) {
        while (amount < 0) {
            amount += DIR_MOD;
        }
        dir += amount;
        dir %= DIR_MOD;
    }

    /**
     * Move by 'speed' is the current direction
     * */
    public void move(double speed) {
        int[] delta = deltas[dir];
        x += delta[0] * speed;
        y += delta[1] * speed;
    }

    /**
     * Get current direction
     * */
    public Direction direction() {
        switch (dir) {
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.EAST;
            case 2:
                return Direction.SOUTH;
            case 3:
                return Direction.WEST;
            default:
                throw new RuntimeException("Internal error");
        }
    }
}

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
}
