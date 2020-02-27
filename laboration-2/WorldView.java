import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// This panel represent the animated part of the view with the car images.

public class WorldView extends JPanel{
    BufferedImage volvoImage;
    BufferedImage scaniaImage;
    BufferedImage saabImage;

    World world;

    // Initializes the panel and reads the images
    public WorldView(int x, int y, World world) {
        this.world = world;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(WorldView.class.getResourceAsStream("pics/Volvo240.jpg"));
            scaniaImage = ImageIO.read(WorldView.class.getResourceAsStream("pics/Scania.jpg"));
            saabImage = ImageIO.read(WorldView.class.getResourceAsStream("pics/Saab95.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (RoadVehicle car : world.cars) {
            double x = car.getPos()[0];
            double y = car.getPos()[1];
            BufferedImage img;
            if (car instanceof Scania) {
                img = scaniaImage;
            } else if (car instanceof Saab95) {
                img = saabImage;
            } else if (car instanceof Volvo240) {
                img = volvoImage;
            } else {
                throw new RuntimeException("Unknown car type");
            }
            g.drawImage(img, (int)x, (int)y, null);
        }
    }
}
