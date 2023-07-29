package ui.gui.components;

import model.Thesaurus;

import javax.swing.*;
import java.awt.*;

import static ui.gui.components.MainFrame.getThesaurus;

// abstract class to mitigate against code repetition; need refactoring at present since was late design decision
public abstract class PanelSuper {

    protected JPanel panel;
    protected Thesaurus thesaurus;

    // MODIFIES: this
    // EFFECTS: constructor
    public PanelSuper() {
        panel = new JPanel(new BorderLayout());
        thesaurus = getThesaurus();
    }

    // MODIFIES: this
    // EFFECTS: adds components to panel
    public abstract void initialise();

    // EFFECTS: returns panel
    public JPanel getPanel() {
        return panel;
    }
}

// EFFECTS: returns a what!!!! for super
