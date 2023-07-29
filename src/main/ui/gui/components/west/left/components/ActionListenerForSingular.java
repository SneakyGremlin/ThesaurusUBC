package ui.gui.components.west.left.components;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static ui.gui.components.MainFrame.getThesaurus;
import static ui.gui.components.east.east.BottomRightPanelForMorphs.resetEastMostBottomRight;
import static ui.gui.components.east.north.NorthPanelForDefinition.resetEastMostTop;
import static ui.gui.components.east.west.BottomLeftPanelForRelated.resetEastMostBottomLeft;
import static ui.gui.components.west.right.components.TopPanelConstructDisplay.getRefresh;
import static ui.gui.components.west.right.components.TopPanelConstructDisplay.getToViewOrDelete;

// ActionListener for subpanels (textbox and togglebutton) in TopPanelForSingular
public class ActionListenerForSingular implements ActionListener, KeyListener {

    private JTextArea textAreaRelated;
    private JTextArea textAreaMorphs;
    private JPanel panelWithRadio;
    private JRadioButton singular;
    private JRadioButton idiom;
    private JRadioButton compoundWord;
    private JTextField textField;
    private JButton submit;
    private JCheckBox morphsToggler;
    private ButtonGroup bg;

    private boolean messageShown = false;

    private CompoundWord proto = new CompoundWord("proto");


    // MODIFIES: this
    // EFFECTS: constructor
    public ActionListenerForSingular(JTextArea textRelated, JTextArea textMorphs,
                                     JPanel panelWithRadio, JButton submit, JTextField textField,
                                     JCheckBox morphsToggler, ButtonGroup bg) {
        this.textAreaRelated = textRelated;
        this.textAreaMorphs = textMorphs;
        this.panelWithRadio = panelWithRadio;
        this.submit = submit;
        this.textField = textField;
        this.morphsToggler = morphsToggler;
        this.bg = bg;

        for (Component c : panelWithRadio.getComponents()) {
            if (c instanceof JToggleButton) {
                if (((JRadioButton) c).getName().equals("singularWord")) { // alternatively getText() :(
                    this.singular = (JRadioButton) c;
                }
                if (((JRadioButton) c).getName().equals("idiom")) { // alternatively getText() :(
                    this.idiom = (JRadioButton) c;
                }
                if (((JRadioButton) c).getName().equals("compoundWord")) { // alternatively getText() :(
                    this.compoundWord = (JRadioButton) c;
                }
            }
            /*if (c instanceof JTextField) {
                this.textField = (JTextField) c;
            }*/
        }
    }

