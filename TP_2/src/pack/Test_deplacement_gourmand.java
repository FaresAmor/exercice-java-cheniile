package pack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Test_deplacement_gourmand {

    public static void main(String[] args) {
        // Create a window
        JFrame fenetre = new JFrame("Chenilles Gourmandes");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(800, 600);
        fenetre.setResizable(false); // Optional: make the window not resizable

        // Create an instance of the drawing area
        Dessin d = new Dessin();

        // Add the drawing area to the window
        fenetre.getContentPane().add(d);
        fenetre.setVisible(true); // Make the window visible after adding components

        // List to store multiple greedy caterpillars
        List<Chenille_gourmande> chenilles = new ArrayList<>();

        // Create several greedy caterpillars
        for (int i = 0; i < 5; i++) {
            chenilles.add(new Chenille_gourmande(d)); // Add a greedy caterpillar
        }

        // Add the greedy caterpillars to the drawing area
        for (Chenille_gourmande chenille : chenilles) {
            d.ajoutChenille(chenille);
        }

        // Create and add salads to the drawing area
        for (int i = 0; i < 20; i++) {
            Salade salade = new Salade((int) (Math.random() * (d.getLargeur() - 20)), 
                                        (int) (Math.random() * (d.getHauteur() - 20)), 20);
            d.ajoutSalade(salade);
        }

        // Use a Timer to animate the caterpillars
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Chenille_gourmande chenille : chenilles) {
                    chenille.deplacer(); // Move each greedy caterpillar
                    
                    // Check if the caterpillar eats a salad
                    List<Salade> saladsToRemove = new ArrayList<>(); // Store salads to remove

                    for (Salade salade : d.getSalades()) {
                        if (chenille.mangerSalade(salade)) {
                            saladsToRemove.add(salade); // Mark the salad for removal
                            chenille.agrandir();
                            
                        }
                    }

                    // Remove the eaten salads from the drawing area
                    for (Salade salade : saladsToRemove) {
                        d.retirerSalade(salade);
                    }
                }
                d.repaint(); // Redraw the drawing area
            }
        });

        timer.start(); // Start the animation
    }
}
