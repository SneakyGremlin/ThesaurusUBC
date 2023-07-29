package ui.gui.components.west.left.components;

import model.Thesaurus;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import static ui.gui.components.MainFrame.getThesaurus;

// panel with ludicrous picture(s)
public class BottomPanelForAnimation {

    public static final int INCREMENT = 10;

    private static JPanel panel;
    private static JLabel imageLabel;

    static String imagePathHomer = "./data/Homer.png";
    static String imageBrain1 = "./data/first brain.png";
    static String imageBrain2 = "./data/second brain.png";
    static String imageBrain3 = "./data/third brain.png";
    static String imageBrain4 = "./data/fourth brain.png";
    static BufferedImage image;

    // MODIFIES: this
    // EFFECTS: return an instance of BottomPanelForAnimation
    public BottomPanelForAnimation() {
        panel = new JPanel(new BorderLayout());
        initialise();
    }

    // MODIFIES: this
    // EFFECTS: adds an image to panel
    public void initialise() {
        try {
            image = ImageIO.read(new File(imagePathHomer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imageLabel = new JLabel(new ImageIcon(image));
        panel.setBackground(Color.black);
        panel.add(imageLabel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: updates the image to reflect number of entries in the thesaurus
    public static void updateImage() {
        int n = getThesaurus().getEntriesAsStrings().size();
        /*JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), n);*/
        try {
            if (n >= INCREMENT) {
                if (n >= INCREMENT * 2) {
                    if (n >= INCREMENT * 3) {
                        image = ImageIO.read(new File(imageBrain3));
                    } else {
                        image = ImageIO.read(new File(imageBrain2));
                    }
                } else {
                    image = ImageIO.read(new File(imageBrain1));
                }
            } else {
                image = ImageIO.read(new File(imagePathHomer));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageLabel = new JLabel(new ImageIcon(image));
        panel.removeAll();
        panel.add(imageLabel, BorderLayout.NORTH);
    }


    // EFFECTS: returns panel
    public JPanel getPanel() {
        return panel;
    }
}

/*if (n >= INCREMENT * 4) {
                image = ImageIO.read(new File(imageBrain4));
            } else if (n >= INCREMENT * 3) {
                ImageIO.read(new File(imageBrain3));
            } else if (n >= INCREMENT * 2) {
                ImageIO.read(new File(imageBrain2));
            } else if (n >= INCREMENT) {
                ImageIO.read(new File(imageBrain1));
            } else {
                ImageIO.read(new File(imagePathHomer));
            }*/

/*if (n >= INCREMENT) {
                if (n >= INCREMENT * 2) {
                    if (n >= INCREMENT * 3) {
                        if (n >= INCREMENT * 4) {
                            image = ImageIO.read(new File(imageBrain4));
                        } else {
                            image = ImageIO.read(new File(imageBrain3));
                        }
                    } else {
                        image = ImageIO.read(new File(imageBrain2));
                    }
                } else {
                    image = ImageIO.read(new File(imageBrain1));
                }
            } else {
                image = ImageIO.read(new File(imagePathHomer));
            }*/

// >>> static methods must refer to static variables

