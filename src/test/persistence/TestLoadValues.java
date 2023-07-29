package persistence;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoadValues {

    private String locationForEmptyFile = "./data/testLoadValuesEmptyThesaurus.json";
    private String locationForGeneralFile = "./data/testLoadValuesGeneralThesaurus.json";
    private LoadValues loader;

    private SingularWord prayer;
    private MultiLexemeLexicalConstructs dogMan, iFellOnTheApples;

    @BeforeEach
    public void setVariablesForGeneralComparison(){
        ArrayList<String> containerForRelated= new ArrayList<>();
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
    }

    @Test
    public void testLoadValuesNonExistentFile(){
        Thesaurus thesaurus = new Thesaurus();
        this.loader = new LoadValues(thesaurus);
        try {
            loader.beginLoading("./data/iDoNotExist.json");
            fail();
        } catch (IOException e) {
            // I PASS
        }
    }

    @Test
    public void testLoadValuesEmptyThesaurus(){
        Thesaurus thesaurusForEmptyJsonFile = new Thesaurus();
        this.loader = new LoadValues(thesaurusForEmptyJsonFile);
        try {
            assertEquals(thesaurusForEmptyJsonFile, loader.beginLoading(this.locationForEmptyFile));
        } catch (IOException e) {
            fail("File is present.");
        }
    }
    @Test
    public void testLoadValuesGeneralThesaurusCompoundWord() {
        Thesaurus emptyThesaurus = new Thesaurus();
        Thesaurus thesaurusFromLoadValues = new Thesaurus();
        this.loader = new LoadValues(emptyThesaurus);
        try {
            thesaurusFromLoadValues = loader.beginLoading(this.locationForGeneralFile);
        } catch (IOException e) {
            fail("File is present.");
        }
        // match pairs
        assertEquals(dogMan.getValue(), thesaurusFromLoadValues.returnLexicalObject("dog_man").getValue());
        assertEquals(dogMan.getRelatedLexicalConstructs(),
                thesaurusFromLoadValues.returnLexicalObject("dog_man").getRelatedLexicalConstructs());
        assertEquals(dogMan.getCompoundOrIdiom(), ((MultiLexemeLexicalConstructs)
                thesaurusFromLoadValues.returnLexicalObject("dog_man")).getCompoundOrIdiom());
    }

    @Test
    public void testLoadValuesGeneralThesaurusSingularWord() {
        Thesaurus emptyThesaurus = new Thesaurus();
        Thesaurus thesaurusFromLoadValues = new Thesaurus();
        this.loader = new LoadValues(emptyThesaurus);
        try {
            thesaurusFromLoadValues = loader.beginLoading(this.locationForGeneralFile);
        } catch (IOException e) {
            fail("File is present.");
        }
        // match pairs
        assertEquals(prayer.getValue(), thesaurusFromLoadValues.returnLexicalObject("prayer").getValue());
        assertEquals(prayer.getRelatedLexicalConstructs(),
                thesaurusFromLoadValues.returnLexicalObject("prayer").getRelatedLexicalConstructs());
        assertEquals(prayer.getMorphologicalForms(), ((SingularWord)
                thesaurusFromLoadValues.returnLexicalObject("prayer")).getMorphologicalForms());
    }

    @Test
    public void testLoadValuesGeneralThesaurusIdiomaticExpression() {
        Thesaurus emptyThesaurus = new Thesaurus();
        Thesaurus thesaurusFromLoadValues = new Thesaurus();
        this.loader = new LoadValues(emptyThesaurus);
        try {
            thesaurusFromLoadValues = loader.beginLoading(this.locationForGeneralFile);
        } catch (IOException e) {
            fail("File is present.");
        }
        // match pairs
        assertEquals(iFellOnTheApples.getValue(), thesaurusFromLoadValues.returnLexicalObject("i_fell_on_the_apples").getValue());
        assertEquals(iFellOnTheApples.getRelatedLexicalConstructs(),
                thesaurusFromLoadValues.returnLexicalObject("i_fell_on_the_apples").getRelatedLexicalConstructs());
        assertEquals(iFellOnTheApples.getCompoundOrIdiom(), ((MultiLexemeLexicalConstructs)
                thesaurusFromLoadValues.returnLexicalObject("i_fell_on_the_apples")).getCompoundOrIdiom());
    }

}

/* try {
            for (String s: ((loader.beginLoading(this.locationForGeneralFile)).getEntriesAsStrings()))
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

// null pointer exception for each

//privy lavatory privy to

// what is automatic modifier access ? !!!

// winded winding pass wind decompose function into

// Override .equals method <<<


// can test methods be longer than : no decompose them <<<
// DECOMPOSE FOR DIFFERENT CASES <<<
// do test classes need documentation? NOPE NOWHERE <<<
