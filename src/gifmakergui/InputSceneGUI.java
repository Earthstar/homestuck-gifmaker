package gifmakergui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import gifmaker.GifMaker;
import gifmaker.TestStyle;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class InputSceneGUI implements GUIClass{
    
    GifMaker gifMaker;
    GifMakerGUI parent;
    JFrame frame;
    JPanel panel;
    JScrollPane scrollPane;
    //Holds all the scene rows
    JPanel scrollPanePanel;
    List<JPanel> rowPanelList;
    JLabel instruction;
    String[] animationNames;
    //Since we want to repeat this many times, make list?
    List<JComboBox<String>> animationsList;
    List<JRadioButton> leftButtonList;
    List<JRadioButton> rightButtonList;
    List<JColorChooser> colorChooserList;
    List<JTextArea> inputTextAreaList;
    JButton more;
    JButton back;
    JButton next;
    
    
    public InputSceneGUI(GifMakerGUI parent, JFrame frame, JPanel panel){
        this.parent = parent;
        this.frame = frame;
        this.panel = panel;
        gifMaker = parent.getParent();
    }

    /**
     * Obtain default values for animationNames from parent
     */
    public void initialize(){
        
        scrollPanePanel = new JPanel();
        scrollPanePanel.setBackground(Color.red);
        //Use BoxLayout to make things stack vertically
        scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.Y_AXIS));
        instruction = new JLabel("Specify scenes. Click more button for more scenes.");
        animationNames = gifMaker.getAnimationNames();
        rowPanelList = new ArrayList<JPanel>();
        animationsList = new ArrayList<JComboBox<String>>();
        leftButtonList = new ArrayList<JRadioButton>();
        rightButtonList = new ArrayList<JRadioButton>();
        //TODO colorChooser is really big. Make popout? Or textbox with 
        //hex color?
        colorChooserList = new ArrayList<JColorChooser>();
        inputTextAreaList = new ArrayList<JTextArea>();
        addNewRowElementsToList();
        
        scrollPane = new JScrollPane();
        
        more = new JButton("More");
        back = new JButton("Back");
        next = new JButton("Next");
        
    }
    
    /**
     * Adds a new set of swing elements to lists.
     */
    public void addNewRowElementsToList(){
        animationsList.add(new JComboBox<String>(animationNames));
        JRadioButton left = new JRadioButton("left");
        JRadioButton right = new JRadioButton("right");
        leftButtonList.add(left);
        rightButtonList.add(right);
        //Add buttons to the same buttonGroup. Don't need to store?
        ButtonGroup group = new ButtonGroup();
        group.add(left);
        group.add(right);
        colorChooserList.add(new JColorChooser());
        inputTextAreaList.add(new JTextArea());
        JPanel p = new JPanel();
        p.setBackground(Color.BLUE); //debug
        GroupLayout layout = new GroupLayout(p);
        p.setLayout(new GroupLayout(p));
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        rowPanelList.add(p);
    }
    
    @Override
    public void layout() {
        //Lay out each panel in rowPanelList first
        //Only need to lay out last panel? TODO
        for (int i = 0; i < rowPanelList.size(); i++){
            JPanel rowPanel = rowPanelList.get(i);
            GroupLayout layout = (GroupLayout) rowPanel.getLayout();
            JComboBox<String> animations = animationsList.get(i);
            JColorChooser colorChooser = colorChooserList.get(i);
            JRadioButton left = leftButtonList.get(i);
            JRadioButton right = rightButtonList.get(i);
            JTextArea text = inputTextAreaList.get(i);
            text.setPreferredSize(new Dimension(100, 100));
            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(animations)
                            .addComponent(colorChooser)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(left)
                                    .addComponent(right))
                            )
                    .addComponent(text)
            );
            layout.setVerticalGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(animations)
                            .addComponent(colorChooser)
                            .addGroup(layout.createParallelGroup()
                                    .addComponent(left)
                                    .addComponent(right)
                                    )
                            )
                    .addComponent(text)
            );
            rowPanel.repaint();
        }
        
        //Add all subpanels to larger panel. This doesn't work?
        for (JPanel p: rowPanelList){
            System.out.println(p);
            System.out.println("adding panel");
            scrollPanePanel.add(p);
        }
        scrollPanePanel.add(more);
        scrollPanePanel.repaint();
        
        scrollPane.add(scrollPanePanel);
        scrollPane.setBackground(Color.pink);
        scrollPane.repaint();
        //Layout whole thing TODO change later
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setBackground(Color.GREEN);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(instruction)
                .addComponent(scrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addComponent(next)
                        )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(instruction)
                .addComponent(scrollPane)
                .addGroup(layout.createParallelGroup()
                        .addComponent(back)
                        .addComponent(next)
                        )
        );
        panel.repaint();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
    
    public void displayRowPanel(JFrame f){
        
        f.add(rowPanelList.get(0));
        f.pack();
        f.setVisible(true);
    }
    
    public void displayScrollPanePanel(JFrame f){
        f.add(scrollPanePanel);
        f.pack();
        f.setVisible(true);
    }
    
    public List<JPanel> getRowPanelList(){
        return rowPanelList;
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                GifMaker g = new GifMaker();
                g.setStyle(new TestStyle());
                GifMakerGUI gui = new GifMakerGUI(g);
                JFrame f = new JFrame("rowpanel");
                JPanel p = new JPanel();
                GroupLayout layout = new GroupLayout(p);
                p.setLayout(layout);
                InputSceneGUI i = new InputSceneGUI(gui, f, p);
                i.initialize();
                i.layout();
                i.displayRowPanel(f);
                JFrame f2 = new JFrame("scrollpanel");
                i.displayScrollPanePanel(f2);
            }
        });
    }

}
