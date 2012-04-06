package labyrinth;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import util.Punkt;

/**
 *  MATTHIAS HAT DEN GROESSTEN
 * @author Matthias
 */
public class Labyrinth {
    
    private int breite;    
    private int hoehe;
    
    private LinkedList<Punkt> startPunkte;
    private LinkedList<Punkt> endPunkte;
    
    final boolean BESUCHT = true;
    final boolean OFFEN = false;
    
    final int[] OBEN   =   {0,1};
    final int[] RECHTS =   {1,0};
    final int[] UNTEN  =   {0,-1};
    final int[] LINKS  =   {-1,0};
    
    private boolean[][] besucht;
   
    public Labyrinth(int breite, int hoehe) {   
        System.out.println(breite);
        System.out.println(hoehe);
        
        startPunkte = new LinkedList<Punkt>();
        endPunkte = new LinkedList<Punkt>();
        
        besucht = new boolean[breite+1][hoehe+1];
        
        // Zunächst alle Punkte auf false initialisieren
        for(int x=0;x<=breite;x++){
            for(int y=0;y<=hoehe;y++){
                besucht[x][y] = OFFEN;
            }
        }
        
        /**
         *  RÄNDER 
         */
        
        // Unten
        startPunkte.add(new Punkt(0,hoehe));
        endPunkte.add(new Punkt(breite,hoehe));
        // Oben
        startPunkte.add(new Punkt(0,0));
        endPunkte.add(new Punkt(breite,0));
        // Links
        startPunkte.add(new Punkt(0,0));
        endPunkte.add(new Punkt(0,hoehe));
        // Rechts
        startPunkte.add(new Punkt(breite,0));
        endPunkte.add(new Punkt(breite,hoehe));
        
        /**
         *  LABYRITNH
         */
        int startX = 0;
        int startY = 0;
        
        int x = 0;
        int y = 0;
        
        int newX = 0;
        int newY = 0;
        
        int maxIt = (breite +1)*(hoehe +1);
        int it = 0;
        
        boolean walk = true;
        
        while(it < 2*maxIt){
            
            // 1. Zufälligen angrenzenden Punkt wählen.            
            walk = false;
            List<int[]> dirs = Arrays.asList(OBEN, UNTEN, RECHTS, LINKS);
            Collections.shuffle(dirs);            
            for(int i=0;i<4;i++){
                int[] dir = dirs.get(i);
                newX = x+dir[0];
                newY = y+dir[1];
                
                // Fallse wir aus dem Raster fallen, weiter in der Schleife
                if(newX < 1 || newY < 1 || newX > breite-1 || newY > hoehe-1){
                    continue;
                }
                
                // Wenn wir einen offenen Punkt haben: hingehen.
                if(besucht[newX][newY] == OFFEN){
                    besucht[newX][newY] = BESUCHT;
                    walk = true;
                    break;
                }                
            }
            
            if(walk){
                // 2. Linie zum neuen Punkt
                startPunkte.add(new Punkt(x,y));
                endPunkte.add(new Punkt(newX,newY));
                
                x = newX;
                y = newY;
            }else{
               // 3. Falls keine Bewegungsmöglichkeit mehr, zurückspringen.
                x = startX+1;
                y = startY;
                                
                if(x > breite){
                    x  = 0;
                    y += 1;
                }
                if(y > hoehe)
                        break;
                                
                besucht[x][y] = BESUCHT;
                startX = x;
                startY = y;
            }            
            
            // 4. Im Raster weiter gehen
            
            it++;
        }
        
        
        this.breite = breite;
        this.hoehe = hoehe;
    }
    
    public int gibBreite() {
        
        return breite;
    }
    
    public int gibHoehe() {
        
        return hoehe;
    }
    
    public int gibAnzahlWandelemente(){
        return startPunkte.size();
    }
    
    public Punkt gibStartpunkt(int i){
        return startPunkte.get(i);
    }
    
    public Punkt gibEndpunkt(int i){
        return endPunkte.get(i);
    }
}
