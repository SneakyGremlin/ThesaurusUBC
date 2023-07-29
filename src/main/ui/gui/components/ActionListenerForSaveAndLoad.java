package ui.gui.components;

import model.Event;
import model.EventLog;
import model.Thesaurus;
import persistence.LoadValues;
import persistence.SaveValues;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static persistence.LocationParameters.LOAD_LOCATION;
import static ui.gui.components.west.right.components.TopPanelConstructDisplay.getRefresh;

// action listener for Save and Load buttons
public class ActionListenerForSaveAndLoad implements ActionListener {

    private LoadValues loader;
    private SaveValues saver;
    private Thesaurus thesaurus;
    private JButton loadButton;
    private JButton saveButton;

    private Thesaurus precautionaryThesaurus = new Thesaurus();

    // MODIFIES: this
    // EFFECTS: constructor
    public ActionListenerForSaveAndLoad(Thesaurus thesaurus, JButton loadButton, JButton saveButton) {
        this.thesaurus = thesaurus;
        loader = new LoadValues(thesaurus);
        saver = new SaveValues(thesaurus);
        this.loadButton = loadButton;
        this.saveButton = saveButton;
    }

    // MODIFIES: thesaurus
    // EFFECTS: if load button is clicked load else if save button is clicked then save
    @Override
    public void actionPerformed(ActionEvent e) {
        //saver.initialiseEventContainer();

        if (e.getSource().equals(loadButton)) {
            actionForLoadButton();
        } else if (e.getSource().equals(saveButton)) {
            actionForSaveButton();
        }

        //saver.fixEventLog();
    }

    // EFFECTS: decomposition of actionPerformed; contains part of saveButton
    private void actionForSaveButton() {
        /*ArrayList<Event> eventContainer = new ArrayList<>();
        for (Event e : EventLog.getInstance()) {
            eventContainer.add(e);
        }*/
        try {
            LoadValues miniLoader = new LoadValues(precautionaryThesaurus);
            miniLoader.beginLoading(LOAD_LOCATION);
            collateThesauruses();
            saver.saveThesaurus(LOAD_LOCATION);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        EventLog.getInstance().clear();
        /*for (Event e : eventContainer) {
            EventLog.getInstance().logEvent(e);
        }*/
    }

    // EFFECTS: decomposition of actionPerformed; contains part of loadButton
    private void actionForLoadButton() {
        try {
            if (thesaurus.getEntriesAsStrings().isEmpty()) {
                loader.beginLoading(LOAD_LOCATION);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"can load only at beginning.");
            }
            getRefresh().doClick();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // MODIFIES: this, thesaurus
    // EFFECTS: combines the entries of the old thesaurus and the new thesaurus new entries take precedence.
    //          first a for loop over new thesaurus to find values that are already present; delete these ones
    //          then a for loop for all the values in the new thesaurus; add these to the old theasurus
    private void collateThesauruses() {
        for (String newEntry: precautionaryThesaurus.getEntriesAsStrings()) {
            if (thesaurus.checkIfThesaurusContainsInput(newEntry)) {
                thesaurus.delete(newEntry);
            }
            thesaurus.addLexicalConstruct(precautionaryThesaurus.returnLexicalObject(newEntry));
        }
    }
}

// there are two thesaurus objects
// can't have repeated values
// will override entries with new ones. <<<<<<< Design decision

// salubrious
// surround with
