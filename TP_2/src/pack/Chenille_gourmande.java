package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Chenille_gourmande extends Chenille {
    private List<Anneau> tab_A_gourmande; // Liste dynamique pour les anneaux gourmands
    private Color couleurChenille; // Couleur de la chenille

   
    public Chenille_gourmande(Dessin d) {
        super(d);
        this.tab_A_gourmande = new ArrayList<>();
        this.couleurChenille = Color.RED; // Couleur fixe pour la chenille

        // Initialiser les anneaux de la chenille gourmande avec ceux de la chenille normale
        for (Anneau anneau : this.tab_A) {
            tab_A_gourmande.add(anneau);
        }
    }

    // Méthode pour ajouter un nouvel anneau à la chenille (agrandissement)
    public void agrandir() {
        // Augmenter le rayon de la tête
        t.setR(t.getR() + 1); // Augmentez le rayon de la tête de 5 par exemple

        // Mettre à jour chaque anneau
        for (Anneau anneau : tab_A_gourmande) {
            anneau.setR(anneau.getR() + 1); // Augmentez le rayon de chaque anneau
        }

        // Récupérer la position du dernier anneau pour y ajouter un nouveau
        Anneau dernierAnneau = tab_A_gourmande.get(tab_A_gourmande.size() - 1);
        int r = dernierAnneau.getR(); // Le nouveau rayon pour le nouvel anneau sera le même que le dernier
        // Ajouter un nouvel anneau juste après le dernier
        tab_A_gourmande.add(new Anneau(dernierAnneau.getX() - r, dernierAnneau.getY(), r));
    }



    // Méthode pour vérifier si la tête de la chenille touche une salade
    public boolean mangerSalade(Salade salade) {
        // Vérifier si la tête est proche de la salade
        Point positionTete = new Point(t.getX(), t.getY());
        Point positionSalade = new Point(salade.getX(), salade.getY());
        double distance = positionTete.distance(positionSalade);

        // Si la distance entre la tête et la salade est inférieure au rayon de la salade
        if (distance < salade.getTaille() + t.getR()) { // Considérer le rayon de la tête
            agrandir(); // Ajouter un nouvel anneau à la chenille
            return true; // La salade a été mangée
        }
        return false; // Pas de contact
    }

    @Override
    public void dessiner(Graphics g) {
    	g.setColor(couleurChenille); // Utiliser la couleur de la chenille
        t.dessiner(g);
        // Dessiner tous les anneaux de la chenille gourmande
        for (Anneau anneau : tab_A_gourmande) {
            anneau.dessiner(g);
        }
    }

    @Override
    public void deplacer() {
        Random random = new Random();

        // Générer un angle aléatoire dans l'intervalle [-30°, +30°] en radians
        double deltaAngle = Math.toRadians(random.nextInt(61) - 30); // 61 pour inclure [-30, 30]

        // Dévier le cap de la tête
        t.devierCap(deltaAngle);
        
        // Assurer que la tête reste dans la fenêtre
        while (!t.capOK(d.getLargeur(), d.getHauteur())) {
            t.devierCap(Math.toRadians(10));
        }
        
        // Déplacer la tête
        t.deplacerSelonCap();
        
        // Déplacer les anneaux : chaque anneau prend la position du précédent
        for (int i = tab_A_gourmande.size() - 1; i > 0; i--) {
            tab_A_gourmande.get(i).placerA(tab_A_gourmande.get(i - 1).getX(), tab_A_gourmande.get(i - 1).getY());
        }

        // L'anneau 0 prend la position de la tête
        tab_A_gourmande.get(0).placerA(t.getX(), t.getY());
    }
}
