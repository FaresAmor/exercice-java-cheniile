package pack;
import java.awt.Graphics;

public class Tete extends Anneau {
    private double cap;

    public Tete(int x, int y, int r, double cap) {
        super(x, y, r);  // Utilise les attributs de la classe parente 'Anneau'
        this.cap = cap;
    }


    @Override
    public void dessiner(Graphics g) {
        g.fillOval(x_init - r, y_init - r, 2 * r, 2 * r);  // Utilisation des attributs hérités
    }

    public void devierCap(double deltaC) {
        cap += deltaC;
    }
    public void augmenterRayon(int increment) {
        if (this.r + increment > 0) { 
            this.r += increment;
        }
    }

    public void deplacerSelonCap() {
        this.x_init = (int) (this.x_init + this.r * Math.cos(this.cap));
        this.y_init = (int) (this.y_init + this.r * Math.sin(this.cap));
    }

    public Boolean capOK(int x_MAX, int y_MAX) {
        int x_next = (int) (x_init + r * Math.cos(cap));
        int y_next = (int) (y_init + r * Math.sin(cap));
        return (x_next >= r && x_next <= x_MAX - r && y_next >= r && y_next <= y_MAX - r);
    }
}
