package gifmaker;

import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OpenboundStyle extends Style{
    
    public OpenboundStyle(){
    spriteLocation = "data/Openbound";
    backgroundLocation = "data/default_background.png";
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
    rightTalkspriteLocation = new Point(300,-150);
    pauseAfterEnd = 5000;
    defaultTiming = 200;
    }

}
