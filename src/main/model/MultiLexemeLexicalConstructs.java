package model;

import java.util.ArrayList;


// A MultiLexemeLexicalConstruct is a catch-all for idioms and compound words.
public abstract class MultiLexemeLexicalConstructs extends LexicalConstruct {

    protected String compoundOrIdiom; // "C" or "I"

    // CONSTRUCTOR
    // EFFECTS: Constructs a MultiLexemeLexicalConstructs object (this is an abstract class, so it serves to
    // allow subclasses to create an object after calling super; a MultiLexemeLexicalConstructs cannot be the
    // Actual Type of an object)
    public MultiLexemeLexicalConstructs(String value) {
        super(value);
        this.value = value;
    }


    // REQUIRES: String toProcess must adhere to all pre-requisites of super.minuscule();
    //           no majuscule (Upper-case) letters should be present (*should* be called after super.minuscule())
    // EFFECTS: Ensures only single spaces exist between distinct words.
    public String singleSpaceEffect(String toProcess) {
        ArrayList<String> toStoreWords = new ArrayList<>();
        String toStoreIndividualWord = "";
        String toReturn = "";
        String toProcessSubString;
        for (int i = 0; i < toProcess.length(); i++) {
            toProcessSubString = toProcess.substring(i, i + 1);
            if (!(toProcessSubString.equals(" "))) {
                toStoreIndividualWord += toProcessSubString;
            } else {
                toStoreWords.add(toStoreIndividualWord);
                toStoreIndividualWord = "";
            }
        }
        toStoreWords.add(toStoreIndividualWord);
        for (String word : toStoreWords) {
            if (!(word.equals(""))) {
                toReturn += word + " ";
            }
        }
        return tryCatchSingleSpaceEffect(toReturn);
    }

    // EFFECTS: try-catch construct for singleSpaceEffect. In case the string is "", just return it.
    public String tryCatchSingleSpaceEffect(String toReturn) {
        try {
            return toReturn.substring(0, toReturn.length() - 1);
        } catch (StringIndexOutOfBoundsException e) {
            return ""; // !!! if the thing is "" just return that
        }
    }

    // REQUIRES: String toModify must adhere to all requirements of singleSpaceEffect();
    //           there must only be singular spaces between the lexemes/words (*should* be called after
    //           singleSpaceEffect()).
    //           replaceWith should be a character
    // EFFECTS: Replaces all spaces inside the LexicalConstruct with underscores: "_"
    public String replaceCharacterWith(String toModify, String charToReplace, String replaceWith) {
        String toReturn = "";
        for (int i = 0; i < toModify.length(); i++) {
            if (!(toModify.substring(i, i + 1).equals(charToReplace))) {
                toReturn += toModify.substring(i, i + 1);
            } else {
                if (replaceWith.equals("_")) {
                    toReturn += "_";
                } else if (replaceWith.equals(" ")) {
                    toReturn += " ";
                } // !!! else throw exception
            }
        }
        return toReturn;
    }

    // !!! Where do I put the Effects clause
    // EFFECTS: Converts to storage form taking into account spaces between the lexemes as well (in addition to all
    //          the super's method does)
    @Override
    public String convertToStorageForm(String toConvertToStorageForm) {
        return replaceCharacterWith(singleSpaceEffect(toMinuscule(trimmer(toConvertToStorageForm))), " ",
                "_");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: returns true if the relatedValueToAdd is not already present in relatedLexicalConstructs and adds the
    //          value to the aforementioned, else return false
    public boolean appendToRelatedLexicalConstructs(String toAppend) {
        if (!(this.relatedLexicalConstructs.contains(convertToStorageForm(toAppend)))) {
            this.relatedLexicalConstructs.add(convertToStorageForm(toAppend));
            return true;
        } else {
            return false;
        }
    }


    //////////////////////////////////////////////////////////// GETTER ////////////////////////////////////////////////
    // EFFECTS: returns field compoundOrIdiom
    public String getCompoundOrIdiom() {
        return this.compoundOrIdiom;
    }
}