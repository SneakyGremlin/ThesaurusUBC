package ui.gui.components.east;

import ui.gui.components.east.east.BottomRightPanelForMorphs;
import ui.gui.components.east.north.NorthPanelForDefinition;
import ui.gui.components.east.west.BottomLeftPanelForRelated;

import javax.swing.*;
import java.awt.*;

// the east panel of the mainframe
public class EastPanel {
    JPanel panel;
    NorthPanelForDefinition north;
    BottomRightPanelForMorphs right;
    BottomLeftPanelForRelated left;

    // MODIFIES: this
    // EFFECTS: constructs a new instance of EastPanel
    public EastPanel() {
        panel = new JPanel(new BorderLayout(1, 1));
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds sub-components to this
    private void initialise() {
        north = new NorthPanelForDefinition();
        right = new BottomRightPanelForMorphs();
        left = new BottomLeftPanelForRelated();

        panel.setBackground(Color.gray);

        panel.add(north.getPanel(), BorderLayout.NORTH);
        panel.add(right.getPanel(), BorderLayout.EAST);
        panel.add(left.getPanel(), BorderLayout.WEST);
        //panel.add(new JButton("Hi"));

    }

    // EFFECTS : return panel
    public JPanel getPanel() {
        return panel;
    }


}
