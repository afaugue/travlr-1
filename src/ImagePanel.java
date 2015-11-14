import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by lbontecou on 10/26/15.
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(URL file_path) {
        try {
            image = ImageIO.read(file_path);
        } catch (IOException ex) {
            System.out.println("Failure Displaying Image.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();

        int h = (int)Math.round(height*.25);
        int w = (int)Math.round(width*.25);
        g.drawImage(image, 0, 0, width, height, this); // see javadoc for more info on the parameters
    }

}
