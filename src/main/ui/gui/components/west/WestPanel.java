package ui.gui.components.west;

import ui.gui.components.west.left.WestsubLeftPanel;
import ui.gui.components.west.right.WestsubRightPanel;

import javax.swing.*;
import java.awt.*;

// the west panel in mainframe panel
public class WestPanel {
    JPanel panel;
    WestsubLeftPanel west;
    WestsubRightPanel east;

    // MODIFIES: this
    // EFFECTS: Create a new instance of LeftPanel
    public WestPanel() {
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds component to panel, initialises sub panels
    private void initialise() {
        panel = new JPanel(new BorderLayout(5, 5));
        west = new WestsubLeftPanel();
        east = new WestsubRightPanel();

        panel.add(west.getPanel(), BorderLayout.WEST);
        panel.add(east.getPanel(), BorderLayout.EAST);

    }

    // EFFECTS: returns field panel
    public JPanel getPanel() {
        return panel;
    }

}
