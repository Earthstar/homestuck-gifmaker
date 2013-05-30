package gifmaker;

import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;

import javax.imageio.ImageIO;

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
    
    public static BufferedImage flipImage(BufferedImage img){
        BufferedImage flipped = new BufferedImage(img.getWidth(), 
                img.getHeight(), img.getType());
        AffineTransform tran = AffineTransform.getTranslateInstance(
                img.getWidth(), 0);
        AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
        tran.concatenate(flip);
        Graphics2D g = flipped.createGraphics();
        g.setTransform(tran);
        g.drawImage(img, 0, 0, null);
        g.dispose();
        try {
            ImageIO.write(flipped, "png", new File("data/flipped.png"));
        } catch (IOException e) {
            System.out.println("can't write image");
        }
        return flipped;
    }
    
    /**
     * Makes frames out of the information in SceneInfo and Style.
     * Method intended to be called by SceneInfo
     */
    public static BufferedImage makeFrame(FrameInfo frameInfo){
        //get locations based on orientation
        int spriteX;
        int spriteY;
        float textX;
        int textY;
        int textboxX;
        int textboxY;
        BufferedImage sprite;
        if (frameInfo.parent.isLeft()){
            spriteX = style.getLeftTalkspriteLocation().x;
            spriteY = style.getLeftTalkspriteLocation().y;
            textX = style.getLeftTextStart().x;
            textY = style.getLeftTextStart().y;
            textboxX = style.getLeftTextboxLocation().x;
            textboxY = style.getLeftTextboxLocation().y;
            sprite = frameInfo.getSprite();
        } else{
            spriteX = style.getRightTalkspriteLocation().x;
            spriteY = style.getRightTalkspriteLocation().y;
            textX = style.getRightTextStart().x;
            textY = style.getRightTextStart().y;
            textboxX = style.getRightTextboxLocation().x;
            textboxY = style.getRightTextboxLocation().y;
            sprite = FrameMaker.flipImage(frameInfo.getSprite());
        }
        
        BufferedImage frame = new BufferedImage(style.getBackgroundWidth(),
                style.getBackgroundHeight(), 5);
        BufferedImage background = frameInfo.getParent().getBackgroundImage();
        BufferedImage textbox = style.getTextbox();
        //Draw background to frame
        Graphics2D g2d = frame.createGraphics();
        g2d.drawImage(background, 0, 0, null);
        //Draw textbox to frame
        g2d.drawImage(textbox, textboxX, 
                textboxY, null);
        //draw sprite with correct scale factor to frame
        int scaledX = (int) (sprite.getWidth()*style.getTalkspriteScale());
        int scaledY = (int) (sprite.getHeight()*style.getTalkspriteScale());
        g2d.drawImage(sprite, 
                spriteX, spriteY, spriteX+scaledX, spriteY+scaledY,
                0, 0, sprite.getWidth(), sprite.getHeight(), null);
        //Draw text with different lines
        //code from Oracle tutorial
        AttributedString as = new AttributedString(frameInfo.getText());
        AttributedCharacterIterator paragraph = as.getIterator();
        as.addAttribute(TextAttribute.FONT, style.getFont());
        g2d.setColor(frameInfo.parent.getTextColor());
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        float breakWidth = style.getMaxTextWidth();
        float drawPosY = textY;
        lineMeasurer.setPosition(paragraphStart);
        while (lineMeasurer.getPosition() < paragraphEnd){
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
            drawPosY += layout.getAscent();
            layout.draw(g2d, textX, drawPosY);
            drawPosY += layout.getDescent() + layout.getLeading();
        }
        return frame;
    }
    
    
}
