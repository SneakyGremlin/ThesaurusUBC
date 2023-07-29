package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// To test concrete class CompoundWord
class TestCompoundWord {

    // NOTE: Arbitrary values are tested to reflect the discretion of the user
    //       If a particular user wishes to treat a sentence as a CompoundWord
    //       there are no restrictions

    MultiLexemeLexicalConstructs compoundWord1, compoundWord2;
    String stringForCompoundWord1 = "lewis carrol";
    // String stringForCompoundWord2 = "high School";
    String value1 = "Alice    in     Wonderland was both    daft and       jubilant   ";
    String value2 = "Lyceum for the      Youth ";

    ArrayList<String> stringContainerForCompoundWord1 = new ArrayList<>();

    @BeforeEach
    public void initialiseTestVariables() {
        compoundWord1 = new CompoundWord(value1); // only for testing purposes, value is always formatted otherwise
        compoundWord2 = new CompoundWord(value2); // ^^^

        stringContainerForCompoundWord1.add(stringForCompoundWord1);
    }

    @Test
    public void testCompoundWordConstructor() {
        assertEquals(compoundWord1.convertToStorageForm(value1),
                compoundWord1.convertToStorageForm(compoundWord1.getValue()));
        assertTrue((compoundWord1.getRelatedLexicalConstructs()).isEmpty());
    }

    @Test
    public void testGetValue() {
        assertEquals(value1, compoundWord1.getValue());
    }

    @Test
    public void testSingleSpaceEffect() {
        assertEquals("Alice in Wonderland was both daft and jubilant",
                compoundWord1.singleSpaceEffect(compoundWord1.getValue()));
        assertEquals("Lyceum for the Youth"
                , compoundWord2.singleSpaceEffect(compoundWord2.getValue()));
        assertEquals("", compoundWord1.singleSpaceEffect(""));
    }

    @Test
    public void testReplaceCharacterWith() {
        assertEquals("Alice_in_Wonderland_was_both_daft_and_jubilant",
                compoundWord1.replaceCharacterWith(compoundWord1.singleSpaceEffect
                        (compoundWord1.trimmer(compoundWord1.getValue())), " ", "_"));
        assertEquals("Alice in Wonderland was both daft and jubilant",
                compoundWord1.replaceCharacterWith(compoundWord1.singleSpaceEffect
                        (compoundWord1.trimmer("Alice_in_Wonderland_was_both_daft_and_jubilant")),
                        "_", " "));
        assertEquals("Aliceis",
                compoundWord1.replaceCharacterWith(compoundWord1.singleSpaceEffect
                        (compoundWord1.trimmer("Alice is")), " ", "INVALID"));

    }

    @Test
    public void testMinuscule() {
        assertEquals("alice    in     wonderland was both    daft and       jubilant   ",
                compoundWord1.toMinuscule(compoundWord1.getValue()));
    }

    @Test
    public void testConvertToStorageForm() {
        assertEquals("alice_in_wonderland_was_both_daft_and_jubilant",
                compoundWord1.convertToStorageForm(compoundWord1.getValue()));
    }

    @Test
    public void testGetRelatedLexicalConstructs() {
        assertTrue((compoundWord1.getRelatedLexicalConstructs()).isEmpty());
    }

    @Test
    public void testAppendToRelatedLexicalConstructs() {
        assertTrue(compoundWord1.appendToRelatedLexicalConstructs(compoundWord1.convertToStorageForm(value1)));
        assertFalse(compoundWord1.appendToRelatedLexicalConstructs(compoundWord1.convertToStorageForm(value1)));
    }

    @Test
    public void testOverwriteRelatedConstructs(){
        compoundWord1.overwriteRelatedLexicalConstructs(stringContainerForCompoundWord1);

        assertEquals(stringContainerForCompoundWord1.get(0), compoundWord1.getRelatedLexicalConstructs().get(0));
    }

    @Test
    public void testIncrementIncidenceCounter(){
        assertEquals(0, compoundWord1.getIncidenceCounter());
        compoundWord1.incrementIncidenceCounter();
        compoundWord1.incrementIncidenceCounter();
        compoundWord1.incrementIncidenceCounter();
        assertEquals(3, compoundWord1.getIncidenceCounter());
    }

    @Test
    public void testUpdateIncidenceCounter(){
        assertEquals(0, compoundWord2.getIncidenceCounter());
        compoundWord2.updateIncidenceCounter(100);
        compoundWord2.incrementIncidenceCounter();
        assertEquals(101, compoundWord2.getIncidenceCounter());
    }

    @Test
    public void testCompoundOrIdiom() {
        assertEquals("C", compoundWord1.getCompoundOrIdiom());
    }
}