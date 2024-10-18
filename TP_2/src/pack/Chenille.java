package pack;

import java.awt.Graphics;
import java.util.Random;

public class Chenille {
    protected Tete t;
    protected Anneau[] tab_A = new Anneau[10];
    protected Dessin d;

    public Chenille(Dessin d) {
        int r = 5; // Radius for the head and segments
        this.d = d;

        // Initialize the head in the center of the window, adjusted by its radius
        t = new Tete(d.getLargeur() / 2, d.getHauteur() / 2, r, 0.0);

        // Initialize the segments (anneaux)
        for (int i = 0; i < tab_A.length; i++) {
            if (i == 0) {
                // First segment follows the head
                tab_A[i] = new Anneau(t.getX() - r, t.getY(), r);
            } else {
                // Subsequent segments follow the previous one
                tab_A[i] = new Anneau(tab_A[i - 1].getX() - r, tab_A[i - 1].getY(), r);
            }
        }
    }


    public void dessiner(Graphics g) {
        t.dessiner(g);
        for (Anneau anneau : tab_A) {
            anneau.dessiner(g);
        }
    }

    public void deplacer() {
        Random random = new Random();

        // Generate a random angle in the range [-30°, +30°] in radians
        double deltaAngle = Math.toRadians(random.nextInt(61) - 30); // 61 to include [-30, 30]

        // Deviate the heading of the head
        t.devierCap(deltaAngle);

        // Ensure the head does not go out of bounds
        if (!t.capOK(d.getLargeur(), d.getHauteur())) {
            t.devierCap(Math.toRadians(180)); // turn around if out of bounds
        }

        // Move the head according to the new heading
        t.deplacerSelonCap();

        // Move the segments (anneaux): each segment takes the position of the previous one
        for (int i = tab_A.length - 1; i > 0; i--) {
            tab_A[i].placerA(tab_A[i - 1].getX(), tab_A[i - 1].getY());
        }

        // The first segment takes the position of the head
        tab_A[0].placerA(t.getX(), t.getY());
    }

    public void deplacerManuel(double angle) {
        // Adjust the heading of the head by the specified angle
        t.devierCap(angle);

        // Move the head according to the new heading
        t.deplacerSelonCap();

        // Ensure the head does not go out of bounds
        if (!t.capOK(d.getLargeur(), d.getHauteur())) {
            t.devierCap(Math.toRadians(180)); // turn around if out of bounds
        }

        // Move the segments (anneaux) accordingly
        for (int i = tab_A.length - 1; i > 0; i--) {
            tab_A[i].placerA(tab_A[i - 1].getX(), tab_A[i - 1].getY());
        }

        // The first segment takes the position of the head
        tab_A[0].placerA(t.getX(), t.getY());
    }
}
