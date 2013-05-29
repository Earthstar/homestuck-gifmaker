package gifmaker;

import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Parent class of all styles. Should contain all specifications
 * that are the same across all frames.
 * TODO Not sure if should be interface or abstract class.
 * Does this need to store any implementations of methods?
 * All times are in seconds.
 * @author earthstar
 *
 */
public abstract class Style {
    protected int backgroundWidth;
    protected int backgroundHeight;
    protected BufferedImage textbox;
    protected Point textboxLocation;
    protected Point textStart;
    protected int maxTextWidth;
    protected int maxTextHeight;
    protected Font font;
    //Assumes that all talksprites will be the same width and height
    //and have the same location
    protected int talkspriteWidth;
    protected int talkspriteHeight;
    protected double talkspriteScale;
    protected Point talkspriteLocation;
    protected int pauseAfterEnd;
    
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
    
    public double getTalkspriteScale(){
        return talkspriteScale;
    }
    
    public Point getTextStart(){
        return textStart;
    }
    
    public int getMaxTextWidth(){
        return maxTextWidth;
    }
    
    public int getMaxTextHeight(){
        return maxTextHeight;
    }
    
    public Font getFont(){
        return font;
    }
}
