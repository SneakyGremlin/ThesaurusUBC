package model;

import java.util.ArrayList;

// A Lexical Construct is a catch-all for singular words, idioms, compound words
public abstract class LexicalConstruct {

    protected String value;
    protected ArrayList<String> relatedLexicalConstructs = new ArrayList<>();
    protected int incidenceCounter = 0;
    // !!! incidenceCounter applies to everyone however due to presently limiting functionality, only Singular Words
    //      and compound words with hyphens are affected

    // CONSTRUCTOR
    // EFFECTS: Constructs a LexicalConstruct object (this is an abstract class, so it serves to allow subclasses to
    // create an object after calling super; a LexicalConstruct cannot be the Actual Type of an object)
    public LexicalConstruct(String value) {
        this.value = value;
    }


    // EFFECTS: removes all leading and trailing whitespace from the LexicalConstruct
    public String trimmer(String toTrim) {
        return toTrim.trim();
    }

    // manual implementation of toLowerCase()
    // REQUIRES: All leading and trailing white space from the String has been removed prior to call; call after
    //           trimmer.
    // EFFECTS: Converts the string to minuscule format (all-small-letters).
    public String toMinuscule(String toConvertIntoMinuscule) {
        String converted = "";
        char toStoreLowerCaseValueOfMajuscule;
        for (char character : toConvertIntoMinuscule.toCharArray()) {
            if ((int) character >= 65 & (int) character <= 90) {
                toStoreLowerCaseValueOfMajuscule = (char) ((int) character + 32);
                converted += toStoreLowerCaseValueOfMajuscule;
            } else if ((int) character >= 97 & (int) character <= 122) {
                converted += character;
            } else {
                converted += character;
            }
        }
        return converted;
    }

    // MODIFIES: this
    // EFFECTS:  Storage Form is an all-lower-caps string with no trailing or leading white space, with singular
    //           underscores between LexicalConstructs with multiple lexemes (in lieu of spaces)
    //           Calls methods to effect the transformation into the form, and subsequently adds the entry
    //           to relatedLexicalConstructs
    //           This is limited in its scope in that it only serves to remove trailing and leading white space and
    //           converts a string to lower case (in that order)
    //           This is completely valid for the SingularWord subtype and is not Overridden there.
    //           !!!
    public String convertToStorageForm(String toConvertToStorageForm) {
        return toMinuscule(trimmer(toConvertToStorageForm));

    }


    ////////////////////////////////////////////SETTERS

    // REQUIRES: the toAppend parameter must be completely properly formatted
    // MODIFIES: this
    // EFFECTS: returns true if the relatedValueToAdd is not already present in relatedLexicalConstructs and adds the
    //          value to the aforementioned, else return false
    public boolean appendToRelatedLexicalConstructs(String toAppend) {
        if (!(this.relatedLexicalConstructs.contains(toAppend))) {
            this.relatedLexicalConstructs.add(convertToStorageForm(toAppend));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: Overwrites the contents of relatedLexicalConstructs field
    public void overwriteRelatedLexicalConstructs(ArrayList<String> newRelatedConstructs) {
        this.relatedLexicalConstructs = newRelatedConstructs;
    }

    // MODIFIES: this
    // EFFECTS: increments the incidenceCounter by one
    public void incrementIncidenceCounter() {
        this.incidenceCounter += 1;
    }

    // MODIFIES: this
    // EFFECTS: increments the incidenceCounter by one
    public void updateIncidenceCounter(int toSet) {
        this.incidenceCounter = toSet;
    }


    ///////////////////////////////////////////////GETTERS

    // EFFECTS: returns the value of the incidenceCounter
    public int getIncidenceCounter() {
        return this.incidenceCounter;
    }

    // EFFECTS: returns the value of the LexicalConstruct i.e. what is the group of lexemes being stored.
    public String getValue() {
        return this.value;
    }

    // EFFECTS: returns an array containing lexical constructs related to the Construct's value.
    public ArrayList<String> getRelatedLexicalConstructs() {
        return this.relatedLexicalConstructs;
    }
}

// !!! Proper spacing between class parts