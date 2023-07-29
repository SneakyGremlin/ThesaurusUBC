package ui.gui.components;

import ui.PrintLog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Window listener for the main frame of the GUI
public class WindowListenerForMainFrame implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        PrintLog.printLog();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
