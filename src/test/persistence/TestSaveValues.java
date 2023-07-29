package persistence;

import model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestSaveValues {

    private SingularWord prayer;
    private MultiLexemeLexicalConstructs dogMan, iFellOnTheApples;
    private String locationForEmptyFile = "./data/testSaveValuesEmptyThesaurus.json";
    private String locationForGeneralFile = "./data/testSaveValuesGeneralThesaurus.json";

    private Thesaurus thesaurusForGeneral = new Thesaurus();

    private SaveValues saver;

    // this is for when thesaurus.equals() is overridden.
    @BeforeEach
    public void setVariablesForGeneralComparison() {
        ArrayList<String> containerForRelated = new ArrayList<>();
        containerForRelated.add("vespers");
        containerForRelated.add("matins");
        ArrayList<String> containerForMorphs = new ArrayList<>();
        containerForMorphs.add("prayers");
        prayer = new SingularWord("prayer");
        prayer.overwriteMorphologicalForms(containerForMorphs);
        prayer.overwriteRelatedLexicalConstructs(containerForRelated);

        containerForRelated = new ArrayList<>(); // !!! scrutinise later
        containerForRelated.add("pet-owner");
        containerForRelated.add("canine-lover");
        dogMan = new CompoundWord("dog_man");
        dogMan.overwriteRelatedLexicalConstructs(containerForRelated);

        containerForRelated = new ArrayList<>(); // !!! scrutinise later
        containerForRelated.add("passed_out");
        containerForRelated.add("sleep");
        iFellOnTheApples = new IdiomaticExpression("i_fell_on_the_apples");
        iFellOnTheApples.overwriteRelatedLexicalConstructs(containerForRelated);

        thesaurusForGeneral.addLexicalConstruct(prayer);
        thesaurusForGeneral.addLexicalConstruct(dogMan);
        thesaurusForGeneral.addLexicalConstruct(iFellOnTheApples);
    }

    @Test
    public void testSaveValuesInvalidFileNameProvided() {
        Thesaurus emptyThesaurus = new Thesaurus();
        saver = new SaveValues(emptyThesaurus);
        try {
            saver.saveThesaurus("./data/UN\0SPECIFIED");
            fail("Exception not thrown");
        } catch (FileNotFoundException e) {
            // pass
        }
        // pass
    }

    // NOTE: the thesaurus needn't be empty, the FILE we are saving to is.
    @Test
    public void testSaveValuesEmptyThesaurus() {
        Thesaurus emptyThesaurus = new Thesaurus();
        saver = new SaveValues(emptyThesaurus);
        try {
            saver.saveThesaurus(locationForEmptyFile);
        } catch (FileNotFoundException e) {
            fail("Exception was thrown when was not supposed to be.");
        }
        // pass

        LoadValues loader = new LoadValues(emptyThesaurus);
        try {
            loader.beginLoading(locationForEmptyFile);
        } catch (IOException e) {
            fail("Exception was thrown when was not supposed to be.");
        }
        assertTrue(emptyThesaurus.getEntries().isEmpty());
    }

    @Test
    public void testSaveValuesGeneralThesaurus() {
        saver = new SaveValues(thesaurusForGeneral);
        try {
            saver.saveThesaurus(locationForGeneralFile);
        } catch (FileNotFoundException e) {
            fail("File must be made available.");
        }

        // ensuring file was saved properly
        Thesaurus thesaurusForSave = new Thesaurus();
        LoadValues loader = new LoadValues(thesaurusForSave);
        try {
            thesaurusForSave = loader.beginLoading(this.locationForGeneralFile);
        } catch (IOException e) {
            fail("File is present.");
        }

        // match pairs
        assertEquals(iFellOnTheApples.getValue(), thesaurusForSave.returnLexicalObject(
                "i_fell_on_the_apples").getValue());
        assertEquals(iFellOnTheApples.getRelatedLexicalConstructs(),
                thesaurusForSave.returnLexicalObject("i_fell_on_the_apples").getRelatedLexicalConstructs());
        assertEquals(iFellOnTheApples.getCompoundOrIdiom(), ((MultiLexemeLexicalConstructs)
                thesaurusForSave.returnLexicalObject("i_fell_on_the_apples")).getCompoundOrIdiom());
        assertEquals(dogMan.getValue(), thesaurusForSave.returnLexicalObject("dog_man").getValue());
        assertEquals(dogMan.getRelatedLexicalConstructs(),
                thesaurusForSave.returnLexicalObject("dog_man").getRelatedLexicalConstructs());
        assertEquals(dogMan.getCompoundOrIdiom(), ((MultiLexemeLexicalConstructs)
                thesaurusForSave.returnLexicalObject("dog_man")).getCompoundOrIdiom());
        assertEquals(prayer.getValue(), thesaurusForSave.returnLexicalObject("prayer").getValue());
        assertEquals(prayer.getRelatedLexicalConstructs(),
                thesaurusForSave.returnLexicalObject("prayer").getRelatedLexicalConstructs());
        assertEquals(prayer.getMorphologicalForms(), ((SingularWord)
                thesaurusForSave.returnLexicalObject("prayer")).getMorphologicalForms());
        assertEquals(3, thesaurusForSave.getEntries().size());
    }
}

// test is too long !!!
// how to say [] for tests? isEmpty?

 /*String expected = iFellOnTheApples.replaceCharacterWith(valueThatShouldHaveBeenSaved, " ",
                "") ;
        String actual = iFellOnTheApples.replaceCharacterWith(valueThatWasSaved, " ",
                "") ;
        *//*JSONObject expected = new JSONObject(valueThatShouldHaveBeenSaved);
        JSONObject actual = new JSONObject(valueThatWasSaved);*/

    /*Thesaurus thesaurusExpected = new Thesaurus();
        LoadValues loader2 = new LoadValues(thesaurusExpected);
        try {
            thesaurusActual = loader2.beginLoading("./data/testFileForComparison.json");
        } catch (IOException e) {
            fail("File is present.");
        }*/


// package data persistence
// disingenuous
// scum
// encompass with
// multiple occureces of
// acturil

