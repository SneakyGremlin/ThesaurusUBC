package ui.gui.components;

import model.Thesaurus;
import persistence.LoadValues;
import ui.PrintLog;
import ui.gui.components.east.EastPanel;
import ui.gui.components.west.WestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static persistence.LocationParameters.LOAD_LOCATION;
import static ui.gui.components.ConstantsForGuiForVector.*;

// This is the superframe that contains all panels
public class MainFrame implements ActionListener {

    private static JFrame frame;
    private MainMenuNorth mainMenu;
    private WestPanel westPanel;
    private EastPanel eastPanel;
    private WindowListenerForMainFrame windowListener;

    private static Thesaurus thesaurus; // package private ? !!!
    //private LoadValues loader;

    // MODIFIES: this
    // EFFECTS: constructor
    public MainFrame() {
        thesaurus = new Thesaurus();
        frame = new JFrame("Thesaurus (or something)");
        frame.setSize(WHOLE_WIDTH, WHOLE_HEIGHT); // !!! Option for full screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        // frame.setForeground(Color.WHITE);
        frame.setResizable(false);
        addComponents();
        frame.pack();
        frame.setVisible(true);

        windowListener = new WindowListenerForMainFrame();
        frame.addWindowListener(windowListener);
    }

    // EFFECTS: does nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        // save, load, ld, exit
        // already has the menu bar with the X so overwrite what it does
    }

    // MODIFIES: this
    // EFFECTS: adds menu, panels, and adjusts feel
    public void addComponents() {
        addMenu();
        addPanels();
        // modifyFeel(frame);
    }

    /*// MODIFIES: this
    // EFFECTS: sets background to black, foreground to white for all elements
    private void modifyFeel(Component component) {
        for (Component c: component.getComponen) {
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
        }
    }*/

    // MODIFIES: this
    // EFFECTS:  adds a menu-bar to this
    public void addMenu() {
        mainMenu = new MainMenuNorth();
        frame.add(mainMenu.getMenuBar(), BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds the west and the east subpanels to super
    public void addPanels() {
        westPanel = new WestPanel();
        frame.add((westPanel.getPanel()), BorderLayout.WEST);
        eastPanel = new EastPanel();
        frame.add(eastPanel.getPanel(), BorderLayout.EAST);
        // frame.add(new JButton("Hi"), BorderLayout.EAST);
    }

    // EFFECTS: returns thesaurus; exists to facilitate processing
    public static Thesaurus getThesaurus() {
        return thesaurus;
    }

    // EFFECTS: returns Jframe
    public static JFrame getFrame() {
        return frame;
    }
}

// >>> the reason implementing WindowListener wasn't working is because the damn thing isn't a WINDOW
// >>> IT SHOULD ONLY WORK WHEN THE DAMN THING IS IS ACTUALLY EXTENDING A FRAME OR SMTH. I think !!! verify
// last bit
// THIS IS WHY IT'S IMPORTANT TO KNOW THE CLASSES.

// frame.pack()
// dynamic resizing !!!
// !!! can private static constatns be referenced anywhere?
// non keyword access static means what?

//       frame.setLocationRelativeTo(null);

/*        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Not Located"); // !!! this allowed?
        }
        setDefaultLookAndFeelDecorated(true);*/

// static context is when you reference without object


// peripheral skin epidermis hypoderm pachyderm
