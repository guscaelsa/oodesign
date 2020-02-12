import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private static final int CAR_HEIGHT = 60;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<RoadVehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(0, 0));
        cc.cars.add(new Scania(100, 0));
        cc.cars.add(new Saab95(200, 0));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    public void turboOff() {
        for (RoadVehicle car : cars) {
            try {
                Saab95 c = (Saab95)car;
                c.setTurboOff();
            } catch (ClassCastException e) {
                // pass
            }
        }
    }
    public void turboOn() {
        for (RoadVehicle car : cars) {
            try {
                Saab95 c = (Saab95)car;
                c.setTurboOn();
            } catch (ClassCastException e) {
                // pass
            }
        }
    }

    public void liftBed() {
        for (RoadVehicle car : cars) {
            try {
                Scania c = (Scania) car;
                c.raisePlatform(Scania.MAX_ANGLE);
            } catch (RuntimeException e) {
                // pass
            }
        }
    }

    public void lowerBed() {
        for (RoadVehicle car : cars) {
            try {
                Scania c = (Scania) car;
                c.lowerPlatform(Scania.MAX_ANGLE);
            } catch (RuntimeException e) {
                // pass
            }
        }
    }

    public void startAll() {
        for (RoadVehicle car : cars) {
            try {
                car.startEngine();
            } catch (RuntimeException e) {
                // pass
            }
        }
    }

    public void stopAll() {
        for (RoadVehicle car : cars) {
            car.stopEngine();
        }
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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
                //frame.drawPanel.moveit(x, y);
                frame.drawPanel.updatePos(car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (RoadVehicle car : cars
                ) {
            try {
                car.gas(gas);
            } catch (RuntimeException e) {
                // pass
            }
        }
    }

    void brake(int amount) {
        double frac = ((double) amount) / 100;
        for (RoadVehicle car : cars
        ) {
            car.brake(frac);
        }
    }
}
