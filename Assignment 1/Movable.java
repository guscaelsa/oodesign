/**
 * Something that can be moved
 * */
public interface Movable {
    /**
     * Moves the Movable forward
     *<p>
     *     In the direction it's currently facing, by a speed determined by the implementor.
     *</p>
     * */
    void move();

    /**
     * Turn left
     *<p>
     *     Change the current facing 90 degrees to the left.
     *</p>
     * */
    void turnLeft();

    /**
     * Turn right
     *<p>
     *     Change the current facing 90 degrees to the right.
     *</p>
     * */
    void turnRight();

    /**
     * Get the current position
     *<p>
     *     Returns a double-array with length two ({x, y}).
     * */
    double[] getPos();

    double getCurrentSpeed();
}
