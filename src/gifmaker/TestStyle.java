package gifmaker;

import java.awt.Font;
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
        //Might need to mess with 
        //drawing image from different locations
        talkspriteLocation = new Point(-100,50);
        talkspriteScale = 2.0;
        textStart = new Point(300, 300);
        maxTextWidth = 300;
        maxTextHeight = 300;
        font = new Font("Courier New", Font.BOLD, 20);
        pauseAfterEnd = 5000;
    }
    
}
