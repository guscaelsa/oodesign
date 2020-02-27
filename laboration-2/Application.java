import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private static final int X = 800;
    private static final int Y = 600;
    private final int delay = 50;

    int gasAmount;

    Application() {
        this.setTitle("CarSim 2.0");
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        RoadVehicle[] roadVehicles = new RoadVehicle[]{
                new Volvo240(0, 0),
                new Scania(100, 0),
                new Saab95(200, 0)};
        for (RoadVehicle car : roadVehicles) {
            car.gas(1);
        }

        World world = new World();
        world.addAll(roadVehicles);
        WorldView drawPanel = new WorldView(X,Y, world);
        this.add(drawPanel);

        Timer timer = new Timer(delay, e -> {
            world.step();
            drawPanel.repaint();
        });
        timer.start();

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Application app = new Application();
//        // Instance of this class
//        CarController cc = new CarController(new RoadVehicle[]{
//                new Volvo240(0, 0),
//                new Scania(100, 0),
//                new Saab95(200, 0)});
//
//        // Start a new view and send a reference of self
//        cc.frame = new CarView("CarSim 1.0", cc);
//
//        // Start the timer
//        cc.timer.start();
    }
}
