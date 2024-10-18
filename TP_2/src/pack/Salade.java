package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Salade {
    private int x, y; // Position of the salad
    private int taille; // Size of the salad

    public Salade(int x, int y, int taille) {
        this.x = x;
        this.y = y;
        this.taille = taille;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTaille() {
        return taille;
    }

    // Method to draw the salad
    public void dessiner(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, taille, taille); // Draw the salad as a green circle
    }
}
