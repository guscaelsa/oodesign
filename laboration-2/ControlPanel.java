import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner;
    int gasAmount = 100;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JButton addCarButton = new JButton("Add random car");
    JButton removeCarButton = new JButton("Remove random car");

    World world;

    ControlPanel(int w, int h, World world) {
        this.world = world;

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(gasAmount, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);
        this.add(gasButton);
        gasButton.setPreferredSize(new Dimension((int) (w*0.2), (int) (h*0.2)));
        this.add(brakeButton);
        brakeButton.setPreferredSize(new Dimension((int) (w*0.2), (int) (h*0.2)));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,6));
        buttons.setPreferredSize(new Dimension(w, (int) (h*0.6)));

        buttons.add(turboOnButton);
        buttons.add(liftBedButton);
        buttons.add(startButton);
        buttons.add(addCarButton);

        buttons.add(turboOffButton);
        buttons.add(lowerBedButton);
        buttons.add(stopButton);
        buttons.add(removeCarButton);
        this.add(buttons);

        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);

        gasButton.addActionListener(e -> gas(gasAmount));
        brakeButton.addActionListener(e -> brake(gasAmount));

        turboOffButton.addActionListener(e -> turboOff());
        turboOnButton.addActionListener(e -> turboOn());

        liftBedButton.addActionListener(e -> liftBed());
        lowerBedButton.addActionListener(e -> lowerBed());

        startButton.addActionListener(e -> startAll());
        stopButton.addActionListener(e -> stopAll());
        
        addCarButton.addActionListener(e -> addCar());
        removeCarButton.addActionListener(e -> removeCar());
    }

    private void addCar() {
        if (world.cars.size() < 10) {
            world.add(CarChanger.createRandomCar());
        }
    }

    private void removeCar() {
        if (world.cars.size() > 0) {
            CarChanger.removeRandomCar(world.cars);
        }
    }

    public void turboOff() {
        for (RoadVehicle car : world.cars) {
            CarChanger.turboOff(car);
        }
    }
    public void turboOn() {
        for (RoadVehicle car : world.cars) {
            CarChanger.turboOn(car);
        }
    }

    public void liftBed() {
        for (RoadVehicle car : world.cars) {
            CarChanger.liftBed(car);
        }
    }

    public void lowerBed() {
        for (RoadVehicle car : world.cars) {
            CarChanger.lowerBed(car);
        }
    }

    public void startAll() {
        for (RoadVehicle car : world.cars) {
            CarChanger.start(car);
        }
    }

    public void stopAll() {
        for (RoadVehicle car : world.cars) {
            CarChanger.stop(car);
        }
    }

    // Calls the gas method for each car once
    public void gas(int ticker_value) {
        double amount = ((double) ticker_value) / 100;
        for (RoadVehicle car : world.cars) {
            CarChanger.gas(car, amount);
        }
    }

    public void brake(int ticker_value) {
        double amount = ((double) ticker_value) / 100;
        for (RoadVehicle car : world.cars) {
            CarChanger.brake(car, amount);
        }
    }
}
