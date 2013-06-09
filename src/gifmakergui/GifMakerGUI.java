package gifmakergui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gifmaker.GifMaker;

/**
 * Main GUI class. Creates one JFrame, and multiple 
 * JPanels that you can toggle between.
 * @author earthstar
 *
 */
public class GifMakerGUI{
    
    GifMaker parent;
    JFrame frame;
    int currentPanel;
    //Store panels so you can go backwards
    List<JPanel> panels;
    List<GUIClass> guis;
    
    public GifMakerGUI(GifMaker parent){
        this.parent = parent;
        frame = new JFrame("GifMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Start off at first panel
        currentPanel = 0;
        guis = new ArrayList<GUIClass>(Arrays.asList(
                new StyleSelectGUI(this, frame, 
                        new JPanel()),
                new InputSceneGUI(this, frame, new JPanel()),
                new PreviewGUI(),
                new SaveGUI()
                ));
        
    }
    
    /**
     * Should only be used for displaying the 0th entry
     */
    public void start(){
        JPanel panel0 = guis.get(0).getPanel();
        frame.add(panel0);
        frame.pack();
        frame.setVisible(true);
    }
    
    public GifMaker getParent(){
        return parent;
    }
    
    /**
     * Returns the next JPanel for the GUIs
     * Increments current panel, generates next panel
     * @return
     */
    public JPanel getNextPanel(){
        return new JPanel(); //TODO
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                GifMaker g = new GifMaker();
                GifMakerGUI gui = new GifMakerGUI(g);
                gui.start();
                /*JFrame frame = new JFrame("name");
                JPanel panel = new JPanel();
                panel.add(new JButton("foobar"));
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);*/
            }
        });
        
    }

}
