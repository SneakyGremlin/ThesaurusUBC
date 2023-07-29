package ui.gui.components.west.left.components;

import javax.swing.*;
import java.awt.*;

// Panel which allows you to add entries to the thesaurus
public class TopPanelForSingular {

    private JPanel panel;

    private JTextField fieldForConstruct;

    private JPanel panelForRadioAndLabelAndField;
    private JPanel panelForLabelAndField;
    private ButtonGroup bg;
    private JPanel panelForRadio;

    private JPanel panelForSubmitAndRelatedAndMorphs;
    private static JButton submit;
    private JPanel panelForRelatedAndMorphs;
    private JPanel panelForRelated;
    private JToggleButton relatedTextToggler;
    private JTextArea relatedText;
    private JPanel panelForMorphs;
    private JToggleButton morphsTextToggler;
    private JTextArea morphsText;

    private ActionListenerForSingular actionListener;

    private JLabel labelAdd;

    private JRadioButton singularWord;
    private JRadioButton idiom;
    private JRadioButton compoundWord;

    //... !!!

    // MODIFIES: this
    // EFFECTS: creates an instance of TopPanelForSingular
    public TopPanelForSingular() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.gray);
        addComponents();
    }

    // MODIFIES this
    // EFFECTS: adds components to the JPanel
    public void addComponents() {
        panelForRadioAndLabelAndField = new JPanel(new BorderLayout());
        panelForLabelAndField = new JPanel(new BorderLayout());
        labelAdd = new JLabel("Add: ");
        panelForLabelAndField.add(labelAdd, BorderLayout.NORTH);
        fieldForConstruct = new JTextField();
        //fieldForConstruct.setToolTipText("Warning: this entry is pre-present.\nSubmitting means you overwrite the "
        //        + "entry");

        panelForLabelAndField.add(fieldForConstruct, BorderLayout.SOUTH);
        panelForRadioAndLabelAndField.add(panelForLabelAndField, BorderLayout.NORTH);

        addRadioButtons();

        panel.add(panelForRadioAndLabelAndField, BorderLayout.NORTH);

        addCheckBoxesWithPanelsAndSubmit();

        // action listener
        actionListener = new ActionListenerForSingular(relatedText, morphsText, panelForRadio, submit,
                fieldForConstruct, (JCheckBox) morphsTextToggler, bg);
        submit.addActionListener(actionListener);
        fieldForConstruct.addActionListener(actionListener);
        fieldForConstruct.addKeyListener(actionListener);
        singularWord.addActionListener(actionListener);
        idiom.addActionListener(actionListener);
        compoundWord.addActionListener(actionListener);

    }

    // MODIFIES: this
    // EFFECTS: adds to panel, a super panel with two subpanels, each of which has a textbox at the bottom and the
    //          checkbox at the top
    private void addCheckBoxesWithPanelsAndSubmit() {
        panelForSubmitAndRelatedAndMorphs = new JPanel(new BorderLayout(1, 1));
        panelForRelatedAndMorphs = new JPanel(new BorderLayout(1, 1));
        panelForRelated = new JPanel(new BorderLayout(3, 3));
        panelForMorphs = new JPanel(new BorderLayout(3, 3));
        morphsTextToggler = new JCheckBox("Add morphs; Use ### to separate: ");
        morphsTextToggler.addKeyListener(actionListener);
        relatedTextToggler = new JCheckBox("Add related; Use ### to separate: ");
        morphsText = new JTextArea(16, 20);
        relatedText = new JTextArea(16,20);
        morphsText.setLineWrap(true);
        morphsText.setWrapStyleWord(true);
        relatedText.setLineWrap(true);
        relatedText.setWrapStyleWord(true); // why does the line go out of the screen

        panelForRelated.add(relatedTextToggler, BorderLayout.NORTH);
        panelForRelated.add(relatedText, BorderLayout.SOUTH);

        panelForMorphs.add(morphsTextToggler, BorderLayout.NORTH);
        panelForMorphs.add(morphsText, BorderLayout.SOUTH);
        panelForMorphs.addKeyListener(actionListener);

        panelForRelatedAndMorphs.add(panelForRelated, BorderLayout.WEST);
        panelForRelatedAndMorphs.add(panelForMorphs, BorderLayout.EAST);
        panelForSubmitAndRelatedAndMorphs.add(panelForRelatedAndMorphs, BorderLayout.NORTH);

        addSubmitButton();

        panel.add(panelForSubmitAndRelatedAndMorphs, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds a submit Button to the bottom of this panel
    private void addSubmitButton() {
        submit = new JButton("Click to Submit");
        // submit.setActionCommand();

        // al for right most and right frame for updating.

        panelForSubmitAndRelatedAndMorphs.add(submit, BorderLayout.SOUTH);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds a radio button group to this.panel
    private void addRadioButtons() {
        panelForRadio = new JPanel();
        bg = new ButtonGroup();
        singularWord = new JRadioButton("Singular Word");
        singularWord.setName("singularWord");
        idiom = new JRadioButton("Idiomatic Expression");
        idiom.setName("idiom");
        compoundWord = new JRadioButton("Compound Word");
        compoundWord.setName("compoundWord");
        singularWord.setEnabled(false);
        compoundWord.setEnabled(false);
        idiom.setEnabled(false);

        bg.add(singularWord);
        bg.add(idiom);
        bg.add(compoundWord);
        panelForRadio.add(singularWord);
        panelForRadio.add(idiom);
        panelForRadio.add(compoundWord);

        // !!! actions!!!
        panelForRadioAndLabelAndField.add(panelForRadio, BorderLayout.SOUTH);
    }


    // EFFECTS: returns panel field
    public JPanel getPanel() {
        return panel;
    }

    // EFFECTS: return Submit Button
    public static JButton getSubmit() {
        return submit;
    }
}

// new something () {}.. what is this !!!

// b1.setVerticalTextPosition
//     b1.setVerticalTextPosition(AbstractButton.CENTER); LEADING

// adhere to styles
// inintilaise new
// preceding shit
// documentation
// parsing
// seems a bit contrived

// >>> if the actual type is different from the apparent type need to cast for actual type parm accepting