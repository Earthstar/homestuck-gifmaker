package gifmaker;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Initialize a style for testing purposes
 * @author earthstar
 *
 */
public class TestStyle extends Style{
    
    public TestStyle(){
        backgroundWidth = 650;
        backgroundHeight = 450;
        //need to create image of textbox
        textbox = null;
        try{
        textbox = ImageIO.read(new File("data/textbox.png"));
        } catch (IOException ex){
            System.out.println("can't find textbox");
        }
        textboxLocation = new Point(100, 150);
        talkspriteWidth = 210;
        talkspriteHeight = 260;
        talkspriteLocation = new Point(0,0);
        talkspriteScale = 2.0;
    }
    
}
