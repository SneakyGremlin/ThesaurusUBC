package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// To test concrete class IdiomaticExpression
public class TestIdiomaticExpression {

    IdiomaticExpression idiom1, idiom2;

    @BeforeEach
    public void initialiseVariables(){
        idiom1 = new IdiomaticExpression("may the wind be at your back");
        idiom2 = new IdiomaticExpression("the valley of death");
    }

    @Test
    public void testIdiomaticExpressionConstructor(){
        assertTrue(idiom1.getRelatedLexicalConstructs().isEmpty());
        assertEquals("may the wind be at your back", idiom1.getValue());
    }

    @Test
    public void testGetCompoundOrIdiom() {
        assertEquals("I", idiom1.getCompoundOrIdiom());
    }

    // Remaining test methods are tested in CompoundWord and SingularWord.

}
