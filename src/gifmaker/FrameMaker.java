package gifmaker;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Static class that creates individual frames. Abstract type?
 * @author earthstar
 *
 */
public class FrameMaker {

    private static Style style;
    
    public static void setStyle(Style style){
        FrameMaker.style = style;
    }
    
    /**
     * Makes frames out of the information in SceneInfo and Style.
     * Method intended to be called by SceneInfo
     */
    public static BufferedImage makeFrame(FrameInfo frameInfo){
        BufferedImage frame = new BufferedImage(style.getBackgroundWidth(),
                style.getBackgroundHeight(), 5);
        BufferedImage background = frameInfo.getParent().getBackgroundImage();
        BufferedImage sprite = frameInfo.getSprite();
        BufferedImage textbox = style.getTextbox();
        //Draw background to frame
        Graphics2D g2d = frame.createGraphics();
        g2d.drawImage(background, 0, 0, null);
        //Draw textbox to frame
        g2d.drawImage(textbox, style.getTextboxLocation().x, 
                style.getTextboxLocation().y, null);
        //draw sprite with correct scale factor to frame
        int scaledX = (int) (sprite.getWidth()*style.getTalkspriteScale());
        int scaledY = (int) (sprite.getHeight()*style.getTalkspriteScale());
        g2d.drawImage(sprite, 
                0, 0, scaledX, scaledY,
                0, 0, sprite.getWidth(), sprite.getHeight(), null);
        return frame;
    }
    
    
}
