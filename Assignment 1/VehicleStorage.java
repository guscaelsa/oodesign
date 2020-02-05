import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

public class VehicleStorage<V extends Vehicle> implements Iterable<V> {
    private final int capacity;
    private final int maxWeight;

    private final Queue<V> queue;

    public static <T> Queue<T> lifoQueue() {
        return Collections.asLifoQueue(new ArrayDeque<>());
    }
    public static <T> Queue<T> fifoQueue() {
        return new ArrayDeque<>();
    }

    public VehicleStorage(Queue<V> structure, int carCapacity, int maxCarWeight) {
        queue = structure;
        capacity = carCapacity;
        maxWeight = maxCarWeight;
    }

    public void push(V v) {
        if (queue.size() >= capacity) {
            throw new IllegalStateException("Capacity has been reached");
        }
        help.print(queue.size(), capacity);
        if (v.weight > maxWeight) {
            throw new IllegalArgumentException("Vehicle to be added weighs too much");
        }
        queue.add(v);
    }

    public V pop() {
        return queue.remove();
    }

    @Override
    public Iterator<V> iterator() {
        return queue.iterator();
    }
}
