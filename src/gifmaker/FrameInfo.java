package gifmaker;

import java.awt.image.BufferedImage;

/**
 * Class contains information specific to one frame, which 
 * FrameMaker takes in to create a frame. Should be 
 * created by SceneInfo. FrameMaker stores time and the final
 * frame, which can then be added to a AnimatedGifEncoder
 * @author earthstar
 *
 */
public class FrameInfo {
    
    SceneInfo parent;
    String text;
    BufferedImage sprite;
    int timing;
    BufferedImage frame;
    
    public FrameInfo(SceneInfo parent, String text, 
            BufferedImage sprite, int timing){
        this.parent = parent;
        this.text = text;
        this.sprite = sprite;
        this.timing = timing;
        frame = FrameMaker.makeFrame(this);
    }
    
    public String getText(){
        return text;
    }
    
    public SceneInfo getParent(){
        return parent;
    }
    
    public BufferedImage getSprite(){
        return sprite;
    }
    
    public int getTiming(){
        return timing;
    }
    
    public BufferedImage getFrame(){
        return frame;
    }

}
