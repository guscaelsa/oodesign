import java.util.ArrayList;
import java.util.Arrays;

public class World {
    // A list of cars, modify if needed
    ArrayList<RoadVehicle> cars = new ArrayList<>();

    public void addAll(RoadVehicle[] roadVehicles) {
        cars.addAll(Arrays.asList(roadVehicles));
    }
}
