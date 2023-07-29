package model;

// An IdiomaticExpression is a LexicalConstruct that stores Idioms; stored as all lower-case, with "_" separating
// lexemes. Examples are:
// "Seek and ye shall receive" -> "seek_and_ye_shall_receive"
public class IdiomaticExpression extends MultiLexemeLexicalConstructs {

    // CONSTRUCTOR
    // REQUIRES: field compoundOrIdiom must be "I" !!! is this requires correct?
    // EFFECTS: instantiates an IdiomaticExpression object
    public IdiomaticExpression(String value) {
        super(value);
        this.compoundOrIdiom = "I";
    }
}
