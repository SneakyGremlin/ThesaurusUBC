package ui.gui.components.east.north;

import model.LexicalConstruct;

import javax.swing.*;
import java.awt.*;

// sub-panel for east panel that displays definition of construct
public class NorthPanelForDefinition {

    JPanel panel;
    static JTextArea definition;
    JTextArea relatedToTheseConstructs;

    // MODIFIES: this
    // EFFECTS: returns an instance of NorthPanel
    public NorthPanelForDefinition() {
        panel = new JPanel(new BorderLayout());
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds components to panel
    public void initialise() {
        panel.setBackground(Color.black);
        definition = new JTextArea(5, 50);
        definition.setBackground(Color.black);
        definition.setForeground(Color.white);
        definition.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        definition.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ definition<<<"
                + "\n" + "NOTE: THIS IS UNIMPLEMENTED FUNCTIONALITY.");
        definition.setLineWrap(true);
        definition.setWrapStyleWord(true);
        definition.setEditable(false);
        relatedToTheseConstructs = new JTextArea(20, 50);
        relatedToTheseConstructs.setWrapStyleWord(true);
        relatedToTheseConstructs.setLineWrap(true);
        relatedToTheseConstructs.setEditable(false);
        relatedToTheseConstructs.setBackground(Color.black);
        //panel.add(new JLabel("Definition: "), BorderLayout.NORTH);
        panel.add(definition, BorderLayout.CENTER);
        panel.add(relatedToTheseConstructs, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: updates definition TextArea
    public static void updateEastMostTop(LexicalConstruct c) {
        definition.setText("");
    }

    // MODIFIES: this
    // EFFECTS: resets definition TextArea
    public static void resetEastMostTop() {
        definition.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ definition<<<"
                + "\n" + "NOTE: THIS IS UNIMPLEMENTED FUNCTIONALITY.");
    }

    // EFFECTS : return panel
    public JPanel getPanel() {
        return panel;
    }
}
