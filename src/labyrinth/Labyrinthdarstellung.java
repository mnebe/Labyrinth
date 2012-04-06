package labyrinth;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import util.Punkt;

/**
 * Ein Objekt dieser Klasse stellt ein Labyrinth2 grafisch dar.
 */
public class Labyrinthdarstellung extends JPanel {

    /**
     * Innenabstand der Darstellung vom Fensterrand.
     */
    private static final int INNENABSTAND = 2;
    
    /**
     * Pixel pro Rastereinheit des Labyrinths.
     */
    private int rastermass;

    /**
     * Labyrinth2, das dargestellt wird.
     */
    private Labyrinth labyrinth;

    /**
     * Erzeugt ein Objekt dieser Klasse zur Darstellung des
     * übergebenen Labyrinths.
     */
    public Labyrinthdarstellung(Labyrinth labyrinth, int rastermass) {

        this.labyrinth = labyrinth;
        this.rastermass = rastermass;

        this.setBorder(new EmptyBorder(INNENABSTAND, INNENABSTAND,
                                       INNENABSTAND, INNENABSTAND));
    }

    /**
     * Liefert bevorzugte Größe dieser Darstellung.
     *
     * @return bevorzugte Größe basierend auf Rastermaß und
     *         Größe des Labyrinths
     */
    @Override
    public Dimension getPreferredSize() {

        return (labyrinth == null)
               ? new Dimension(0, 0)
               : new Dimension(rastermass * labyrinth.gibBreite()
                               + getInsets().left + getInsets().right,
                               rastermass * labyrinth.gibHoehe()
                               + getInsets().top + getInsets().bottom);
    }

    /**
     * Zeichnet das Labyrinth2 in die angegebene Grafikumgebung.
     *
     * @param graphics  Grafikumgebung, in die gezeichnet wird
     */
    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        graphics.setColor(Color.BLACK);

        for (int i = 0; i < labyrinth.gibAnzahlWandelemente(); i++) {
            Punkt startpunkt = labyrinth.gibStartpunkt(i);
            Punkt zielpunkt = labyrinth.gibEndpunkt(i);
            graphics.drawLine(
                    getInsets().left + rastermass * startpunkt.gibX(),
                    getInsets().top + rastermass * startpunkt.gibY(),
                    getInsets().left + rastermass * zielpunkt.gibX(),
                    getInsets().top + rastermass * zielpunkt.gibY());
        }
    }
}