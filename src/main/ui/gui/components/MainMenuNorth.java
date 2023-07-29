package ui.gui.components;

import javax.swing.*;

import static persistence.LocationParameters.LOAD_LOCATION;
import static persistence.LocationParameters.SAVE_LOCATION;
import static ui.gui.components.MainFrame.getThesaurus;

// the class for the Menu with buttons for save load and lexical deconstructor
public class MainMenuNorth {

    private JMenuBar menuBar;
    private JMenuItem menuItem;
    private JButton loadButton;
    private JButton saveButton;
    private JButton ldeconButton;
    private ActionListenerForSaveAndLoad actionListenerForSaveAndLoad;
    private static boolean previousStateLoaded;

    // MODIFIES: this
    // EFFECTS: constructor
    public MainMenuNorth() {
        menuBar = new JMenuBar();
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: initialise the menu
    public void initialise() {
        forButtons();

    }

    // MODIFIES: this
    // EFFECTS: creates the buttons and adds them to the menuBar
    public void forButtons() {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        ldeconButton = new JButton("Lexical Deconstructor");

        loadButton.setToolTipText("Load file from: " + LOAD_LOCATION);
        saveButton.setToolTipText("Save (overwrite) file at" + SAVE_LOCATION);
        ldeconButton.setToolTipText("Enter Lexical Deconstructor");

        actionListenerForSaveAndLoad = new ActionListenerForSaveAndLoad(getThesaurus(), loadButton, saveButton);
        // loadButton.getAction(); !!!

        loadButton.addActionListener(actionListenerForSaveAndLoad);
        saveButton.addActionListener(actionListenerForSaveAndLoad);

        menuBar.add(loadButton);
        menuBar.add(saveButton);
        menuBar.add(ldeconButton);
    }

    // EFFECTS: returns the menuBar
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    // EFFECTS: returns previousStateLoaded
    public static boolean getPreviousStateLoaded() {
        return previousStateLoaded;
    }

}

// !!! why can't i use this in static methods

//ldButton = new JButton((Action) null);
// !!! what does it mean to cast null? where in the hierarchy is null a subtype
//      how would the casting even work? you can't cast a different subtype, only the super if apparent type

// adhere to style
