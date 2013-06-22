package gifmaker;

import java.awt.image.BufferedImage;

public class TalkspriteFrameData implements Comparable<TalkspriteFrameData>{
    
    public final BufferedImage data;
    public final int index;
    public final int time;
    
    public TalkspriteFrameData(BufferedImage data, int index, int time){
        this.data = data;
        this.index = index;
        this.time = time;
    }
    
    @Override
    public int compareTo(TalkspriteFrameData other){
        return Integer.compare(this.index, other.index);
    }

}
