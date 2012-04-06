package labyrinth;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * Ein Objekt dieser Klasse dient der Darstellung eines Labyrinths.
 */
public class UILabyrinth extends JFrame {

    /**
     * Breite des Labyrinths.
     */
    private static final int BREITE = 20;

    /**
     * Höhe des Labyrinths.
     */
    private static final int HOEHE = 15;

    /**
     * Pixel pro Rastereinheit des Labyrinths.
     */
    private static final int PIXEL_PRO_RASTER = 20;

    /**
     * Komponente zur Darstellung des Labyrinths.
     */
    private Labyrinthdarstellung darstellung;

    /**
     * Erzeugt die Oberfläche zur Darstellung des übergebenen Labyrinths.
     *
     * @param labyrinth  Labyrinth2, das dargestellt wird
     * @param rastermass  Anzahl Pixel pro Rastereinheit
     */
    public UILabyrinth(Labyrinth labyrinth, int rastermass) {

        super("Labyrinth");

        /* Erzeugt die Komponenten dieses Frame.
         */
        erzeugeKomponenten(labyrinth, rastermass);

        /* Anwendung beim Schließen dieses Frame beenden.
         */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Erzeugt die Komponenten dieses Frame.
     */
    private void erzeugeKomponenten(Labyrinth labyrinth, int rastermass) {

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 1));

        /*
         * Objekt zur Darstellung des Labyrinths erzeugen und dem
         * Container diesen Frame hinzufügen.
         */
        darstellung = new Labyrinthdarstellung(labyrinth, rastermass);
        container.add(darstellung);
    }

    /**
     * Start der Anwendung.
     *
     * @param args  wird nicht verwendet
     */
    public static void main(String[] args) {

        UILabyrinth fenster =
                new UILabyrinth(new Labyrinth(BREITE, HOEHE),
                                              PIXEL_PRO_RASTER);
        fenster.pack();
        fenster.setResizable(true);
        fenster.setVisible(true);
    }
}