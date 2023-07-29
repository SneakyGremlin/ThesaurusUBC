package ui.gui.components.east.west;

import model.LexicalConstruct;
import ui.gui.components.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// bottom left panel of the east superpanel; is to display related of the entry selected
public class BottomLeftPanelForRelated {
    JPanel panel;
    static JTextArea related;

    // MODIFIES: this
    // EFFECTS: returns an instance of NorthPanel
    public BottomLeftPanelForRelated() {
        panel = new JPanel(new BorderLayout());
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds components to panel
    public void initialise() {
        related = new JTextArea(20,25);
        related.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        related.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ this field<<<");
        related.setEditable(false);
        related.setBackground(Color.black);
        // morphs.setFont(new Font("Sans Serif", ));
        related.setForeground(Color.white);
        for (String s: MainFrame.getThesaurus().getEntriesAsStrings()) {
            related.append(s + "\n");
        }
        // panel.add(new JLabel("Related:"), BorderLayout.NORTH);
        panel.add(related);
    }

    // MODIFIES: this
    // EFFECTS: displays related;
    public static void updateEastMostBottomLeft(LexicalConstruct c) {
        ArrayList<String> toDisplay = c.getRelatedLexicalConstructs();
        related.setText("");
        for (String val: toDisplay) {
            related.append(">>>" + val + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: resets definition TextArea
    public static void resetEastMostBottomLeft() {
        related.setText(">>>No Entry Chosen, if this is blank it means the entry was empty @ this field<<<");
    }

    // EFFECTS : return panel
    public JPanel getPanel() {
        return panel;
    }
}
