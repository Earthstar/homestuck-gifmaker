package gifmaker;

import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
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
        BufferedImage frame = new BufferedImage(style.getBackgroundWidth(),
                style.getBackgroundHeight(), 5);
        BufferedImage background = frameInfo.getParent().getBackgroundImage();
        BufferedImage sprite = frameInfo.getSprite();
        BufferedImage textbox = style.getTextbox();
        //Draw background to frame
        Graphics2D g2d = frame.createGraphics();
        g2d.drawImage(background, 0, 0, null);
        //Draw textbox to frame
        g2d.drawImage(textbox, style.getTextboxLocation().x, 
                style.getTextboxLocation().y, null);
        //draw sprite with correct scale factor to frame
        int startX = style.getTalkspriteLocation().x;
        int startY = style.getTalkspriteLocation().y;
        int scaledX = (int) (sprite.getWidth()*style.getTalkspriteScale());
        int scaledY = (int) (sprite.getHeight()*style.getTalkspriteScale());
        g2d.drawImage(sprite, 
                startX, startY, startX+scaledX, startY+scaledY,
                0, 0, sprite.getWidth(), sprite.getHeight(), null);
        //Draw text with different lines
        float textX = style.getTextStart().x;
        int textY = style.getTextStart().y;
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
