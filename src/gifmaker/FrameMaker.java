package gifmaker;

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
        return null; //TODO
    }
}
