package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

// Saves the current state of the program i.e. current LexicalConstructs to a data file.
public class SaveValues {

    // NOTE: in the "REQUIRES" clause, the formatting refers to the formatting inside this application, not JSON's
    // NOTE: incidence counters are not stored on account of indexing functionality not being implemented yet.

    public static final int INDENT_VALUE = 5;

    private Thesaurus thesaurus;

    // the user should call save; the EventLog values should be stored, process is run, then the event log is cleared;
    // the events are readded
    private ArrayList<Event> eventContainer;

    // CONSTRUCTOR
    // EFFECTS: sets field this.thesaurus to thesaurus
    public SaveValues(Thesaurus thesaurus) {
        this.thesaurus = thesaurus;
    }

    // MODIFIES: this, EventLog
    // EFFECTS: converts a Thesaurus object to a properly formatted JSON object and stores it inside LOCATION
    //          TO ADD !!! add backup option as per specification below
    public void saveThesaurus(String location) throws FileNotFoundException {
        initialiseEventContainer();
        PrintWriter writer = new PrintWriter(location);
        JSONArray thesaurusAsJasonArray = new JSONArray();
        JSONObject placeHolder;
        for (LexicalConstruct construct : thesaurus.getEntries()) {
            if (thesaurus.isMultiLexeme(construct.getValue())) {
                placeHolder = toJsonMulti((MultiLexemeLexicalConstructs) construct);
            } else { // is SingularWord
                placeHolder = toJsonSingular((SingularWord) construct);
            }
            thesaurusAsJasonArray.put(placeHolder);
        }
        JSONObject toStore = new JSONObject();
        toStore.put("thesaurus", thesaurusAsJasonArray);
        writer.print(toStore.toString(INDENT_VALUE));
        writer.close(); // must close every time; children imitate perfectly for a reason
        // for backup functionality introduce a new function here that saves the file but appends backup and then date
        // to the file for saving

        fixEventLog();
    }

    // MODIFIES: this
    // EFFECTS: stores a copy of Events in the EventLog prior to call to Save
    public void initialiseEventContainer() {
        eventContainer = new ArrayList<>();
        /*for (Event e : EventLog.getInstance()) {
            eventContainer.add(e);
        }*/
    }

    // MODIFIES: EventLog
    // EFFECTS: clears the event log, and resets it to its state prior to this method call
    public void fixEventLog() {
        /*for (Iterator<Event> it = eventContainer; it.hasNext(); ) {
            Event e = it.next();
            EventLog.getInstance().logEvent(e);
        }*/
        EventLog.getInstance().clear();
        /*for (Event e : eventContainer) {
            EventLog.getInstance().logEvent(e);
        }*/
    }


    // REQUIRES: proper formatting of value and fields.
    // EFFECTS: returns SingularWord object as a JSONObject
    public JSONObject toJsonSingular(SingularWord singularWord) {
        JSONObject toReturn = new JSONObject();
        toReturn.put("value", singularWord.getValue());
        toReturn.put("morphologicalForms", arrayListToJsonArray(singularWord.getMorphologicalForms()));
        toReturn.put("relatedLexicalConstructs", arrayListToJsonArray(singularWord.getRelatedLexicalConstructs()));
        toReturn.put("type", "S"); // type is only for storage, is one of S,M.
        return toReturn;
    }

    // REQUIRES: proper formatting of value and fields.
    // EFFECTS: returns subtypes of MultiLexemeLexicalConstructs as a JSONObject
    public JSONObject toJsonMulti(MultiLexemeLexicalConstructs instanceOfMulti) {
        JSONObject toReturn = new JSONObject();
        toReturn.put("value", instanceOfMulti.getValue());
        toReturn.put("compoundOrIdiom", instanceOfMulti.getCompoundOrIdiom());
        toReturn.put("relatedLexicalConstructs", arrayListToJsonArray(instanceOfMulti.getRelatedLexicalConstructs()));
        toReturn.put("type", "M"); // type is only for storage, is one of S,M.
        return toReturn;
    }

    // REQUIRES: proper formatting of the fields in listOfRelatedOrMorphs
    // EFFECTS: puts each member of the array list into a json array.
    public JSONArray arrayListToJsonArray(ArrayList<String> listOfRelatedOrMorphs) {
        JSONArray toReturn = new JSONArray();
        for (String element : listOfRelatedOrMorphs) {
            toReturn.put(element);
        }
        return toReturn;
    }
}

// does a constructor have a MODIFIES?

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HIGH-PRIORITY: incidence counters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

// I don't put the value before the curly brackets cuz I don't know how to :( !!!

// PrintWriter writer = new PrintWriter(new File("./data/myFile.json"))

// modifier static not allowed here !!!

// scoping rules !!!