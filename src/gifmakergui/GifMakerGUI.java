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
    JPanel p1, p2, p3, p4;
    StyleSelectGUI styleSelect;
    InputSceneGUI inputScene;
    PreviewGUI preview;
    SaveGUI save;
    
    public GifMakerGUI(GifMaker parent){
        this.parent = parent;
        frame = new JFrame("GifMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); //doesnt' work? TODO
        //Start off at first panel
        currentPanel = 0;
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        GroupLayout layout1 = new GroupLayout(p1);
        GroupLayout layout2 = new GroupLayout(p2);
        GroupLayout layout3 = new GroupLayout(p3);
        GroupLayout layout4 = new GroupLayout(p4);
        p1.setLayout(layout1);
        p2.setLayout(layout2);
        p3.setLayout(layout3);
        p4.setLayout(layout4);
        styleSelect = new StyleSelectGUI(this, frame, 
                p1);
        inputScene = new InputSceneGUI(this, frame, p2);
        preview = new PreviewGUI();
        save = new SaveGUI();
    }
    
    /**
     * Should only be used for displaying the StyleSelect GUI
     */
    public void start(){
        styleSelect.initialize();
        styleSelect.layout();
        JPanel panel0 = styleSelect.getPanel();
        frame.add(panel0);
        frame.pack();
        frame.setVisible(true);
    }
    
    public GifMaker getParent(){
        return parent;
    }
    
    public StyleSelectGUI getStyleSelectGUI(){
        return styleSelect;
    }
    
    public InputSceneGUI getInputSceneGUI(){
        return inputScene;
    }
    
    public PreviewGUI getPreviewGUI(){
        return preview;
    }
    
    public SaveGUI getSaveGUI(){
        return save;
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
