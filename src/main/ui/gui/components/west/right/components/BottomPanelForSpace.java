package ui.gui.components.west.right.components;

import ui.gui.components.PanelSuper;

import javax.swing.*;

import java.awt.*;

import static ui.gui.components.MainFrame.getThesaurus;

// essentially white (black) space at the bottom of its super panel
public class BottomPanelForSpace extends PanelSuper {

    JTextArea space;

    // MODIFIES: this
    // EFFECTS: constructor
    public BottomPanelForSpace() {
        super();
        space = new JTextArea(10, 30);
        space.setBackground(Color.BLACK);
        space.setEnabled(false);

        panel.add(space, BorderLayout.SOUTH);
    }

    // EFFECTS: nothing
    @Override
    public void initialise() {

    }
}
