package gifmakergui;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InputSceneGUI implements GUIClass{
    
    GifMakerGUI parent;
    JFrame frame;
    JPanel panel;
    JScrollPane scrollPane;
    JLabel instruction;
    JComboBox<String> animations;
    String[] animationNames;
    JRadioButton left;
    JRadioButton right;
    JColorChooser colorChooser;
    JTextArea input;
    
    public InputSceneGUI(GifMakerGUI parent, JFrame frame, JPanel panel){
        this.parent = parent;
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void layout() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public JPanel getPanel() {
        // TODO Auto-generated method stub
        return this.panel;
    }

}
