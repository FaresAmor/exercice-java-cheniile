package pack;
import java.awt.Graphics;
import java.util.Random;

public class Chenille {
    private Tete t;
    private Anneau[] tab_A = new Anneau[10];
    private Dessin d;

    public Chenille(Dessin d) {
        int r = 5;
        this.d = d;
        t = new Tete(d.getLargeur() / 2, d.getHauteur() / 2, r, 0.0);
        
        // Initialisation des anneaux
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                tab_A[i] = new Anneau(t.getX() - r, t.getY() - r, r);
            } else {
                tab_A[i] = new Anneau(tab_A[i - 1].getX() - r, tab_A[i - 1].getY(), r);
            }
        }
    }

    public void setR(int x) {
        t = new Tete(d.getLargeur() / 2, d.getHauteur() / 2, x, 0.0);;
    }


    
    // Méthode pour augmenter la taille (rayon) de chaque anneau
    public void augmenterTaille() {
    	t.augmenterRayon(2);
        for (Anneau anneau : tab_A) {
            anneau.augmenterRayon(2); // Augmenter le rayon de 2, par exemple
        }
    }
    
    public void reduireTaille() {
    	t.augmenterRayon(-2);
        for (Anneau anneau : tab_A) {
            anneau.augmenterRayon(-2);
        }
    }
    public Chenille(Dessin d,int r) {

        this.d = d;
        t = new Tete(d.getLargeur() / 2, d.getHauteur() / 2, r, 0.0);
        
        // Initialisation des anneaux
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                tab_A[i] = new Anneau(t.getX() - r, t.getY() - r, r);
            } else {
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

        // Générer un angle aléatoire dans l'intervalle [-30°, +30°] en radians
        double deltaAngle = Math.toRadians(random.nextInt(61) - 30); // 61 pour inclure [-30, 30]

        // Dévier le cap de la tête et s'assurer que le cap est correct
        t.devierCap(deltaAngle);
        
        // Tant que le cap de la tête ne garantit pas qu'elle reste dans la fenêtre
        while (!t.capOK(d.getLargeur(), d.getHauteur())) {
            // Dévier le cap de 10 degrés supplémentaires en radians
            t.devierCap(Math.toRadians(10));
        }
        
        // Déplacer la tête selon le nouveau cap
        t.deplacerSelonCap();
        
        // Déplacer les anneaux : chaque anneau prend la position du précédent
        for (int i = tab_A.length - 1; i > 0; i--) {
            tab_A[i].placerA(tab_A[i-1].getX(), tab_A[i-1].getY());
        }
        
        // L'anneau 0 prend la position de la tête
        tab_A[0].placerA(t.getX(), t.getY());
    

    }
}
