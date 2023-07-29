package ui.gui.components.west.right;

import ui.gui.components.PanelSuper;
import ui.gui.components.west.right.components.BottomPanelForSpace;
import ui.gui.components.west.right.components.TopPanelConstructDisplay;

import java.awt.*;

import static ui.gui.components.MainFrame.getThesaurus;

// right panel of the left superpanel
public class WestsubRightPanel extends PanelSuper {

    TopPanelConstructDisplay topPanel;
    BottomPanelForSpace bottomPanel;

    // MODIFIES: this
    // EFFECTS: constructor
    public WestsubRightPanel() {
        super();
        topPanel = new TopPanelConstructDisplay();
        bottomPanel = new BottomPanelForSpace();
        initialise();

        panel.add(topPanel.getPanel(), BorderLayout.NORTH);
        panel.add(bottomPanel.getPanel(), BorderLayout.SOUTH);
    }

    // EFFECTS: nothing
    @Override
    public void initialise() {

    }
}
