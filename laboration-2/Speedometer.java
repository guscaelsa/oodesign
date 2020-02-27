import javax.swing.*;
import java.awt.*;

public class Speedometer extends JPanel {
    World world;
    JLabel text = new JLabel();

    public Speedometer(World world) {
        this.world = world;
        this.add(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        StringBuilder newText = new StringBuilder("<html>");
        for (RoadVehicle car : world.cars) {
            newText.append(car.getModelName());
            newText.append(": ");
            newText.append(car.getCurrentSpeed());
            newText.append("<br>");
        }
        text.setText(newText.toString());
        super.paintComponent(g);
    }
}
