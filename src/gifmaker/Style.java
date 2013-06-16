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
    protected String spriteLocation;
    protected int backgroundWidth;
    protected int backgroundHeight;
    protected BufferedImage textbox;
    //Distinguish between left-flipped and right-flipped sprites
    protected Point leftTextboxLocation;
    protected Point rightTextboxLocation;
    protected Point leftTextStart;
    protected Point rightTextStart;
    protected float maxTextWidth;
    protected int maxTextHeight;
    protected Font font;
    //Assumes that all talksprites will be the same width and height
    //and have the same location
    protected int talkspriteWidth;
    protected int talkspriteHeight;
    protected double talkspriteScale;
    protected Point leftTalkspriteLocation;
    protected Point rightTalkspriteLocation;
    protected int pauseAfterEnd;
    protected int defaultTiming;
    
    public String getSpriteLocation(){
        return spriteLocation;
    }
    
    public int getBackgroundWidth() {
        return backgroundWidth;
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public BufferedImage getTextbox() {
        return textbox;
    }

    public Point getLeftTextboxLocation() {
        return leftTextboxLocation;
    }
    
    public Point getRightTextboxLocation(){
        return rightTextboxLocation;
    }

    public int getTalkspriteWidth() {
        return talkspriteWidth;
    }

    public int getTalkspriteHeight() {
        return talkspriteHeight;
    }

    public Point getLeftTalkspriteLocation() {
        return leftTalkspriteLocation;
    }
    
    public Point getRightTalkspriteLocation() {
        return rightTalkspriteLocation;
    }
    
    public double getTalkspriteScale(){
        return talkspriteScale;
    }
    
    public Point getLeftTextStart(){
        return leftTextStart;
    }
    
    public Point getRightTextStart(){
        return rightTextStart;
    }
    
    public float getMaxTextWidth(){
        return maxTextWidth;
    }
    
    public int getMaxTextHeight(){
        return maxTextHeight;
    }
    
    public Font getFont(){
        return font;
    }
    
    public int getPauseAfterEnd(){
        return pauseAfterEnd;
    }
    
    public int getDefaultTiming(){
        return defaultTiming;
    }
}
