import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * Class contains static method to overlay text on an image
 * @author earthstar
 *
 */
public class TextOverlay {
    
    /**
     * Returns a new BufferedImage with text laid over it.
     * TODO make sure text doesn't overflow sides of image?
     * TODO add parameter to specify area that text should fill?
     * @param old
     * @param text
     * @return
     */
    public static BufferedImage overlayText(BufferedImage old, String text){
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(w, h, 5); //Idk what type "5" is
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, null);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Courier New", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        //Text appears at right top corner.
        int x = img.getWidth() - fm.stringWidth(text) - 5;
        int y = fm.getHeight();
        g2d.drawString(text, x, y);
        g2d.dispose();
        return img;
        
    }
    
    
    /**
     * Given an amount of text and a dimension, 
     * splits the text into lines that fit on the dimension. 
     * Text may overflow in the vertical direction 
     * @param text - string with spaces
     * @param x
     * @param y
     * @param font
     * @return
     */
    public static String[] splitText(String text, int x, int y, String font){
        return null;
    }
    
    public static void main(String[] args){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("data/blank.png"));
            
        } catch (Exception e){
            
        }
        BufferedImage newImg = overlayText(img, 
                "an extremely long piece of text that will spill over the available space in the image");
        //Write image to file
        File output = new File("data/text_overlay.png");
        try {
            ImageIO.write(newImg, "png", output);
        } catch (IOException e) {
            System.out.println("can't write file");
        }
    }
    
}
