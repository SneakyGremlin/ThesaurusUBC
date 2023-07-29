package model;

import exceptions.NotPresentInThesaurus;

import java.util.ArrayList;

// Thesaurus is a depot for LexicalConstructs e.g. singular words et cetera; all members adhere to the Storage format.
public class Thesaurus {

    private ArrayList<String> entriesAsStrings = new ArrayList<>();
    private ArrayList<LexicalConstruct> entries = new ArrayList<>();
    private ArrayList<LexicalConstruct> indexed; // for further optimisation !!! takes into accounts incidences
    private CompoundWord protoLexicalConstruct = new CompoundWord("facilitator"); // !!!

    // CONSTRUCTOR
    // EFFECTS: Instantiates a Thesaurus object
    public Thesaurus() {

    }

    //////////////////////////////////////////// ADDERS/MODIFIERS/DELETER

    // REQUIRES: entry fields must be properly formatted
    // MODIFIES: this
    // EFFECTS: adds a LexicalConstruct to entries, and a corresponding entry to entriesAsStrings
    public void addLexicalConstruct(LexicalConstruct entry) {
        entries.add(entry);
        entriesAsStrings.add(entry.getValue());
        EventLog.getInstance().logEvent(new Event("A new " + entry.getClass().getSimpleName() + ": "
                + entry.getValue() + " "
                + "has been added to the Thesaurus."));
    }

    // REQUIRES: Only called after checkIfThesaurusContainsInput, and after determining type of lexical construct is
    //           SingularWord; proper formatting
    // MODIFIES: this
    // EFFECTS:  adds the entry to the Thesaurus as both a string (entriesAsStrings) and a SingularWord
    //           object (entries)
    //           logs event pertaining to adding lexical construct
    public void addSingularWord(String entry) {
        entry = protoLexicalConstruct.convertToStorageForm(entry);
        entries.add(new CompoundWord(entry)); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! changed CompoundWord to Singular Word
        entriesAsStrings.add(entry);
        // if construct for type
    }

    // REQUIRES: Only called after checkIfThesaurusContainsInput, and after determining type of lexical construct is
    //           CompoundWord; proper formatting
    // MODIFIES: this
    // EFFECTS:  adds the entry to the Thesaurus as both a string (entriesAsStrings) and a SingularWord
    //           object (entries)
    public void addCompoundWord(String entry) {
        entry = protoLexicalConstruct.convertToStorageForm(entry);
        entries.add(new CompoundWord(entry));
        entriesAsStrings.add(entry);
    }

    // REQUIRES: Only called after checkIfThesaurusContainsInput, and after determining type of lexical construct is
    //           IdiomaticExpression; Proper formatting
    // MODIFIES: this
    // EFFECTS:  adds the entry to the Thesaurus as both a string (entriesAsStrings) and a SingularWord
    //           object (entries)
    public void addIdiomaticExpression(String entry) {
        entry = protoLexicalConstruct.convertToStorageForm(entry);
        entries.add(new IdiomaticExpression(entry));
        entriesAsStrings.add(entry);
    }

    // REQUIRES: Object must be present in the thesaurus; queryValue must be properly formatted
    // EFFECTS: Returns the thesaurus object corresponding to queryValue.
    public LexicalConstruct returnLexicalObject(String queryValue) {
        int integerForWhile = 0;
        while (integerForWhile < entries.size()) {
            if (entries.get(integerForWhile).getValue().equals(queryValue)) {
                return entries.get(integerForWhile);
            }
            integerForWhile += 1;
        }
        throw new NotPresentInThesaurus();
    }

    // REQUIRES: The thesaurus must contain the QueryValue; All entries must be in proper format
    // EFFECTS: Converts queryValue to proper format and then,
    //          searches the thesaurus for the query value and returns its related search terms as an array object
    public ArrayList<String> returnRelatedLexicalConstructsAsArrayList(String queryValue) {
        String queryValueProper = protoLexicalConstruct.convertToStorageForm(queryValue);
        ArrayList<String> constructHolder = null;
        for (LexicalConstruct construct : entries) {
            if ((construct.getValue()).equals(queryValueProper)) {
                constructHolder = construct.getRelatedLexicalConstructs();
            }
        }
        return constructHolder;
    }
    // if I use while I would still have to check the line after the loop ends


    // EFFECTS: returns true if toSearchFor is present as a LexicalObject, false otherwise.
    public Boolean checkIfThesaurusContainsInput(String toSearchFor) {
        toSearchFor = protoLexicalConstruct.convertToStorageForm(toSearchFor);
        return (entriesAsStrings.contains(toSearchFor));
    }

    // REQUIRES: Construct must be present inside the thesaurus
    // EFFECTS: Removes the Construct corresponding to constructAsString from the Thesaurus fields
    //          (fieldsAsStrings is implied)
    public void delete(String constructAsString) {
        LexicalConstruct constructToRemove = returnLexicalObject(constructAsString);
        entriesAsStrings.remove(constructAsString); // value is always properly formatted everywhere
        entries.remove(returnLexicalObject(constructAsString)); // !!! could swap with constructToRemove
        EventLog.getInstance().logEvent(new Event("The construct \""
                + constructToRemove.getValue() + "\" of type: "
                + constructToRemove.getClass().getSimpleName() + ","
                + " has been removed from the Thesaurus."));
    }

    // REQUIRES: toCheck must be properly formatted (from trimming up to underscores)
    // EFFECTS: checks if toCheck is a Multi Lexeme Lexical Construct;
    //          by our definition thereof and our Storage format
    //          if toCheck contains a space character
    //              return true
    //          else
    //              return false
    public static boolean isMultiLexeme(String toCheck) {
        return (toCheck.contains("_"));
    }

    /////////////////////////////////////////////// GETTERS

    // EFFECTS: returns entriesAsStrings attribute of Thesaurus i.e. returns all entries in the thesaurus as str array
    public ArrayList<String> getEntriesAsStrings() {
        return this.entriesAsStrings;
    }

    // EFFECTS: returns entries attribute of Thesaurus as an array of type LexicalConstruct
    public ArrayList<LexicalConstruct> getEntries() {
        return this.entries;
    }

    // EFFECTS: returns indexed attribute of Thesaurus as an array of type LexicalConstruct
    public ArrayList<LexicalConstruct> getIndexed() {
        return this.indexed;
    }
}

// x.getClass() <<<

// format of EFFECTS for add !!!

// user entries are interface based <<<

// can return null in place of all defined objects <<<
