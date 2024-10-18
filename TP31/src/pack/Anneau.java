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
    public void augmenterRayon(int increment) {
        if (this.r + increment > 0) { // S'assurer que le rayon reste positif
            this.r += increment;
        }
    }

    public void placerA(int px, int py) {
        x_init = px;
        y_init = py;
    }

    public void dessiner(Graphics g) {
        g.drawOval(x_init - r, y_init - r, 2 * r, 2 * r);
    }
}
