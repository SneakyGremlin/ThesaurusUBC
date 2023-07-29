package model;

import exceptions.NotPresentInThesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// To test concrete class Thesaurus
public class TestThesaurus {


    Thesaurus thesaurus;
    SingularWord singularWord;
    CompoundWord compoundWord;
    IdiomaticExpression idiomaticExpression;

    ArrayList<String> commonToAll = new ArrayList<>();


    @BeforeEach
    public void initialiseVariables() {
        thesaurus = new Thesaurus();
        singularWord = new SingularWord("singular");
        compoundWord = new CompoundWord("compound_word");
        idiomaticExpression = new IdiomaticExpression("seek_and_ye_may_still_not_find");
        commonToAll.add("this_is_a_lexical_construct");
    }

    @Test
    public void testThesaurusConstructor() {
        assertTrue((thesaurus.getEntries()).isEmpty());
        assertTrue((thesaurus.getEntriesAsStrings()).isEmpty());
        assertEquals(null, thesaurus.getIndexed());
    }

    @Test
    public void testAddLexicalConstruct() {
        thesaurus.getEntries().add(singularWord);
        thesaurus.returnLexicalObject("singular").overwriteRelatedLexicalConstructs(commonToAll);
        assertTrue(singularWord.equals(thesaurus.returnLexicalObject("singular")));
        singularWord.overwriteRelatedLexicalConstructs(commonToAll);
        assertEquals(singularWord, thesaurus.returnLexicalObject("singular"));
        assertEquals(singularWord, thesaurus.getEntries().get(0));
        assertTrue(thesaurus.getEntries().get(0).getClass().getSimpleName().equals("SingularWord"));
        // thesaurus.getEntries().add();
    }

    @Test
    public void testAddSingularWord() {
        thesaurus.addSingularWord("singular");
        assertEquals("singular", (thesaurus.getEntriesAsStrings()).get(0));
    }

    @Test
    public void testAddCompoundWord() {
        thesaurus.addCompoundWord("compound_word");
        assertEquals("compound_word", (thesaurus.getEntriesAsStrings()).get(0));
    }

    @Test
    public void testAddIdiomaticExpression() {
        thesaurus.addIdiomaticExpression("seek_and_ye_may_still_not_find");
        assertEquals("seek_and_ye_may_still_not_find", (thesaurus.getEntriesAsStrings()).get(0));
    }

    @Test
    public void testReturnRelatedLexicalConstructsAsArrayList() {
        thesaurus.addSingularWord("singular");
        thesaurus.getEntries().get(0).appendToRelatedLexicalConstructs("unitary");
        thesaurus.getEntries().get(0).appendToRelatedLexicalConstructs("one-off");
        thesaurus.getEntries().get(0).appendToRelatedLexicalConstructs("doozy");
        thesaurus.getEntries().get(0).appendToRelatedLexicalConstructs("belter");

        assertEquals("unitary",
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(0));
        assertEquals("one-off",
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(1));
        assertEquals("doozy",
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(2));
        assertEquals("belter",
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(3));

        // it stands to reason that what is returned by the LexicalConstruct subclass is equal to
        // what thesaurus' method should return
        assertEquals(thesaurus.getEntries().get(0).getRelatedLexicalConstructs().get(0),
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(0));
        assertEquals(thesaurus.getEntries().get(0).getRelatedLexicalConstructs().get(1),
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(1));
        assertEquals(thesaurus.getEntries().get(0).getRelatedLexicalConstructs().get(2),
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(2));
        assertEquals(thesaurus.getEntries().get(0).getRelatedLexicalConstructs().get(3),
                thesaurus.returnRelatedLexicalConstructsAsArrayList("singular").get(3));

        assertNull(thesaurus.returnRelatedLexicalConstructsAsArrayList("SOMETHING_THAT_ISN'T_THERE"));
    }

    @Test
    public void testCheckIfThesaurusContainsInput() {
        thesaurus.addSingularWord("singular");
        thesaurus.addSingularWord("seek_and_ye_may_still_not_find");
        assertEquals(true,
                thesaurus.checkIfThesaurusContainsInput("singular"));
        assertEquals(true,
                thesaurus.checkIfThesaurusContainsInput("seek_and_ye_may_still_not_find"));
        assertEquals(true,
                thesaurus.checkIfThesaurusContainsInput("Seek and ye  MAY   still not   find  "));
        assertEquals(false,
                thesaurus.checkIfThesaurusContainsInput("compound_word"));
    }

    @Test
    public void testReturnLexicalObject() {
        thesaurus.addLexicalConstruct(singularWord);
        assertEquals(singularWord, thesaurus.returnLexicalObject("singular"));
        assertTrue(thesaurus.returnLexicalObject("singular").getClass().getSimpleName()
                .equals("SingularWord"));
        thesaurus.addLexicalConstruct(compoundWord);
        assertEquals(compoundWord, thesaurus.returnLexicalObject("compound_word"));
        assertTrue(thesaurus.returnLexicalObject("compound_word").getClass().getSimpleName()
                .equals("CompoundWord"));

        // testing exception NotPresentInThesaurus
        // exception is NOT returned
        try {
            thesaurus.returnLexicalObject("singular");
        } catch (NotPresentInThesaurus e) {
            fail("This should not have failed; singularWord has been added");
        }

        // exception IS returned
        try {
            thesaurus.returnLexicalObject("abracadabra_hocus_pocus");
            fail("This should have failed; MAGIC IS FORBIDDEN... ahem... I mean the word does not exist in Thesaurus.");
        } catch (NotPresentInThesaurus e) {
            //
        }
    }

    @Test
    public void testisMultiLexeme() {
        assertFalse(thesaurus.isMultiLexeme("singular"));
        assertTrue(thesaurus.isMultiLexeme("seek_and_ye_may_still_not_find"));
    }

    @Test
    public void testGetIndexed() {
        assertEquals(null, thesaurus.getIndexed());
    }

    @Test
    public void delete(){
        thesaurus.addLexicalConstruct(singularWord);
        assertTrue(thesaurus.checkIfThesaurusContainsInput("singular"));
        thesaurus.delete("singular");
        assertFalse(thesaurus.checkIfThesaurusContainsInput("singular"));
        try {
            thesaurus.returnLexicalObject("singular");
            fail("Exception not thrown.");
        } catch (NotPresentInThesaurus e) {
            // This should have failed; singularWord has been removed.
        }
        // !!! passes if reach here?
        // !!! how do i place a fail here if I want to test other things as well
    }
}

// wait how does code coverage work? Ok so calling the method without assertions works, so long as it covers that
//      base. NEVER DO THIS code-coverage = 0.

// !!! ONE TEST TOO LONG