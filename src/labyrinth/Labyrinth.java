package labyrinth;

/**
 *  MATTHIAS HAT DEN GROESSTEN
 * @author Matthias
 */
public class Labyrinth {
    
    private int breite;
    
    private int hoehe;
    
    public Labyrinth(int breite, int hoehe) {
        
        this.breite = breite;
        this.hoehe = hoehe;
    }
    
    public int gibBreite() {
        
        return breite;
    }
    
    public int gibHoehe() {
        
        return hoehe;
    }
    
}
