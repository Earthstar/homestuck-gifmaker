package gifmaker;

import java.awt.image.BufferedImage;

/**
 * Class contains information specific to one frame, which 
 * FrameMaker takes in to create a frame. Should be 
 * created by SceneInfo
 * @author earthstar
 *
 */
public class FrameInfo {
    
    SceneInfo parent;
    String text;
    BufferedImage sprite;
    
    public FrameInfo(SceneInfo parent, String text, BufferedImage sprite){
        this.parent = parent;
        this.text = text;
        this.sprite = sprite;
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

}
