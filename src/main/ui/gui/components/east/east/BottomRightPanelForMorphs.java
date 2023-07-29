package ui.gui.components.east.east;

import model.SingularWord;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.components.MainFrame.getThesaurus;

// Bottom right panel of east sub panel; is for displaying morphs
public class BottomRightPanelForMorphs {

    JPanel panel;
    static JTextArea morphs;

    // MODIFIES: this
    // EFFECTS: returns an instance of NorthPanel
    public BottomRightPanelForMorphs() {
        panel = new JPanel(new BorderLayout());
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds components to panel
    public void initialise() {
        morphs = new JTextArea(20, 25);
        morphs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        morphs.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ this field<<<");
        morphs.setEditable(false);
        morphs.setBackground(Color.black);
        // morphs.setFont(new Font("Sans Serif", ));
        morphs.setForeground(Color.white);
        for (String s : getThesaurus().getEntriesAsStrings()) {
            morphs.append(s + "\n");
        }
        panel.add(morphs);
    }

    // REQUIRES: Check has been made to ensure proper type is inputted
    // MODIFIES: this
    // EFFECTS: displays morphs.
    public static void updateEastMostBottomRight(SingularWord c) {
        ArrayList<String> toDisplay = c.getMorphologicalForms();
        morphs.setText("");
        for (String val : toDisplay) {
            morphs.append(">>>" + val + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: resets definition TextArea
    public static void resetEastMostBottomRight() {
        morphs.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ this field<<<");
    }


    // EFFECTS : return panel
    public JPanel getPanel() {
        return panel;
    }
}
