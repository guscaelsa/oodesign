import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

/**
 * A storage for a subset of <code>RoadVehicle</code>s
 * <p>
 *     Uses a user-provided <code>Queue</code> for storing the vehicles and
 *     allows pushing and popping vehicles to that queue.
 * </p>
 * <p>
 *     Has a (user-provided) maximum capacity and a maximum weight for any
 *     individual car pushed to the queue.
 * </p>
 * @param <V>
 */
public class VehicleStorage<V extends RoadVehicle> implements Iterable<V> {
    private final int capacity;
    private final int maxWeight;

    private final Queue<V> queue;

    /**
     * Create and return a last-in first-out queue for a type <code>T</code>
     */
    public static <T> Queue<T> lifoQueue() {
        return Collections.asLifoQueue(new ArrayDeque<>());
    }
    /**
     * Create and return a first-in first-out queue for a type <code>T</code>
     */
    public static <T> Queue<T> fifoQueue() {
        return new ArrayDeque<>();
    }

    public VehicleStorage(Queue<V> structure, int carCapacity, int maxCarWeight) {
        queue = structure;
        capacity = carCapacity;
        maxWeight = maxCarWeight;
    }

    /**
     * Push a vehicle to the internal queue
     * @param v The vehicle to be pushed
     */
    public void push(V v) {
        if (queue.size() >= capacity) {
            throw new IllegalStateException("Capacity has been reached");
        }
        if (v.weight > maxWeight) {
            throw new IllegalArgumentException("Vehicle to be added weighs too much");
        }
        queue.add(v);
    }

    /**
     * Pop the first vehicle of the internal queue
     * @return The vehicle that was popped
     */
    public V pop() {
        return queue.remove();
    }

    @Override
    public Iterator<V> iterator() {
        return queue.iterator();
    }
}
