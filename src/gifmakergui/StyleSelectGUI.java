package gifmakergui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import gifmaker.GifMaker;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Style;

//I think this will take ~30 min to figure out
//Lol nope, 2.4 hours

public class StyleSelectGUI implements GUIClass{
    
    final GifMakerGUI parent;
    JFrame frame;
    JPanel panel;
    JLabel instruction;
    JLabel error;
    JComboBox<String> styles;
    JButton next;
    
    /**
     * 
     * @param parent
     * @param frame has grouplayout
     */
    public StyleSelectGUI(final GifMakerGUI parent, final JFrame frame, final JPanel panel){
        this.parent = parent;
        this.frame = frame;
        this.panel = panel;
        initialize();
        layout();
    }
    
    public void initialize(){
        instruction = new JLabel();
        instruction.setText("Choose style:");
        
        error = new JLabel();
        error.setVisible(false);
        
        //TODO config file
        String[] styleList = {"Test", "Alterniabound"};
        styles = new JComboBox<String>(styleList);
        styles.setEditable(false);
        //Action listener sets parent's style
        styles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox) e.getSource();
                String styleString = (String) cb.getSelectedItem();
                GifMaker gm = parent.getParent();
                Map<String, gifmaker.Style> styleMap = gm.getStyleMap();
                if (styleMap.containsKey(styleString)){
                    error.setVisible(false);
                    
                } else {
                    error.setText("Can't find style "+styleString);
                    error.setVisible(true);
                    //want to repaint the main frame
                    cb.getParent().getParent().repaint();
                }
            }
        });
        
        //When button pressed, go to next frame
        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //set parent's style to current style TODO error checking?
                String styleString = (String) styles.getSelectedItem();
                GifMaker gm = parent.getParent();
                Map<String, gifmaker.Style> styleMap = gm.getStyleMap();
                gm.setStyle(styleMap.get(styleString));
                //Initialize InputSceneGUI
                InputSceneGUI inputScene = parent.getInputSceneGUI();
                inputScene.initialize();
                inputScene.layout();
                //remove current panel, add next panel to frame
                frame.remove(panel);
                frame.setContentPane(inputScene.getPanel());
                frame.validate();
                frame.repaint();
            }
        });
    }
    
    /**
     * Adds elements to a JPanel
     * @param panel
     */
    public void layout(){
        //Add elements to a JPanel
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addComponent(instruction)
                    .addComponent(error)
                    .addComponent(styles)
                    .addComponent(next)
                    );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(instruction)
                    .addComponent(error)
                    .addComponent(styles)
                    .addComponent(next)
                    );
        panel.repaint();
        
    }
    
    public JPanel getPanel(){
        return panel;
    }

}
