package pack;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Test_deplacement_colorée {

    private static int delay = 150; // Délais initial du Timer (en ms)
    private static Timer timer;

    public static void main(String[] args) {
        // Création d'une fenêtre
        JFrame fenetre = new JFrame("Chenilles Multicolores");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(512, 512);

        // Créer une instance de la zone de dessin
        Dessin d = new Dessin();
        
        // Créer un panneau principal pour ajouter dessin + bouton
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(d, BorderLayout.CENTER);
        
        // Panneau pour le bouton en bas
        JPanel buttonPanel = new JPanel();
        JButton accelererButton = new JButton("Accélérer");
        buttonPanel.add(accelererButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajouter le panneau principal à la fenêtre
        fenetre.getContentPane().add(mainPanel);
        fenetre.setVisible(true);

        // Liste pour stocker plusieurs chenilles
        List<Chenille> chenilles = new ArrayList<>();

        // Créer plusieurs chenilles (dont des colorées)
        for (int i = 0; i < 15; i++) { // Par exemple, 5 chenilles
            if (i % 2 == 0) {
                chenilles.add(new Chenille_colorée(d)); // Ajouter une chenille colorée
            } else {
                chenilles.add(new Chenille(d));         // Ajouter une chenille normale
            }
        }

        // Ajouter les chenilles à la zone de dessin
        for (Chenille chenille : chenilles) {
            d.ajoutChenille(chenille);
        }

        // Utiliser un Timer pour animer les chenilles
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Chenille chenille : chenilles) {
                    chenille.deplacer(); // Déplacer chaque chenille
                }
                d.repaint(); // Redessiner la zone de dessin
            }
        });

        timer.start(); // Démarrer l'animation

        // Action à effectuer lorsqu'on clique sur le bouton "Accélérer"
        accelererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Diminuer le délai du Timer pour accélérer l'animation
                if (delay > 10) { // On empêche que le délai soit trop petit
                    delay -= 10;
                    timer.setDelay(delay); // Changer le délai du Timer
                    System.out.println("Nouveau délai: " + delay + " ms");
                }
            }
        });
    }
}
