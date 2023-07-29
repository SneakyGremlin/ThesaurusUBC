package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.PrintLog;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {

    private EventLog el = EventLog.getInstance();
    private Thesaurus thesaurus;
    private SingularWord sw1;
    private IdiomaticExpression idiomaticExpression;
    private CompoundWord cw;

    @BeforeEach
    public void initialise() {
        el.clear();
        thesaurus = new Thesaurus();
        sw1 = new SingularWord("proto");
        idiomaticExpression = new IdiomaticExpression("i is idiom");
        cw = new CompoundWord("compound word");
    }

    @Test
    public void testAdd() {
        thesaurus.addLexicalConstruct(sw1);
        thesaurus.addLexicalConstruct(idiomaticExpression);
        thesaurus.addLexicalConstruct(cw);

        assertEquals("A new SingularWord: proto has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(0).getDescription());
        assertEquals("A new IdiomaticExpression: i is idiom has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(1).getDescription());
        assertEquals("A new CompoundWord: compound word has been added to the Thesaurus.",
                PrintLog.getQuasiEventLogForTest().get(2).getDescription());
    }

    @Test
    public void testDelete() {
        thesaurus.addLexicalConstruct(sw1);
        thesaurus.delete(sw1.getValue());

        assertEquals("The construct \"proto\" of type: SingularWord, has been removed from the Thesaurus.", PrintLog.getQuasiEventLogForTest().get(1).getDescription());
    }
}