    // MODIFIES: this, thesaurus
    // EFFECTS: if textfield. get after key stroke // disable radio button and textArea
    //          if textfield already in warning -- takes precedence over above WILL activate above nonetheless
    //          if submit clear all fields
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource().equals(submit) || e.getSource().equals(textField))) {
            if (!(singular.isSelected() || compoundWord.isSelected() || idiom.isSelected())) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Choose the type of Lexical Construct.");
            } else if (getThesaurus().checkIfThesaurusContainsInput(proto.convertToStorageForm(textField.getText()))) {
                if (!(messageShown)) {
                    JOptionPane.showMessageDialog(panelWithRadio,
                            "Caution: Entry is already present. Proceeding will override it.");
                    messageShown = true;
                } else {
                    getThesaurus().delete(proto.convertToStorageForm(textField.getText()));
                    addConstruct();
                    resetComponents();
                    messageShown = false;
                    getRefresh().doClick();
                }
            } else {
                addConstruct();
                resetComponents();
                messageShown = false;
                getRefresh().doClick();
            }
        } //else if (e.getSource().equals)
    }

    // SUPERANNUATED METHOD FOR PREVIOUS ITERATION
    // MODIFIES: this, refresh, viewOrDelete
    // EFFECTS: effectively refreshes the entire frame
    public static void refresher() {
        getRefresh().doClick();
        getToViewOrDelete().setText(null);
        resetEastMostBottomRight();
        resetEastMostBottomLeft();
        resetEastMostTop();
    }


    // MODIFIES: textAreaMorphs, textAreaRelated, textField, this
    // EFFECTS: sets to empty the first three parms, and sets to false messageShown
    public void resetComponents() {
        textAreaMorphs.setText("");
        textAreaRelated.setText("");
        textField.setText("");
        messageShown = false;
    }

    // MODIFIES: this, thesaurus
    // EFFECTS: adds the construct to the thesaurus
    private void addConstruct() { // !!!!!!!!!!! why do i need the break here.
        String constructType = whichButton();
        switch (constructType) {
            case "singular":
                SingularWord singularWord = new SingularWord(proto.convertToStorageForm(textField.getText()));
                format("R", singularWord, "S");
                format("M", singularWord, "S");
                getThesaurus().addLexicalConstruct(singularWord);
                break;
            case "idiom":
                IdiomaticExpression idiom = new IdiomaticExpression(proto.convertToStorageForm(textField.getText()));
                format("R", idiom, "M");
                getThesaurus().addLexicalConstruct(idiom);
                break;
            default:
                CompoundWord compound = new CompoundWord(proto.convertToStorageForm(textField.getText()));
                format("R", compound, "M");
                getThesaurus().addLexicalConstruct(compound);
        }
        // getThesaurus().addLexicalConstruct();
    }

    // MODIFIES: this
    // EFFECTS: stores the data in the text box after formatting
    private void format(String rorl, LexicalConstruct lc, String sorm) {
        JTextArea textArea;
        if (rorl == "R") {
            textArea = textAreaRelated;
        } else { // "M"
            textArea = textAreaMorphs;
        }
        String text = textArea.getText();
        String[] elementsOfTextArea = text.split("###");
        ArrayList<String> elementsToOverwrite = new ArrayList<>();
        for (String s : elementsOfTextArea) {
            elementsToOverwrite.add(proto.convertToStorageForm(s));
        }
        if (sorm.equals("S")) {
            if (rorl.equals("R")) {
                ((SingularWord) lc).overwriteRelatedLexicalConstructs(elementsToOverwrite);
            } else {
                ((SingularWord) lc).overwriteMorphologicalForms(elementsToOverwrite);
            }
        } else {
            ((MultiLexemeLexicalConstructs) lc).overwriteRelatedLexicalConstructs(elementsToOverwrite);
        }
    }

    // EFFECTS: returns the type of button pressed
    private String whichButton() {
        if (singular.isSelected()) {
            return "singular";
        } else if (idiom.isSelected()) {
            return "idiom";
        } else {
            return "compoundWord";
        }
    }

    // MODIFIES: textAreaMorphs, singular,
    // EFFECTS: this is a method to reduce code duplication; it is the action for all ovverides of KeyListener
    //          it disables or enables components depending on the text in textField
    public void keyHandler() {
        if (getThesaurus().checkIfThesaurusContainsInput(proto.convertToStorageForm(textField.getText()))) {
            // send a small alert after a timer
        }
        if (textField.getText().trim().equals("") || textField.getText().equals("Null")) {
            singular.setEnabled(false);
            idiom.setEnabled(false);
            compoundWord.setEnabled(false);
            bg.clearSelection();
        } else if (getThesaurus().isMultiLexeme(proto.convertToStorageForm(textField.getText()))) {
            helperForMLInTextField();
        } else { // if singular word
            helperForSingularInTextField();
        }
    }

    // MODIFIES: this !!!
    // EFFECTS: helper for keyHandler; what to do when the lc is mllc
    private void helperForMLInTextField() {
        textAreaMorphs.setEditable(false);
        textAreaMorphs.setBackground(Color.GRAY);
        if (singular.isSelected()) {
            bg.clearSelection();
        }
        singular.setEnabled(false);
        morphsToggler.setEnabled(false);
        idiom.setEnabled(true);
        compoundWord.setEnabled(true);
    }

    // MODIFIES: this !!!
    // EFFECTS: helper for keyHandler; what to do when the lc is singular
    private void helperForSingularInTextField() {
        // !!!!!!!!!!!!! checkbox and area; no need; just use is enabled on the textbox; I did someteing similar
        if (!(singular.isSelected())) {
            singular.setSelected(true);
        }
        textAreaMorphs.setEditable(true);
        textAreaMorphs.setBackground(Color.WHITE);
        singular.setEnabled(true);
        morphsToggler.setEnabled(true);
        idiom.setEnabled(false);
        compoundWord.setEnabled(false);
    }


    // MODIFIES: textAreaMorphs, singular
    // EFFECTS: enables/disables parms above depending on text in the textField
    @Override
    public void keyTyped(KeyEvent e) {
        keyHandler();
    }

    // MODIFIES: textAreaMorphs, singular
    // EFFECTS: enables/disables parms above depending on text in the textField
    @Override
    public void keyPressed(KeyEvent e) {
        keyHandler();
    }

    // MODIFIES: textAreaMorphs, singular
    // EFFECTS: enables/disables parms above depending on text in the textField
    @Override
    public void keyReleased(KeyEvent e) {
        keyHandler();
    }

    // method for auto disabling use keyListener
    // method for disabling text box depending on preference
}

// .setText()

// adhere to style
// rigorously ^^
// stringent
// ardously !!! not ardour
// ardorously ardently
// punctilious prissy


// !!! do interfaces have constructos? I think not

// !!! converted isMulti to static !!!!!!!!!!!!!!!!!!!!!!!!