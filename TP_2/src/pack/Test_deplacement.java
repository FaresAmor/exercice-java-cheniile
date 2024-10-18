package pack;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Test_deplacement {

    public static void main(String[] args) {
        // création d'une fenêtre
        JFrame fenetre = new JFrame("Chenille");
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setSize(512, 512);

        // créer une instance de la zone de dessin
        Dessin d = new Dessin();

        // ajouter la zone de dessin à la fenêtre
        fenetre.getContentPane().add(d);
        fenetre.setVisible(true);

        // Créer plusieurs instances de chenilles
        for (int i = 0; i < 1; i++) {  // Exemple : ajouter 10 chenilles
            Chenille chenille = new Chenille(d);
            d.ajoutChenille(chenille);
        }

        while (true) {
            // Faire déplacer toutes les chenilles
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
    }
}
