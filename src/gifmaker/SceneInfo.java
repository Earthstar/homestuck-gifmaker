package gifmaker;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores all the information of one "scene".
 * Intended to be created by the GUI and initialized
 * @author earthstar
 *
 */
public class SceneInfo {

    private GIFMaker parent;
    //Orientation should only be "left" or "right". Enum?
    private String orientation; 
    private String text;
    private String textTiming; //could probably get as int
    private String talksprite;
    private String talkspriteTiming;
    private String textColor;
    //GUI class responsible for opening background?
    //Or should this class take in a file?
    private BufferedImage background;
    private List<FrameInfo> frameInfos;
    private List<BufferedImage> frames;
    private int frameTiming;
    
    /**
     * Initializes empty SceneInfo, which GUI is intended to
     * fill out. All times are in milliseconds.
     * @param parent
     */
    public SceneInfo(GIFMaker parent){
        this.parent = parent;
        setOrientation("left");
        setText(null);
        setTextTiming("5"); //TODO mess with timing
        setTalksprite(null); //Create SBaHJ defaults?
        setTalkspriteTiming("200");
        setBackground(null);
        FrameMaker.setStyle(parent.getStyle());
        frameInfos = new ArrayList<FrameInfo>();
        frames = new ArrayList<BufferedImage>();
    }
    
    /**
     * Calculates time between frames. Must be recalculated every time
     * one of them changes?
     * @param talkspriteTiming - must be > 0
     * @param textTiming - must be > 0
     * @return
     */
    public int calculateTiming(int talkspriteTiming, int textTiming){
        return textTiming; //TODO is this all? This is okay if
        //talkspriteTiming is evenly divisible by textTiming. What if not?
    }
    
    /**
     * Calculates a series of FrameInfos from the current settings
     * 
     * Needs individual BufferedImage of sprites
     * 
     * @return
     */
    public List<FrameInfo> makeFrameInfoList(){
         //TODO
        return null;
    }
    
    /**
     * Sets this.frames to a list of BufferedImages of frames. 
     * Should be called by GIFMaker?
     */
    public void makeFrames(){
        for (FrameInfo f:frameInfos){
            frames.add(FrameMaker.makeFrame(f));
        }
    }
    
    /**
     * Adds frames to a AnimatedGifEncoder. Should be called by
     * GIFMaker
     */
    public void addFrames(AnimatedGifEncoder gifEncoder){
        //TODO
    }
    
    
    /**
     * Getters and setters
     */

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public GIFMaker getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextTiming() {
        return textTiming;
    }

    public void setTextTiming(String textTiming) {
        this.textTiming = textTiming;
    }

    public String getTalksprite() {
        return talksprite;
    }

    public void setTalksprite(String talksprite) {
        this.talksprite = talksprite;
    }

    public String getTalkspriteTiming() {
        return talkspriteTiming;
    }

    public void setTalkspriteTiming(String talkspriteTiming) {
        this.talkspriteTiming = talkspriteTiming;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }


    public List<BufferedImage> getFrames() {
        return frames;
    }
    
}
