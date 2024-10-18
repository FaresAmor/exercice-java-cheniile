package pack;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Dessin extends JPanel {
    // Déclarer une liste de chenilles
    private List<Chenille> chenilles = new ArrayList<>();
    private List<Salade> salades = new ArrayList<>(); // Liste pour stocker les salades

    public int getLargeur() {
        return getWidth();
    }

    public int getHauteur() {
        return getHeight();
    }

    // Une méthode qui ajoute une chenille à la zone de dessin
    public void ajoutChenille(Chenille c) {
        chenilles.add(c); // Ajoute une chenille à la liste
        repaint();
    }

    // Méthode pour ajouter une salade à la zone de dessin
    public void ajoutSalade(Salade salade) {
        salades.add(salade); // Ajoute une salade à la liste
        repaint();
    }

    // Méthode pour retirer une salade de la zone de dessin
    public void retirerSalade(Salade salade) {
        salades.remove(salade); // Retire la salade de la liste
        repaint();
    }

    // Ajoute un "getter" pour accéder à la liste des chenilles
    public List<Chenille> getChenilles() {
        return chenilles;
    }

    // Ajoute un "getter" pour accéder à la liste des salades
    public List<Salade> getSalades() {
        return salades;
    }

    public void pause(int duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessine toutes les salades
        for (Salade salade : salades) {
            salade.dessiner(g);
        }
        // Dessine toutes les chenilles
        for (Chenille c : chenilles) {
            c.dessiner(g);
        }
    }
}
