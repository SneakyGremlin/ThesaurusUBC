package model;

// A compound word is essentially two words with spaces that are treated as one word. It is a VERY abstruse case and
// this is further compounded by the fact that hyphenated words are treated as singular
// the lexemes of a compound word are separated by a special character "_" !!!
public class CompoundWord extends MultiLexemeLexicalConstructs {

    // CONSTRUCTOR
    // REQUIRES: Inherited field compoundOrIdiom must be "C".
    // EFFECTS: instantiates a CompoundWord object, sets inherited field compoundOrIdiom to "C".
    public CompoundWord(String value) {
        super(value);
        this.compoundOrIdiom = "C";
    }

}

// use enumeration and regex !!!