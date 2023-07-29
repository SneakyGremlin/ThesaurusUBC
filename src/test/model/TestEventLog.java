package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.PrintLog;

import static org.junit.jupiter.api.Assertions.*;



public class TestEventLog {

    private EventLog el = EventLog.getInstance();
    private Thesaurus thesaurus;
    private SingularWord sw1;
    private IdiomaticExpression idiomaticExpression;

    @BeforeEach
    public void initialise() {
        el.clear();
        PrintLog.getQuasiEventLogForTest().clear();
        thesaurus = new Thesaurus();
        sw1 = new SingularWord("proto");
        idiomaticExpression= new IdiomaticExpression("i is idiom");
    }

    @Test
    public void testAddLexicalConstruct() {
        thesaurus.addLexicalConstruct(sw1);
        assertEquals("A new SingularWord: proto has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(0).getDescription());
    }

    @Test
    public void testDeleteLexicalConstruct() {
        thesaurus.addLexicalConstruct(idiomaticExpression);
        thesaurus.delete("i is idiom");
        assertEquals("The construct \"i is idiom\" of type: IdiomaticExpression, has been removed from the" +
                        " Thesaurus.", PrintLog.getQuasiEventLogForTest().get(1).getDescription());
    }

    @Test
    public void testAddAddDeleteLexicalConstruct() {
        thesaurus.addLexicalConstruct(sw1);
        thesaurus.addLexicalConstruct(idiomaticExpression);
        thesaurus.delete("proto");
        // counter = 0
        // for (e: el.getInstanve) {
        // --- 0
        assertEquals("A new SingularWord: proto has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(0).getDescription());
        assertEquals("A new IdiomaticExpression: i is idiom has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(1).getDescription());
        assertEquals("The construct \"proto\" of type: SingularWord, has been removed from the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(2).getDescription());
    }
}
