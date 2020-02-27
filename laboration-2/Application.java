import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private static final int X = 800;
    private static final int Y = 600;
    private final int delay = 50;

    Application(RoadVehicle[] roadVehicles) {
        this.setTitle("CarSim 2.0");
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        int SIM_HEIGHT = (int) (Y * 0.65);
        int SIM_WIDTH = (int) (X * 0.8);

        World world = new World(SIM_HEIGHT);
        world.addAll(roadVehicles);

        WorldView drawPanel = new WorldView(SIM_WIDTH, SIM_HEIGHT, world);
        this.add(drawPanel);
        Speedometer speedometer = new Speedometer(world);
        this.add(speedometer);
        ControlPanel controlPanel = new ControlPanel(X, Y - SIM_HEIGHT, world);
        this.add(controlPanel);


        Timer timer = new Timer(delay, e -> {
            world.step();
            drawPanel.repaint();
            speedometer.repaint();
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
        new Application(new RoadVehicle[]{
                new Volvo240(0, 0),
                new Scania(100, 0),
                new Saab95(200, 0)});
    }
}
