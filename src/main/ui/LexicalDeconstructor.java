package ui;

import model.CompoundWord;
import model.MultiLexemeLexicalConstructs;
import model.SingularWord;
import model.Thesaurus;

import java.util.ArrayList;
import java.util.Scanner;

public class LexicalDeconstructor {
    Scanner passageInput;
    String passage;
    MultiLexemeLexicalConstructs proto = new CompoundWord("");
    String container = "There was once a creature who loved to dance in the westering sun. One day the sun never rose."
            + " And thus the creature embarked on a great quest... !!! !!! ^^^";
    AskToContinue askToContinue = new AskToContinue();

    // CONSTRUCTOR
    // REQUIRES: passage isn't empty !!! can remove now single space effect doesn't complain any more
    // EFFECTS: instantiates a LexicalDeconstructor object and subsequently deconstructs an entered passage into words
    //          if the word exists
    //              incrementCounter()
    //          else
    //              create new SingularWord object
    //              incrementCounter()
    public LexicalDeconstructor(Thesaurus thesaurus) {
        System.out.println("Enter the Passage: ");
        passageInput = new Scanner(System.in);
        passage = "";
        while (true) {
            String passageBeforeAppending = passage;
            passage += passageInput.nextLine();
            if (passageBeforeAppending.equals(passage)) {
                break;
            }
        }
        lexicalDeconstruction(passage, thesaurus);
    }


    // EFFECTS: runs the Lexical deconstruction process:
    //          first run modifiers on the passage to enforce formatting; convert to StringArray
    //          for each member in StringArray
    //              if member exists as an entry in the thesaurus
    //                  increment incidenceCounter by 1
    //              else
    //                  add new entry to thesaurus whilst increasing its incidenceCounter by 1
    public void lexicalDeconstruction(String passage, Thesaurus thesaurus) {
        ArrayList<String> containerForAdded = new ArrayList<>();
        ArrayList<String> containerForUpdated = new ArrayList<>(); // add new field !!! recently updated
        passage = removeAllNonAlphabetCharactersExceptSpaces(passage);
        passage = proto.toMinuscule(proto.singleSpaceEffect(proto.trimmer(passage)));
        String[] passageMembersAsArray = passage.split(" ");
        for (String member : passageMembersAsArray) {
            if (member.equals("")) {
                throw new RuntimeException(); // split always returns a non-empty StringArray even if val is ""
            }
            if (thesaurus.checkIfThesaurusContainsInput(member)) {
                thesaurus.returnLexicalObject(member).incrementIncidenceCounter();
            } else {
                thesaurus.addLexicalConstruct(new SingularWord(member)); // at present only singular words !!!
                thesaurus.returnLexicalObject(member).incrementIncidenceCounter();
            }
        }
        // incidence is returned in CommandLineInterface
    }

    // EFFECTS: removes all characters from the passage that are not lower or uppercase latin alphabets, and white
    //          spaces
    public String removeAllNonAlphabetCharactersExceptSpaces(String passage) {
        passage = proto.trimmer(passage);
        String toReturn = "";
        for (char character : passage.toCharArray()) {
            if ((((int) character >= 65) & ((int) character <= 90))
                    ||
                    (((int) character >= 97) & ((int) character <= 122))
                    ||
                    (character == ' ')) {
                toReturn += character;
            } else {
                toReturn += " ";
            }
        }
        return toReturn;
    }
}

// !!!
// TO-DO:
// Display only the incidence for modified characters; return them as an array and have that array printed out
// Deal with run-time exception thrown
// add functionality to read idioms and non-hyphenated compound words as well

// To-sus-out
// non-static field proto cannot be called from a static Context.
