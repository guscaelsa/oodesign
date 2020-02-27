import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// This panel represent the animated part of the view with the car images.

public class WorldView extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage scaniaImage;
    BufferedImage saabImage;
    // To keep track of a single cars position
    Point volvoPoint = new Point();
    Point scaniaPoint = new Point();
    Point saabPoint = new Point();

    World world;

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        volvoPoint.x = x;
        volvoPoint.y = y;
    }

    void updatePos(RoadVehicle car) {
        if (car instanceof Volvo240) {
            updatePos((Volvo240)car);
        } else if (car instanceof Scania) {
            updatePos((Scania)car);
        } else if (car instanceof Saab95) {
            updatePos((Saab95)car);
        } else {
            throw new RuntimeException("Unknown type of car!");
        }
    }
    void updatePos(Volvo240 car) {
        volvoPoint.x = (int)car.getPos()[0];
        volvoPoint.y = (int)car.getPos()[1];
    }
    void updatePos(Scania car) {
        scaniaPoint.x = (int)car.getPos()[0];
        scaniaPoint.y = (int)car.getPos()[1];
    }
    void updatePos(Saab95 car) {
        saabPoint.x = (int)car.getPos()[0];
        saabPoint.y = (int)car.getPos()[1];
    }
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
    // TODO: Change to suit your needs.
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
//        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
//        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null); // see javadoc for more info on the parameters
//        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null); // see javadoc for more info on the parameters
    }
}
