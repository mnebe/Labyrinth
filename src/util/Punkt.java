package util;


/**
 * Ein Objekt dieser Klasse stellt einen Punkt in einer zweidimensionalen
 * Fläche dar.
 */
public class Punkt {

    /**
     * Die x-Koordinate dieses Punkts.
     */
    private short x;

    /**
     * Die y-Koordinate dieses Punkts.
     */
    private short y;

    /**
     * Erzeugt einen neuen Punkt.
     *
     * @param x Die x-Koordinate dieses Punkts.
     * @param y Die y-Koordinate dieses Punkts.
     */
    public Punkt(int x, int y) {

        this.x = (short) x;
        this.y = (short) y;
    }

    /**
     * Addiert zwei Punkte koordinatenweise und erzeugt daraus einen neuen
     * Punkt.
     *
     * @param punkt  zu addierender Punkt
     * @return neuer Punkt aus den addierten Koordinaten
     */
    public Punkt addiere(Punkt punkt) {

        return new Punkt(this.x + punkt.x, this.y + punkt.y);
    }

    /**
     * Liefert genau dann true, wenn dieser Punkt sich innerhalb des Rechtecks
     * befindet, das durch die beiden uebergebenen Punkte beschrieben wird.
     *
     * @param linkeObereEcke  linke obere Ecke des Rechtecks
     * @param rechteUntereEcke  rechte untere Ecke des Rechtecks
     * @return true genau dann, wenn dieser Punkt im Rechteck liegt
     */
    public boolean istInnerhalb(Punkt linkeObereEcke, Punkt rechteUntereEcke) {

        return linkeObereEcke.x <= this.x
               && linkeObereEcke.y <= this.y
               && this.x <= rechteUntereEcke.x
               && this.y <= rechteUntereEcke.y;
    }

    /**
     * Gibt die x-Koordinate dieses Punkts zurueck.
     *
     * @return x-Koordinate
     */
    public int gibX() {

        return x;
    }

    /**
     * Gibt die y-Koordinate dieses Punkts zurueck.
     *
     * @return y-Koordinate
     */
    public int gibY() {

        return y;
    }
}