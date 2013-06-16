package gifmakergui;

import javax.swing.JPanel;

public interface GUIClass {
    
    /**
     * Initialize the class from information in parents
     */
    public void initialize();
    
    /**
     * Layout the JPanel
     */
    public void layout();
    
    public JPanel getPanel();

}
