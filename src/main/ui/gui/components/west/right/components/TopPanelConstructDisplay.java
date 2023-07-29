package ui.gui.components.west.right.components;

import exceptions.NotPresentInThesaurus;
import model.CompoundWord;
import model.LexicalConstruct;
import model.MultiLexemeLexicalConstructs;
import model.SingularWord;
import ui.gui.components.PanelSuper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.gui.components.east.east.BottomRightPanelForMorphs.resetEastMostBottomRight;
import static ui.gui.components.east.east.BottomRightPanelForMorphs.updateEastMostBottomRight;
import static ui.gui.components.east.north.NorthPanelForDefinition.resetEastMostTop;
import static ui.gui.components.east.north.NorthPanelForDefinition.updateEastMostTop;
import static ui.gui.components.east.west.BottomLeftPanelForRelated.resetEastMostBottomLeft;
import static ui.gui.components.east.west.BottomLeftPanelForRelated.updateEastMostBottomLeft;
import static ui.gui.components.west.left.components.BottomPanelForAnimation.updateImage;

// Top panel in the east side of the west panel of the frame
public class TopPanelConstructDisplay extends PanelSuper {

    // JList listForConstructs;
    // DefaultListModel constructContainer;
    // ListSelectionDataListenerForTopUUU listener;
    // DefaultListCellRenderer renderer;
    // boolean toInitialise = true;

    CompoundWord proto = new CompoundWord("proto");

    JTextArea forConstructsDisplay;
    JScrollPane scroller;
    JButton delete;
    JButton view;

    static JButton refresh;
    static JTextField toViewOrDelete;

    JPanel forButtons;

    // MODIFIES: this
    // EFFECTS: constructor
    public TopPanelConstructDisplay() {
        super();
        forButtons = new JPanel(new BorderLayout());
        setUpRefresh();
        setUpView();
        setUpDelete();
        refresh.setMargin(new Insets(0,0,0,0));
        scroller = new JScrollPane();
        forConstructsDisplay = new JTextArea(45, 30);
        forConstructsDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        forConstructsDisplay.setBackground(Color.BLACK);
        forConstructsDisplay.setForeground(Color.WHITE);
        forConstructsDisplay.setEditable(false);
        scroller.setViewportView(forConstructsDisplay);

        setUpToViewOrDelete();

        //panel.add(forConstructsDisplay, BorderLayout.NORTH);
        panel.add(scroller, BorderLayout.NORTH);
        panel.add(toViewOrDelete, BorderLayout.CENTER);

        forButtons.add(refresh, BorderLayout.EAST);
        forButtons.add(delete, BorderLayout.WEST);
        forButtons.add(view, BorderLayout.CENTER);
        panel.add(forButtons, BorderLayout.SOUTH);

    }

    // MODIFIES: this
    // EFFECTS: sets up JTextField.
    private void setUpToViewOrDelete() {
        toViewOrDelete = new JTextField(10);
        // toViewOrDelete.addActionListener();
    }

    // !!! add an action to Submit button that clears value textField here.
    // !!! submit refreshes the right boxes as well

    // MODIFIES: this, thesaurus
    // EFFECTS: Sets up a delete button. Delete Button has an action listener that deletes the value
    private void setUpDelete() {
        delete = new JButton("Delete Entry");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = proto.convertToStorageForm(toViewOrDelete.getText());
                if (thesaurus.checkIfThesaurusContainsInput(value)) {
                    thesaurus.delete(proto.convertToStorageForm(toViewOrDelete.getText()));
                    toViewOrDelete.setText("");
                    getRefresh().doClick();
                    /*resetEastMostBottomRight();
                    resetEastMostBottomLeft();
                    resetEastMostTop();*/
                } else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "Entry Not Present; Cannot Delete.");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets up the view Button and adds action listener thereto
    private void setUpView() {
        view = new JButton("View Information");
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LexicalConstruct construct;
                try {
                    construct = thesaurus.returnLexicalObject(
                            proto.convertToStorageForm(toViewOrDelete.getText()));
                    updateEastMostTop(construct);
                    try {
                        SingularWord singularWord = (SingularWord) construct;
                        updateEastMostBottomRight(singularWord);
                    } catch (Exception exc) {
                        // do nothing // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    }
                    updateEastMostBottomLeft(construct);
                } catch (NotPresentInThesaurus exc) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "Entry Not Present; Cannot Show.");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Sets up an action listener for refresh
    private void setUpRefresh() {
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCDisplay();
                resetEastMostBottomRight();
                resetEastMostBottomLeft();
                resetEastMostTop();
                updateImage();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: updates the contents of forConstructsDisplay
    public void updateCDisplay() {
        forConstructsDisplay.setText(null);
        for (String lc: thesaurus.getEntriesAsStrings()) {
            forConstructsDisplay.append(typeAppend(lc) + " " + lc + "\n"); // !!! Prepend type as well
        }
    }

    // EFFECTS: returns a string representing the type of a Lexical Construct
    public String typeAppend(String lcs) {
        if (thesaurus.isMultiLexeme(lcs)) {
            if (((MultiLexemeLexicalConstructs) (thesaurus.returnLexicalObject(lcs))).getCompoundOrIdiom()
                    .equals("I")) {
                return "<<<I>>>";
            } else {
                return "<<<C>>>";
            }
        } else {
            return "<<<S>>>";
        }
    }

    // EFFECTS: returns refresh
    public static JButton getRefresh() {
        return refresh;
    }

    // EFFECTS: returns toViewOrDelete
    public static JTextField getToViewOrDelete() {
        return toViewOrDelete;
    }

    /*listener = new ListSelectionDataListenerForTopUUU(this);
        renderer = new DefaultListCellRenderer();
        constructContainer = new DefaultListModel<>();
        for (String lc: thesaurus.getEntriesAsStrings()) {
            constructContainer.addElement(lc);
        }
        constructContainer.addListDataListener(listener);
        constructContainer.addElement("Hello");
        // initialiseDefaultListModel();
        listForConstructs = new JList(constructContainer);
        listForConstructs.setSelectionMode(SINGLE_SELECTION);
        listForConstructs.addListSelectionListener(listener);
        listForConstructs.setLayoutOrientation(JList.VERTICAL);
        listForConstructs.setVisibleRowCount(60);
        listForConstructs.setCellRenderer(renderer);


        panel.add(listForConstructs, BorderLayout.NORTH);*/

    /*// MODIFIES: this
    // EFFECTS: Adds values of thesaurus to the constructContainer
    public void initialiseDefaultListModel() {
        if (toInitialise) {
            constructContainer.addElement("");
        }
        if (toInitialise) {
            constructContainer.remove(0);
            toInitialise = false;
        }

    }*/

    // EFFECTS: nothing
    @Override
    public void initialise() {

    }
}

// static methods must reference static classes
