import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CarChanger {
    public static void turboOff(RoadVehicle car) {
        try {
            Saab95 c = (Saab95)car;
            c.setTurboOff();
        } catch (ClassCastException e) {
            // pass
        }
    }

    public static void turboOn(RoadVehicle car) {
        try {
            Saab95 c = (Saab95)car;
            c.setTurboOn();
        } catch (ClassCastException e) {
            // pass
        }
    }

    public static void liftBed(RoadVehicle car) {
        try {
            Scania c = (Scania) car;
            c.raisePlatform(Scania.MAX_ANGLE);
        } catch (RuntimeException e) {
            // pass
        }
    }

    public static void lowerBed(RoadVehicle car) {
        try {
            Scania c = (Scania) car;
            c.lowerPlatform(Scania.MAX_ANGLE);
        } catch (RuntimeException e) {
            // pass
        }
    }

    public static void start(RoadVehicle car) {
        try {
            car.startEngine();
        } catch (RuntimeException e) {
            // pass
        }
    }

    public static void stop(RoadVehicle car) {
        car.stopEngine();
    }

    public static void gas(RoadVehicle car, double amount) {
        try {
            car.gas(amount);
        } catch (RuntimeException e) {
            // pass
        }
    }

    public static void brake(RoadVehicle car, double amount) {
        car.brake(amount);
    }

    static int randint(int high) {
        return ThreadLocalRandom.current().nextInt(0, high);
    }

    public static void removeRandomCar(List<RoadVehicle> cars) {
        cars.remove(randint(cars.size()));
    }

    public static RoadVehicle createRandomCar() {
        switch (randint(3)) {
            case 0:
                return new Saab95();
            case 1:
                return new Scania();
            case 2:
                return new Volvo240();
            default:
                throw new RuntimeException("Dumb mistake when generating random integer");
        }
    }
}
