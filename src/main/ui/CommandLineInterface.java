package ui;

// import scanner class

// import exceptions.InvalidInputFromEnumeration;

import model.*;
import persistence.LoadValues;
import persistence.LocationParameters;
import persistence.SaveValues;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// CommandLineInterface that enables interactivity
public class CommandLineInterface {

    private Scanner inputFromUser; // scanner object to allow user input

    // protoLexicalConstructs are instances used to call on methods that belong to their respective classes, but
    // rely on user input
    private IdiomaticExpression protoIdiomaticExpression = new IdiomaticExpression("proto_idiomatic_expression");
    private CompoundWord protoCompoundWord = new CompoundWord("proto_compound_word");
    private SingularWord protoSingularWord = new SingularWord("proto_singular_word");
    private static Thesaurus thesaurus = new Thesaurus();
    private LexicalDeconstructor deconstructor;

    private AskToContinue askToContinue = new AskToContinue();

    private SaveValues saver = new SaveValues(thesaurus);
    private LoadValues loader = new LoadValues(thesaurus);

    static Boolean isNew = true;

    // CONSTRUCTOR
    // EFFECTS: instantiates a CommandLineInterface object and subsequent runs code that enables interactivity !!!
    public CommandLineInterface() {
        if (isNew && askIfLoadOrSave("reload data")) {
            try {
                thesaurus = loader.beginLoading(LocationParameters.LOAD_LOCATION);
            } catch (IOException e) {
                System.out.println("No file was found to pre-load and thus the thesaurus is empty :(. ");
            }
        }
        isNew = false;
        runCommandLineInterface();
        try {
            saver.saveThesaurus(LocationParameters.SAVE_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
        }
    }

    // NOTE: If data is not loaded the previous data is overridden.
    // REQUIRES: stringDeterminant is either "reload data" or "save data".
    // EFFECTS: asks the user if they want to reload data from file.
    public boolean askIfLoadOrSave(String stringDeterminant) {
        if (stringDeterminant.equals("reload data")) {
            System.out.println("Note: if you choose not to reload data, the previous data is lost forever if you "
                    + "choose to save this new state");
        }
        System.out.println("Do you wish to " + stringDeterminant + ", enter Y or N: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        return input.equals("Y");
    }

    // REQUIRES: "_" are deemed special characters at present; desist usage
    // MODIFIES: thesaurus !!!
    // EFFECTS: Primary method for UserInterface
    public void runCommandLineInterface() {
        if (enterSingularMode()) {
            singularMode();
        } else {
            lexicalDeconstructionMode();
        }
        askToContinue.askToContinueAlpha();
    }
    // boolean toRun = true; CAN USE WHILE TO ESCAPE RECURSION


    // REQUIRES: User must enter valid input in the form of Y or N. !!! Exception Handling
    // EFFECTS: determines if the user wishes to enter Singular mode i.e. view, edit, and add single entries, or if they
    //          wish to enter LexicalDeconstructor mode.
    public boolean enterSingularMode() {
        System.out.println("Would you like to Enter SingularMode? Y or N; N means you choose the Lexical"
                + " Deconstructor. ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        return (input.equals("Y"));
    }

    //////////////////////////////// SINGULAR MODE /////////////////////////////

    // EFFECTS: Initiates Singular Mode which allows you to edit or view entries
    public void singularMode() {
        if (wishToAdd()) {
            addEntrySubmodeOfSingular();
        } else {
            viewDeleteAndPossiblyEditSubmodeOfSingular();
        }
        if (askToContinue.askToContinueBeta("Singular Mode")) {
            singularMode();
        }
    }

    // EFFECTS: determines if the user wishes to add an entry or simply view previous entries; this is SingularMode
    public boolean wishToAdd() {
        System.out.println("If you wish to view and possibly edit fields of a Construct, or delete a construct,"
                + " enter 'V', else enter 'E' for introducing new entries: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        if (input.equals("V")) {
            return false;
        } else {
            return true;
        }
    }

    // Thesaurus does not contain one element of type "" !!! Format entry.
    // EFFECTS: allows the user to view the entries in the thesaurus and possibly edit their fields
    public void viewDeleteAndPossiblyEditSubmodeOfSingular() {
        if (thesaurus.getEntries().isEmpty()) {
            System.out.println("Thesaurus is at present empty; nothing to display. ");
        } else {
            String viewWithIncidence;
            System.out.println("Do you wish to display the incidence as well? Y or N: ");
            inputFromUser = new Scanner(System.in);
            viewWithIncidence = inputFromUser.nextLine();
            if (viewWithIncidence.equals("Y")) {
                printOutMembersOfAStringArrayWithIncidence(thesaurus.getEntriesAsStrings());
            } else {
                printOutMembersOfAStringArray(thesaurus.getEntriesAsStrings());
            } // !!! make new method to free up lines
            methodToSelectConstructAndThenDisplayValuesPertinentToLexicalConstructAndAskForEditingOrDeletion();
            if (askToContinue.askToContinueBeta("View and Possibly Edit")) {
                viewDeleteAndPossiblyEditSubmodeOfSingular();
            }
        }
    }

    // !!! check for type empty string entry!!!
    // EFFECTS: adds an entry to the thesaurus
    public void addEntrySubmodeOfSingular() {
        System.out.println("After the colon kindly enter a construct that you wish to append to the thesaurus.");
        System.out.println("Enter the Construct: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        input = protoCompoundWord.convertToStorageForm(input);
        if (thesaurus.checkIfThesaurusContainsInput(input)) {
            System.out.println("Thesaurus already contains the entered input. ");
            return;
        } else {
            LexicalConstruct newEntry = createConstructAfterDeterminingType(enterTypeOfLexicalConstruct(), input);
            askIfOverwriteFields(newEntry, true);
            // System.out.println(newEntry.getClass());
            thesaurus.addLexicalConstruct(newEntry); // !!! Type maintained?
        }
        if (askToContinue.askToContinueBeta("Add Entries")) {
            addEntrySubmodeOfSingular(); //
        }
    }
    // !!! thesaurus.addLexicalConstruct(create ... seems very roundabout.

    ///////////////////////////////////////// lexicalDeconstructionMode ///

    // EFFECTS: calls on the lexicalDeconstructor module; thereafter insert prompt for printing out values
    public void lexicalDeconstructionMode() {
        initialiseLexicalDeconstructor(thesaurus);
        printOutMembersOfAStringArrayWithIncidence(thesaurus.getEntriesAsStrings()); //
        // !!! ^^^ Add choice for viewing updated or new or both
        if (askToContinue.askToContinueBeta("Lexical Deconstructor")) {
            lexicalDeconstructionMode();
        }
    }

    // EFFECTS: this is a helper method for lexicalDeconstructionMode. It separates the fields that were either
    //          updated inside the thesaurus or newly added
    // for this introduce a new field of type enumeration in each construct which says either New or Updated
    // reset field to null after for loop
    // update field parameters in lexical deconstruction
    public void toSeparatelyPrintUpdatedAndAdded(ArrayList<String> added, ArrayList<String> updated) {
        System.out.println("The following are the fields that were added: ");
        System.out.println("The following are the fields that were updated: ");
    }

    ///////////////////////////////////////// HELPERS ///

    // EFFECTS: overwrites the field(s) of a LexicalObject if the user wishes to
    public void askIfOverwriteFields(LexicalConstruct newEntry, boolean isNew) {
        if (newEntry.getClass().getSimpleName().equals("SingularWord")) {
            if (!isNew) {
                promptForViewingAndEditingFieldsOfNonNewLexicalConstructs(newEntry, "MF");
            } else if (askIfYouWishToOverwriteAFieldBasedOnDeterminant("Morphs")) {
                ((SingularWord) newEntry).overwriteMorphologicalForms(inputAListOfEntries(
                        "Morphs")); // !!! CAN I GET RID OF CASTING > no object must conform
            }
        } else {
            if (!isNew) {
                promptForViewingAndEditingFieldsOfNonNewLexicalConstructs(newEntry, "RLC");
            } else if (askIfYouWishToOverwriteAFieldBasedOnDeterminant("Related")) {
                newEntry.overwriteRelatedLexicalConstructs(inputAListOfEntries("Related"));
            }
        }
    }

    // !!! Do I put requires if the input is back-end i.e. I know what the values should be.
    // REQUIRES: strDet is either "MF" - morphologicalForms - or "RLC" - RelatedLexicalConstructs -
    // EFFECTS: asks if you want to overwrite the fields of a LexicalConstruct
    public void promptForViewingAndEditingFieldsOfNonNewLexicalConstructs(LexicalConstruct newEntry, String strDet) {
        if (strDet.equals("MF")) {
            editingMorphsForSingularNonNew(newEntry);
        }
        editingRelated(newEntry);
    }

    // EFFECTS: asks if you want to overwrite relatedLexicalConstructs and then allows overwriting
    public void editingRelated(LexicalConstruct newEntry) {
        if (askIfViewRelatedOrMorphs("Related")) {
            if ((newEntry.getRelatedLexicalConstructs()).isEmpty()) {
                System.out.println("This field is empty, you should add something. ");
            } else {
                printOutMembersOfAStringArray(newEntry.getRelatedLexicalConstructs());
            }
        }
        if (askIfYouWishToOverwriteAFieldBasedOnDeterminant("Related")) {
            newEntry.overwriteRelatedLexicalConstructs(inputAListOfEntries("Related"));
        }
    }

    // EFFECTS: asks if you want to overwrite both fields of a Singular Word and allows overwriting
    public void editingMorphsForSingularNonNew(LexicalConstruct newEntry) {
        if (askIfViewRelatedOrMorphs("Morphs")) {
            if ((((SingularWord) newEntry).getMorphologicalForms()).isEmpty()) {
                System.out.println("This field is empty, you should add something. ");
            } else {
                printOutMembersOfAStringArray(((SingularWord) newEntry).getMorphologicalForms());
            }
        }
        if (askIfYouWishToOverwriteAFieldBasedOnDeterminant("Morphs")) {
            ((SingularWord) newEntry).overwriteMorphologicalForms(inputAListOfEntries("Morphs"));
        }
    }

    // REQUIRES: input must be Y or N
    // EFFECTS: return true if user wishes to enter/overwrite relatedLexicalConstructs/Morphological Fields
    //          determinantRelatedOrMorphs determines print message
    public boolean askIfYouWishToOverwriteAFieldBasedOnDeterminant(String determinantRelatedOrMorphs) {
        if (determinantRelatedOrMorphs.equals("Related")) {
            System.out.println("Do you wish to overwrite Related Lexical Constructs, Y or N: ");
        } else if (determinantRelatedOrMorphs.equals("Morphs")) {
            System.out.println("Do you wish to overwrite Morphological Forms, Y or N: ");
        }
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        return (input.equals("Y"));
    }

    // REQUIRES: relatedOrMorphs is either "Related" or "Morphs", local variable input is either "Y" or "N"
    public boolean askIfViewRelatedOrMorphs(String relatedOrMorphs) {
        System.out.println("Do you wish to view \"" + relatedOrMorphs + "\", Y or N: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        input = input.trim(); // !!! does the input naturally have a new line character?
        if (!(input.equals("Y") || input.equals("N"))) { // DeMorgan's Law
            throw new RuntimeException(); // InvalidInputFromEnumeration()
        }
        return input.equals("Y");//
    }

    // REQUIRES: determinantRelatedOrMorphs is either "related" (for field relatedLexicalConstructs) or morphs (field
    //           morphologicalForm)
    //           All inputs from the user are valid !!! this is important for self-referencing
    // EFFECTS: print different message depending on determinantRelatedOrMorphs.
    //          take a list of inputs from the user
    public ArrayList<String> inputAListOfEntries(String determinantRelatedOrMorphs) {
        ArrayList<String> container = new ArrayList<>();
        if (determinantRelatedOrMorphs.equals("Related")) {
            System.out.println("Enter related lexical constructs, enter END to terminate: ");
        } else if (determinantRelatedOrMorphs.equals("Morphs")) {
            System.out.println("Enter related morphological forms, enter END to terminate: ");
        }
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        while (!(input.equals("END"))) {
            if (!(container.contains(protoCompoundWord.convertToStorageForm(input)))) {
                container.add(protoCompoundWord.convertToStorageForm(input));
            } else {
                System.out.println("This instance of \"" + determinantRelatedOrMorphs + "\" is already present and so"
                        + " it was not added.");
            }
            System.out.println("Enter \"" + determinantRelatedOrMorphs + "\" ,or enter END to terminate: ");
            inputFromUser = new Scanner(System.in);
            input = inputFromUser.nextLine();
        }
        return container;
    }
    // !!! Option to rewrite them therein ^


    // EFFECTS: checks if the inputted value is indeed present inside the thesaurus
    //          if construct is present depending on user input
    //              either call viewer of fields
    //              or call deleter
    public void methodToSelectConstructAndThenDisplayValuesPertinentToLexicalConstructAndAskForEditingOrDeletion() {
        System.out.println(" Enter the construct whose fields you wish to view, or (construct) to delete: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        input = protoCompoundWord.convertToStorageForm(input);
        if (!(thesaurus.checkIfThesaurusContainsInput(input))) { // !!! throw an exception maybe?
            System.out.println("Entry is not present. ");
            return;
        }
        if (viewOrDelete()) {
            askIfOverwriteFields(thesaurus.returnLexicalObject(input), false);
        } else {
            deleteConstruct(input);
        }
    }

    // REQUIRES: construct must be present inside the thesaurus
    // EFFECTS: deletes the lexical construct from the thesaurus
    public void deleteConstruct(String input) {
        thesaurus.delete(input);
    }

    // EFFECTS: returns true if the user wishes to view fields of the construct, false if he wishes to delete
    public Boolean viewOrDelete() {
        System.out.println("If you wish to view sub-fields enter Y; If you wish to delete this construct, N: ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine().trim();
        if (!(input.equals("Y") || input.equals("N"))) { // DeMorgan's Law
            throw new RuntimeException(); // InvalidInputFromEnumeration()
        }
        return input.equals("Y");
    }

    // EFFECTS: prints out the members of an array
    public void printOutMembersOfAStringArray(ArrayList<String> whereinLieTheMembersToPrint) {
        for (String member : whereinLieTheMembersToPrint) {
            System.out.println(member);
        }
    }

    // EFFECTS: prints out the LexicalConstruct's value with its incidence.
    public void printOutMembersOfAStringArrayWithIncidence(ArrayList<String> whereinLieTheMembersToPrint) {
        for (String member : whereinLieTheMembersToPrint) {
            System.out.println(member + " " + thesaurus.returnLexicalObject(member).getIncidenceCounter());
        }
    }

    // REQUIRES: typeOfLexicalConstruct must be either "C", "I", or "S" which stand for CompoundWord,
    //          IdiomaticExpression,
    //          and SingularWord respectively
    // EFFECTS: creates a LexicalConstruct depending on the value of the determinant; the Construct created corresponds
    //          to the letter.
    public LexicalConstruct createConstructAfterDeterminingType(String determinant, String newEntry) {
        if (determinant.equals("C")) {
            return new CompoundWord(protoCompoundWord.convertToStorageForm(newEntry));
        } else if (determinant.equals("I")) {
            return new IdiomaticExpression(protoCompoundWord.convertToStorageForm(newEntry));
        } else {
            return new SingularWord(protoCompoundWord.convertToStorageForm(newEntry));
        }
    }

    // REQUIRES: typeOfLexicalConstruct must be either "C", "I", or "S" which stand for CompoundWord,
    //          IdiomaticExpression,
    //          and SingularWord respectively
    // EFFECTS: reads user input in the form of above and returns that value
    public String enterTypeOfLexicalConstruct() {
        String input;
        inputFromUser = new Scanner(System.in);
        System.out.println("Please declare the type of lexical construct this is - \"C\", \"I\", or \"S\" which stand "
                +
                "for CompoundWord, IdiomaticExpression, and SingularWord respectively ");
        input = inputFromUser.nextLine();
        input = input.trim();
        return input;
    }

    //////////////////////////////// LEXICAL DECONSTRUCTOR /////////////////////////////

    // NOTE: At present idioms and compound words (Except those that are hyphenated) are unincorporated !!!
    // REQUIRES: thesaurus must be pre-loaded OR must have fields that are non-null. !!! is this even necessary?
    // EFFECTS: receives a passage as input and deconstructs it into SingularWord Objects. Updates the index!!! field of
    //          the objects to reflect their incidence. If new words are encountered they are entered into thesaurus.
    public void initialiseLexicalDeconstructor(Thesaurus thesaurus) {
        deconstructor = new LexicalDeconstructor(thesaurus);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // UNUSED METHODS BELOW; WILL BE INCORPORATED LATER

    // EFFECTS: returns true if toCheck is declared to be a MultiLexemeConstruct by the user
    public boolean isMultiLexeme(String toCheck) {
        inputFromUser = new Scanner(System.in);
        System.out.println("If the entry is a MultiLexemeConstruct enter 'True', otherwise 'False': ");
        String answer = inputFromUser.nextLine();
        answer = answer.trim();
        System.out.println(answer.equals("True"));
        return answer.equals("True");
    }

    // REQUIRES: called after isMultiLexeme
    // EFFECTS: returns true if the user decides that the string is an IdiomaticExpression
    public boolean isIdiom(String trueOrFalse) {
        inputFromUser = new Scanner(System.in);
        System.out.println("If the entry is an idiom enter 'True', otherwise 'False': ");
        String answer = inputFromUser.nextLine();
        answer = answer.trim();
        System.out.println(answer.equals("True"));
        return answer.equals("True");
    }
}


// TO-DO:

// !!!!!!!!!!!!!!!!!HIGH-PRIORITY!!!!!!!!!!!!!!!!!!!!!!!!!!
// an empty string is added as an object - should terminate
// may make allowance for special characters
// during entries of fields Related and Morphs I allow " and   empty and

// What happens in my program when I hit enter and not enter Y or N !!! Make chart. Could add a third option for invalid
// entry try again.
// Have in the program code for terminate at any given time !!! If ever input is "###TERMINATE###" terminate program,
// take yourself to asToContinueAlpha (can return from here)

// is an empty string added to the arrays !!! during overwrite and input list of !!! it isn't but why
// does the null object end loops?

// Convert Compound Word to non-hyphenated compound word; hyphenated compound words are singular words !!!

// do something about underscores; replace with special instance of space like **SPACE**; but what if the user has a
// a special character (**SPACE**) usage? In that case, first check for its presence, remove it, increment counter.
// have it preloaded in the thesaurus as default value
// add field for descriptions. !!!

// Option for deleting a field entry; maybe I might forego this? just make them retype it?
// what if they misspell an entry?
//      option for appending lists of that to new entry non misspelled?
// add option for editing an entry and its subfields

// Make the functions non-recursive somehow. !!!

// make the editing methods static, trimmer et cetera.

// TO FIND OUT:
// if you pass a null object (rather than an array with 0 elements) to an iterator what happens?
// Modifies thesaurus YUP
// for testing code coverage, do I have to test the method using any subclass (Even the subclass of a subclass once?)
// so long as it's not overridden?
// should be final; check the errors in Intellij; what does should be final mean? Can you not declare variables inside
//                  the class?
// shouldn't thesaurus be static? MADE IT SO
// abstract classes; do they have method implementations? They do, but they cannot have them as well
// to prevent repetition do you pre-plan or just abstract later on or a mixture


// variables inside try catch have a different scope <<<
// be wary of null objects that are never initialized (< tautology) <<<
// if casting is incorrect it returns!!! or throws!!! an Exception <<<

// native !!!
// can use private and static together <<<
