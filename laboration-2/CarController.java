import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ButtonController {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer;

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    private World world = new World();

    public CarController(RoadVehicle[] roadVehicles) {
        world.addAll(roadVehicles);
        timer = new Timer(delay, e -> {
            world.step();
            reDraw();
        });
    }

    private void reDraw() {
        for (RoadVehicle car : world.cars) {
            frame.drawPanel.updatePos(car);
        }
        // repaint() calls the paintComponent method of the panel
        frame.drawPanel.repaint();
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController(new RoadVehicle[]{
                new Volvo240(0, 0),
                new Scania(100, 0),
                new Saab95(200, 0)});

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    @Override
    public void turboOff() {
        for (RoadVehicle car : world.cars) {
            CarChanger.turboOff(car);
        }
    }
    @Override
    public void turboOn() {
        for (RoadVehicle car : world.cars) {
            CarChanger.turboOn(car);
        }
    }

    @Override
    public void liftBed() {
        for (RoadVehicle car : world.cars) {
            CarChanger.liftBed(car);
        }
    }

    @Override
    public void lowerBed() {
        for (RoadVehicle car : world.cars) {
            CarChanger.lowerBed(car);
        }
    }

    @Override
    public void startAll() {
        for (RoadVehicle car : world.cars) {
            CarChanger.start(car);
        }
    }

    @Override
    public void stopAll() {
        for (RoadVehicle car : world.cars) {
            CarChanger.stop(car);
        }
    }

    // Calls the gas method for each car once
    @Override
    public void gas(int ticker_value) {
        double amount = ((double) ticker_value) / 100;
        for (RoadVehicle car : world.cars) {
            CarChanger.gas(car, amount);
        }
    }

    @Override
    public void brake(int ticker_value) {
        double amount = ((double) ticker_value) / 100;
        for (RoadVehicle car : world.cars) {
            CarChanger.brake(car, amount);
        }
    }
}
