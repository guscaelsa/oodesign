/**
 * Helper class for Movable objects.
 * */
public class Mover {
    /**
     * Current <i>x</i> position
     * */
    public final double x;
    /**
     * Current <i>y</i> position
     * */
    public final double y;

    static final private int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final private int DIR_MOD = deltas.length;
    private final int dir;

    public Mover() {
        this.x = 0;
        this.y = 0;
        this.dir = 0;
    }

    private Mover(double x, double y, int d) {
        this.x = x;
        this.y = y;
        this.dir = d;
    }

    /**
     * Turn some whole number of steps "steps"
     *<p>
     *     Positive means clockwise, negative means counter-clockwise.
     *</p>
     * */
    Mover turn(int amount) {
        while (amount < 0) {
            amount += DIR_MOD;
        }
        int d = (dir + amount) % DIR_MOD;
//        dir += amount;
//        dir %= DIR_MOD;
        return new Mover(x, y, d);
    }

    /**
     * Move by 'speed' is the current direction
     * */
    public Mover move(double speed) {
        int[] delta = deltas[dir];
        return new Mover(x + delta[0] * speed, y + delta[1] * speed, dir);
//        x += delta[0] * speed;
//        y += delta[1] * speed;
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

    public Mover at(double[] pos) {
        return new Mover(pos[0], pos[1], dir);
    }
}

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
}
