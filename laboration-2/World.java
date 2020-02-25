import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
    private static final int CAR_HEIGHT = 60;
    // A list of cars, modify if needed
    ArrayList<RoadVehicle> cars = new ArrayList<>();

    public void addAll(RoadVehicle[] roadVehicles) {
        cars.addAll(Arrays.asList(roadVehicles));
    }

    public void step() {
        for (RoadVehicle car : cars) {
            car.move();
            int x = (int) Math.round(car.getPos()[0]);
            int y = (int) Math.round(car.getPos()[1]);
            if (y + CAR_HEIGHT > CarView.DRAW_Y) {
                car.turnLeft();
                car.turnLeft();
                car.move();
            }
            if (y < 0) {
                car.turnLeft();
                car.turnLeft();
                car.move();
            }
        }
    }
}
