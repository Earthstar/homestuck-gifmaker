import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Practice class to draw frame
 * @author earthstar
 *
 */
public class DrawFrame {

    public static void main(String[] args){
        BufferedImage frame = new BufferedImage(650, 450, 5);
        BufferedImage sprite = null;
        BufferedImage background = null;
        try {
            sprite = ImageIO.read(new File("data/karkat_test_2.png"));
            background = ImageIO.read(new File("data/sample_background.png"));
        } catch (Exception e){
            System.out.println("can't read image");
        }
        //Draw background to frame
        Graphics2D g2d = frame.createGraphics();
        g2d.drawImage(background, 0, 0, null);
        //Add a geometric shape for textbox TODO color
        //Actually, I think I just want to make another image for this
        g2d.setColor(Color.DARK_GRAY);
        g2d.draw(new Rectangle2D.Double(100, 150, 530, 200));
        //Draw sprite, TODO need to stretch it to twice original size?
        g2d.drawImage(sprite, 0, 40, null);
        File output = new File("data/test_frame_2.png");
        try {
            ImageIO.write(frame, "png", output);
        } catch (IOException e){}
        
    }
}
