import java.util.HashMap;
import java.util.Map;

public class Garage<V extends RoadVehicle> {
    private final int max_capacity;
    Map<Integer, V> storage = new HashMap<>();

    public Garage(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public void checkin(int reference, V v) {
        if (storage.size() >= max_capacity) {
            throw new RuntimeException("Garage is full");
        }
        storage.put(reference, v);
    }

    public V checkout(int reference) {
        return storage.get(reference);
    }
}
