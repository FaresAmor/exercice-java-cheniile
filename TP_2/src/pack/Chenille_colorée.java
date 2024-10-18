package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Chenille_colorée extends Chenille {
    
    private Color[] couleurs;
    
    public Chenille_colorée(Dessin d) {
        super(d);
        
        couleurs = new Color[tab_A.length + 1]; // Un tableau de couleurs pour chaque anneau + la tête

        // Générer des couleurs aléatoires pour chaque segment de la chenille
        Random rand = new Random();
        for (int i = 0; i < couleurs.length; i++) {
            couleurs[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }
    }

    // Redéfinir la méthode dessiner pour inclure des couleurs
    @Override
    public void dessiner(Graphics g) {
        // Dessiner la tête avec une couleur aléatoire
        g.setColor(couleurs[0]);
        t.dessiner(g);

        // Dessiner chaque anneau avec des couleurs aléatoires
        for (int i = 0; i < tab_A.length; i++) {
            g.setColor(couleurs[i + 1]);  // Les couleurs des anneaux commencent à l'indice 1
            tab_A[i].dessiner(g);
        }
    }
}
