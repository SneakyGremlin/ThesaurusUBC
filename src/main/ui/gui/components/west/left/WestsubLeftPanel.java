package ui.gui.components.west.left;

import ui.gui.components.west.left.components.BottomPanelForAnimation;
import ui.gui.components.west.left.components.TopPanelForSingular;

import javax.swing.*;
import java.awt.*;

// The left panel in the West Panel of the main panel
public class WestsubLeftPanel {
    JPanel panel;
    TopPanelForSingular top;
    BottomPanelForAnimation bottom;

    // MODIFIES: this
    // EFFECTS: Create a new instance of LeftPanel
    public WestsubLeftPanel() {
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds component to panel, initialises sub panels
    private void initialise() {
        panel = new JPanel(new BorderLayout());
        top = new TopPanelForSingular();
        bottom = new BottomPanelForAnimation();

        panel.add(top.getPanel(), BorderLayout.NORTH);
        panel.add(bottom.getPanel(), BorderLayout.CENTER);

    }

    // EFFECTS: returns field panel
    public JPanel getPanel() {
        return panel;
    }

}
