package pack;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class deplacement {
    public static void main(String[] args) {
        // Create a window
        JFrame fenetre = new JFrame("Chenille");
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setSize(512, 512);

        // Create an instance of the drawing area
        Dessin d = new Dessin();
        fenetre.getContentPane().add(d); // Add the drawing area to the window

        // Create a single instance of Chenille and add it to the drawing area
        Chenille chenille = new Chenille(d);
        d.ajoutChenille(chenille);

        // Add key listener to handle manual movement
        d.setFocusable(true); // Ensure the drawing area is focusable to receive key events
        d.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        chenille.deplacerManuel(0); // Move forward
                        break;
                    case KeyEvent.VK_DOWN:
                        chenille.deplacerManuel(Math.PI); // Move backward
                        break;
                    case KeyEvent.VK_LEFT:
                        chenille.deplacerManuel(Math.PI / 2); // Turn left
                        break;
                    case KeyEvent.VK_RIGHT:
                        chenille.deplacerManuel(-Math.PI / 2); // Turn right
                        break;
                }
                d.repaint(); // Repaint after manual movement
            }
        });

        fenetre.setVisible(true); // Make the window visible
    }
}
