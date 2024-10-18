package pack;

import java.awt.Graphics;

public class Anneau {
    protected int x_init, y_init, r;  // Changer la visibilité en 'protected'

    public Anneau() {
    }

    public Anneau(int x, int y, int r) {
        this.x_init = x;
        this.y_init = y;
        this.r = r;
    }

    public int getX() {
        return this.x_init;
    }

    public int getY() {
        return this.y_init;
    }

    public int getR() {
        return this.r;
    }

    public void setX(int x) {
        this.x_init = x; // Setter pour modifier la position x
    }

    public void setY(int y) {
        this.y_init = y; // Setter pour modifier la position y
    }

    public void setR(int r) {
        this.r = r; // Setter pour modifier le rayon
    }

    public void placerA(int px, int py) {
        x_init = px;
        y_init = py;
    }

    public void dessiner(Graphics g) {
        g.drawOval(x_init - r, y_init - r, 2 * r, 2 * r);
    }
}
