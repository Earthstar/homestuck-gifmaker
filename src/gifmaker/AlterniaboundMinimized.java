package gifmaker;

import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlterniaboundMinimized extends Style{
    public AlterniaboundMinimized(){
        spriteLocation = "data/Alterniabound";
        backgroundLocation = "data/default_background_minimized.png";
        backgroundWidth = 325;
        backgroundHeight = 225;
        //need to create image of textbox
        textbox = null;
        try{
        textbox = ImageIO.read(new File("data/Openbound/openbound_textbox_minimized.png"));
        } catch (IOException ex){
            System.out.println("can't find textbox");
        }
        leftTextboxLocation = new Point(50, 70);
        rightTextboxLocation = new Point(10, 70);
        leftTextStart = new Point(150, 87);
        rightTextStart = new Point(20, 87);
        maxTextWidth = 150;
        maxTextHeight = 150;
        font = new Font("Courier New", Font.BOLD, 15);
        talkspriteWidth = 105;
        talkspriteHeight = 130;
        talkspriteScale = 1.0;
        //Might need to mess with 
        //drawing image from different locations
        leftTalkspriteLocation = new Point(-80,-75);
        rightTalkspriteLocation = new Point(110,-75);
        pauseAfterEnd = 4000;
        defaultTiming = 100;
    }
}
