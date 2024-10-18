package pack;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class tp32_Q2 {

    public static void main(String[] args) {
        // création d'une fenêtre
        JFrame fenetre = new JFrame("Chenille");
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setSize(512, 512);

        // créer une instance de la zone de dessin
        Dessin d = new Dessin();

        // Créer un panneau principal pour ajouter dessin + bouton
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(d, BorderLayout.CENTER);

        // Panneau pour les boutons G et M
        JPanel buttonPanel = new JPanel();
        JButton G_Boutton = new JButton("G"); // Augmenter la taille
        JButton M_Boutton = new JButton("M"); // Réduire la taille
        buttonPanel.add(G_Boutton);
        buttonPanel.add(M_Boutton); // Ajouter le bouton M
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajouter le panneau principal à la fenêtre
        fenetre.getContentPane().add(mainPanel);
        fenetre.setVisible(true);

        // Créer une instance de chenille
        Chenille chenille = new Chenille(d, 8);
        d.ajoutChenille(chenille);

        // Thread principal pour animer la chenille
        Thread animationThread = new Thread(() -> {
            while (true) {
                // Faire déplacer la chenille
                for (Chenille c : d.getChenilles()) {
                    c.deplacer();
                }

                d.repaint();  // Redessine toutes les chenilles
                try {
                    Thread.sleep(50); // Pause for 50 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.start();

        //Listener 
        G_Boutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chenille.augmenterTaille(); 
            }
        });

  
        M_Boutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chenille.reduireTaille(); 
            }
        });
    }
}
