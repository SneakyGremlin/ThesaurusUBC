package ui;

import model.Event;
import model.EventLog;

import java.util.ArrayList;
import java.util.Iterator;

// Class with static method for printing the contents of the EventLog
public class PrintLog {

    private static ArrayList<Event> quasiEventLogForTest = new ArrayList<>();

    // EFFECTS: prints the descriptions of the EventLog
    public static void printLog() {
        if (!(EventLog.getInstance().iterator().hasNext())) {
            //if (EventLog.getInstance().iterator().next().getDescription().equals("Event log cleared.")) {
            System.out.println("There were no events.");
            //}
        } else {
            System.out.println("Note: added and deleted refer to current state of the program; in order to make"
                    + "the changes permanent you must save the application.");
            System.out.println("Events are: ");
        }

        for (Event e : EventLog.getInstance()) {
            if (!(e.getDescription().equals("Event log cleared."))) {
                System.out.println(e.getDescription());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: returns the quasiEventLogForTest
    public static ArrayList<Event> getQuasiEventLogForTest() {
        for (Event e : EventLog.getInstance()) {
            if (!(e.getDescription().equals("Event log cleared."))) {
                quasiEventLogForTest.add(e);
            }
        }
        return quasiEventLogForTest;
    }


}
