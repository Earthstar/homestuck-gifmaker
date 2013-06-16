package gifmaker;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A container of BufferedImages that corresponds to one animation
 * @author earthstar
 *
 */
public class TalkspriteAnimation {
    List<TalkspriteFrameData> talkspriteImages;
    
    public TalkspriteAnimation(){
        talkspriteImages = new ArrayList<TalkspriteFrameData>();
    }
    
    /**
     * Adds a frame to an image by order n. 
     * What I want: ability to add arbitrary number: frame pairs and
     * have it be sorted by the number. Time to create a new class!
     * @param frame
     * @param n
     */
    public void addFrame(BufferedImage frame, int n, int time){
        TalkspriteFrameData data = 
                new TalkspriteFrameData(frame, n, time);
        talkspriteImages.add(data);
    }
    
    public List<BufferedImage> getBufferedFrames(){
        //Sort list, and then return a list of images
        Collections.sort(talkspriteImages);
        List<BufferedImage> toReturn = new ArrayList<BufferedImage>();
        for (TalkspriteFrameData d: talkspriteImages){
            toReturn.add((BufferedImage) d.data);
        }
        return toReturn;
    }
    
    public List<TalkspriteFrameData> getTalkspriteImages(){
        return talkspriteImages;
    }
    
    public int size(){
        return talkspriteImages.size();
    }

}
