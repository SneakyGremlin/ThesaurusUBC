package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// To test concrete class SingularWord
public class TestSingularWord {

    SingularWord rumination, nous, anima, persona, proto;
    // SingularWord catheter, renal, asystole;
    // SingularWord pachyderm, ichthyic, camelopard;

    ArrayList<String> human = new ArrayList<>();
    // ArrayList<String> medical = new ArrayList<>();
    // ArrayList<String> animal = new ArrayList<>();

    ArrayList<String> toOverrideWith = new ArrayList<>();

    String toTestTrimmer, trimmerCorrect, toTestMinuscule, minusculeCorrect;

    ArrayList<String> morphologyExample;

    @BeforeEach
    public void initialiseVariables() {
        proto = new SingularWord("proto");

        rumination = new SingularWord("rumination");
        nous = new SingularWord("nous");
        anima = new SingularWord("anima");
        persona = new SingularWord("persona");

        SingularWord persona = new SingularWord("persona");

        rumination.appendToRelatedLexicalConstructs("nous");
        rumination.appendToRelatedLexicalConstructs("anima");
        nous.appendToRelatedLexicalConstructs("rumination");
        nous.appendToRelatedLexicalConstructs("anima");
        anima.appendToRelatedLexicalConstructs("rumination");
        anima.appendToRelatedLexicalConstructs("nous");

        human.add("rumination");
        human.add("nous");
        human.add("anima");
        human.add("persona");

        toTestTrimmer = "        Sapphire    Peridot       ";
        trimmerCorrect = "Sapphire    Peridot";
        toTestMinuscule = "AlIcE AcTed   JubiLanT";
        minusculeCorrect = "alice acted   jubilant";

        morphologyExample = new ArrayList<>();

        toOverrideWith.add("successfully");
        toOverrideWith.add("overridden");
    }

    @Test
    public void testSingularWordConstructor() {
        assertTrue((persona.getRelatedLexicalConstructs()).isEmpty());
        assertEquals("persona", persona.getValue());
        assertTrue(persona.getMorphologicalForms().isEmpty());
    }

    @Test
    public void testAppendToRelatedLexicalConstructs() {
        assertTrue(rumination.appendToRelatedLexicalConstructs("rumination"));
        assertTrue(rumination.appendToRelatedLexicalConstructs("persona"));
        assertFalse(rumination.appendToRelatedLexicalConstructs("persona"));

        assertTrue(persona.appendToRelatedLexicalConstructs("rumination"));
        assertTrue(persona.appendToRelatedLexicalConstructs("nous"));
        assertTrue(persona.appendToRelatedLexicalConstructs("anima"));
        assertTrue(persona.appendToRelatedLexicalConstructs("persona"));
        assertEquals(human, persona.getRelatedLexicalConstructs());
    }

    @Test
    public void testTrimmer() {
        assertEquals(trimmerCorrect, proto.trimmer(toTestTrimmer));
    }

    @Test
    public void testToMinuscule() {
        assertEquals(minusculeCorrect, proto.toMinuscule(toTestMinuscule));
        // for special characters; irrelevant for application but applicable to method
        SingularWord special1 = new SingularWord("`hellscape");
        SingularWord special2 = new SingularWord("{hellscape");
        SingularWord special3 = new SingularWord("`hell{scape");
        assertEquals("`hellscape", special1.toMinuscule("`hellscape"));
        assertEquals("{hellscape", special2.toMinuscule("{hellscape"));
        assertEquals("`hell{scape", special3.toMinuscule("`hell{scape"));
    }

    @Test
    public void testEditMorphologicalForms(){
        morphologyExample.add("personas");
        morphologyExample.add("personata");

        persona.overwriteMorphologicalForms(morphologyExample);

        assertEquals( "personas", persona.getMorphologicalForms().get(0));
        assertEquals( "personata", persona.getMorphologicalForms().get(1));
    }

    @Test
    public void testOverwriteRelatedConstructs(){
        rumination.overwriteRelatedLexicalConstructs(toOverrideWith);
        assertEquals(toOverrideWith.get(0), rumination.getRelatedLexicalConstructs().get(0));
        assertEquals(toOverrideWith.get(1), rumination.getRelatedLexicalConstructs().get(1));
    }

    @Test
    public void testIncrementIncidenceCounter(){
        assertEquals(0, rumination.getIncidenceCounter());
        rumination.incrementIncidenceCounter();
        rumination.incrementIncidenceCounter();
        rumination.incrementIncidenceCounter();
        assertEquals(3, rumination.getIncidenceCounter());
    }

    @Test
    public void testUpdateIncidenceCounter(){
        assertEquals(0, nous.getIncidenceCounter());
        nous.updateIncidenceCounter(100);
        nous.incrementIncidenceCounter();
        assertEquals(101, nous.getIncidenceCounter());
    }
}

// !!! @ Before method too long?
