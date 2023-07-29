package model;

// A Singular Word is its namesake; to exemplify:
// "Carrion"
// "Vicissitudes"
// Hyphenated words ARE considered singular words:
// "avant-garde"
// The following are *not* singular words:
// "Catch all" - This is a Compound Word
// "In spades" - This is an Idiom
import java.util.ArrayList;

// A Singular Word is its namesake; to exemplify, "Carrion", "Vicissitudes" are, "In spades", and "Catch-all" are not.
public class SingularWord extends LexicalConstruct {

    private ArrayList<String> morphologicalForms = new ArrayList<>();

    // CONSTRUCTOR
    // EFFECTS: Constructs a SingularWord object
    public SingularWord(String value) {
        super(value);
    }

    //////////////////////////////////////// SETTERS

    // REQUIRES: all entries in the inputted String Array must be properly formatted
    // MODIFIES: this
    // EFFECTS: overwrites morphologicalForms attribute of a SingularWord object
    public void overwriteMorphologicalForms(ArrayList<String> newForms) {
        this.morphologicalForms = newForms;
    }

    ///////////////////////////////////////// GETTERS

    // EFFECTS: returns morphologicalForms attribute as ArrayList<String>
    public ArrayList<String> getMorphologicalForms() {
        return this.morphologicalForms;
    }
}

