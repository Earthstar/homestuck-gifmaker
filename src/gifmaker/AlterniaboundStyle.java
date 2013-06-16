package gifmaker;

import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class contains information about Alterniabound style frames
 * @author earthstar
 *
 */
public class AlterniaboundStyle extends Style{
    public AlterniaboundStyle(){
        spriteLocation = "data/Alterniabound";
        backgroundWidth = 650;
        backgroundHeight = 450;
        //need to create image of textbox
        textbox = null;
        try{
        textbox = ImageIO.read(new File("data/Alterniabound/textbox.png"));
        } catch (IOException ex){
            System.out.println("can't find textbox");
        }
        leftTextboxLocation = new Point(100, 140);
        rightTextboxLocation = new Point(20, 140);
        leftTextStart = new Point(300, 175);
        rightTextStart = new Point(40, 175);
        maxTextWidth = 300;
        maxTextHeight = 300;
        font = new Font("Courier New", Font.BOLD, 15);
        talkspriteWidth = 210;
        talkspriteHeight = 260;
        talkspriteScale = 2.0;
        //Might need to mess with 
        //drawing image from different locations
        leftTalkspriteLocation = new Point(-160,-150);
        rightTalkspriteLocation = new Point(220,-150);
        pauseAfterEnd = 5000;
        defaultTiming = 100;
    }

}
