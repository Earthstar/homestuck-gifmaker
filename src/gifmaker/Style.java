package gifmaker;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Parent class of all styles. Should contain all specifications
 * that are the same across all frames.
 * TODO Not sure if should be interface or abstract class.
 * Does this need to store any implementations of methods?
 * @author earthstar
 *
 */
public abstract class Style {
    protected int backgroundWidth;
    protected int backgroundHeight;
    protected BufferedImage textbox;
    protected Point textboxLocation;
    //Assumes that all talksprites will be the same width and height
    //and have the same location
    protected int talkspriteWidth;
    protected int talkspriteHeight;
    protected Point talkspriteLocation;
    
    public int getBackgroundWidth() {
        return backgroundWidth;
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public BufferedImage getTextbox() {
        return textbox;
    }

    public Point getTextboxLocation() {
        return textboxLocation;
    }

    public int getTalkspriteWidth() {
        return talkspriteWidth;
    }

    public int getTalkspriteHeight() {
        return talkspriteHeight;
    }

    public Point getTalkspriteLocation() {
        return talkspriteLocation;
    }
    
}
