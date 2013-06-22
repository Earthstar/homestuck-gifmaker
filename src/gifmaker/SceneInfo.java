package gifmaker;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Class that stores all the information of one "scene".
 * Intended to be created by the GUI and initialized by GUI buttons
 * @author earthstar
 *
 */
public class SceneInfo {

    private GifMaker parent;
    //Orientation should only be "left" or "right". Enum?
    private boolean isLeft; 
    private String text;
    private int textTiming; //could probably get as int
    //talksprite should be a string that is in parent's animationMap
    private String talksprite;
    private int talkspriteTiming;
    private Color textColor; //TODO not sure format of color
    //GUI class responsible for opening background?
    //Or should this class take in a file?
    private String background;
    private BufferedImage backgroundImage; //cache background img
    private List<FrameInfo> frameInfos;
    private List<BufferedImage> frames;
    private int frameTiming;
    
    /**
     * Initializes empty SceneInfo with defaults, which GUI is intended to
     * fill out. All times are in milliseconds.
     * @param parent
     */
    public SceneInfo(GifMaker parent){
        this.parent = parent;
        setIsLeft(true);
        setText(null);
        setTextTiming(10); //TODO mess with timing
        setTalksprite(null); //Create SBaHJ defaults?
        talkspriteTiming = this.parent.getStyle().getDefaultTiming()/2; //TODO hack fix
        setBackground(parent.getStyle().getBackgroundLocation());
        backgroundImage = null;
        FrameMaker.setStyle(parent.getStyle());
        frameInfos = new ArrayList<FrameInfo>();
        frames = new ArrayList<BufferedImage>();
        textColor = Color.BLACK;
    }
    
    /**
     * Returns backgroundImage, caches it.
     * @return
     */
    public BufferedImage getBackgroundImage(){
        if (backgroundImage != null){
            return backgroundImage;
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(background));
        } catch (Exception e){
            System.out.println("can't read background");
            return null;
        }
        return img;
    }
    
    /**
     * Calculates time between frames. Must be recalculated every time
     * one of them changes?
     * @param talkspriteTiming - must be > 0
     * @param textTiming - must be > 0
     * @return
     */
    public int calculateTiming(){
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
    public void makeFrameInfoList(){
        //Get individual frames
        System.out.println(talksprite);
        TalkspriteAnimation a = parent.getAnimation(talksprite);
        if (a == null){
            System.out.println(parent.getAnimationMap());
        }
        List<BufferedImage> images = a.getBufferedFrames();
        //Calculate how many letters in text must appear with each animation
        //May be some rounding error
        int numLettersPerTalk = talkspriteTiming/
                textTiming;
        List<FrameInfo> frameInfoList = new ArrayList<FrameInfo>();
        //Stores index of current position
        int imagesPosition = 0;
        //For each letter in text, create a new FrameInfo 
        //Every i letters, change to the next sprite in images
        //TODO need to mess with order images are shown?
        for (int i = 0; i < text.length(); i++){
            if (i % numLettersPerTalk == 0){
                imagesPosition = (imagesPosition + 1) % images.size();
            }
            String toAdd = text.substring(0, i+1);
            FrameInfo info = new FrameInfo(this, toAdd, 
                    images.get(imagesPosition), calculateTiming());
            frameInfoList.add(info);
        }
        //Calculate time relative to number of words the character speaks
        int endPause = 5000; //10*text.length();
        //Add a couple of frames of the character's mouth moving
        for (int i = 0; 
                i < endPause/talkspriteTiming; 
                i++){
            imagesPosition = (imagesPosition + 1) % images.size();
            frameInfoList.add(new FrameInfo(this, text, 
                    images.get(imagesPosition), talkspriteTiming*2)); //hack fix TODO
        }
        frameInfos = frameInfoList;
    }
    
    /**
     * Get a preview frame of this scene. Returns image of first sprite
     * frame and all text. Animation must be set beforehand
     * @return
     */
    public BufferedImage getPreview(){
        TalkspriteAnimation a = parent.getAnimation(talksprite);
        List<BufferedImage> images = a.getBufferedFrames();
        BufferedImage previewSprite = images.get(0);
        FrameInfo previewFrameInfo = new FrameInfo(this, 
                this.getText(), previewSprite, 1);
        return previewFrameInfo.getFrame();
    }
    
    /**
     * Adds frames to a AnimatedGifEncoder. Should be called by
     * GIFMaker
     */
    public void addFrames(AnimatedGifEncoder gifEncoder){
        
        for (FrameInfo f: frameInfos){
            gifEncoder.setDelay(f.getTiming());
            gifEncoder.addFrame(f.getFrame());
        }
        
    }
    
    
    
    /**
     * Getters and setters
     */

    public boolean isLeft() {
        return isLeft;
    }

    public void setIsLeft(boolean orientation) {
        this.isLeft = orientation;
    }

    public GifMaker getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextTiming() {
        return textTiming;
    }

    public void setTextTiming(int textTiming) {
        this.textTiming = textTiming;
    }

    public String getTalksprite() {
        return talksprite;
    }

    public void setTalksprite(String talksprite) {
        this.talksprite = talksprite;
    }

    public int getTalkspriteTiming() {
        return talkspriteTiming;
    }

    public void setTalkspriteTiming(int talkspriteTiming) {
        this.talkspriteTiming = talkspriteTiming/2;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Color getTextColor() {
        return textColor;
    }
    
    /**
     * Sets color to hex code. Does not change color if invalid hex code.
     * @param hex - a valid hex code. 
     */
    public void setTextColor(String hex){
        try{
        Color.decode(hex);
        } catch (NumberFormatException e){
            System.out.println("invalid text color code");
        }
    }
    
    /**
     * Sets color to hex code.
     * @param hex
     */
    public void setTextColor(int hex){
        try{
            this.textColor = new Color(hex);
        } catch (Exception e){
            System.out.println("wrong hex code");
        }
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }


    public List<BufferedImage> getFrames() {
        return frames;
    }
    
    @Override
    public String toString(){
        return talksprite + " " + text;
    }
   
}
