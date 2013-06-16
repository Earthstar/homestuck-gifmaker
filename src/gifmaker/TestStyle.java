package gifmaker;

import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Initialize a style for testing purposes TODO add max word length?
 * @author earthstar
 *
 */
public class TestStyle extends Style{
    
    public TestStyle(){
        spriteLocation = "data/Alterniabound";
        backgroundWidth = 650;
        backgroundHeight = 450;
        //need to create image of textbox
        textbox = null;
        try{
        textbox = ImageIO.read(new File("data/textbox.png"));
        } catch (IOException ex){
            System.out.println("can't find textbox");
        }
        leftTextboxLocation = new Point(100, 150);
        rightTextboxLocation = new Point(50, 150);
        leftTextStart = new Point(300, 200);
        rightTextStart = new Point(100, 200);
        maxTextWidth = 300;
        maxTextHeight = 300;
        font = new Font("Courier New", Font.BOLD, 15);
        talkspriteWidth = 210;
        talkspriteHeight = 260;
        talkspriteScale = 2.0;
        //Might need to mess with 
        //drawing image from different locations
        leftTalkspriteLocation = new Point(-160,-150);
        rightTalkspriteLocation = new Point(300,50);
        pauseAfterEnd = 5000;
        defaultTiming = 100;
    }
    
}
