import java.util.HashMap;
import java.util.Map;

/**
 * A garage that can handle some subset of <code>RoadVehicle</code>s
 * <p>
 *     Vehicles are checked in using a reference number and can later
 *     be checked out using the same number
 * </p>
 */
public class Garage<V extends RoadVehicle> {
    private final int max_capacity;
    Map<Integer, V> storage = new HashMap<>();

    public Garage(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    /**
     * Check in a vehicle under a refererence number
     * @param reference reference number for this vehicle
     * @param v vehicle to be checked in
     */
    public void checkin(int reference, V v) {
        if (storage.size() >= max_capacity) {
            throw new RuntimeException("Garage is full");
        }
        if (storage.containsKey(reference)) {
            throw new RuntimeException("That reference number is already in use");
        }
        storage.put(reference, v);
    }

    /**
     * Checkout a vehicle that was checked in with the provided reference number
     * @param reference reference number for the vehicle to be checked out
     * @return the vehicle that was checked in with <code>reference</code>
     */
    public V checkout(int reference) {
        return storage.get(reference);
    }
}
