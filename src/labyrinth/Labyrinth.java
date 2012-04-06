package labyrinth;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    private LinkedList<Punkt> allePunkte;
    
    final boolean BESUCHT = true;
    final boolean OFFEN = false;
    
    final int[] OBEN   =   {0,1};
    final int[] UNTEN  =   {0,-1};
    final int[] RECHTS =   {1,0};
    final int[] LINKS  =   {-1,0};
    
    private boolean[][] besucht;
    
    public Labyrinth(int breite, int hoehe) {   
        System.out.println(breite);
        System.out.println(hoehe);
        
        startPunkte     = new LinkedList<>();
        endPunkte       = new LinkedList<>();
        allePunkte      = new LinkedList<>();
        
        besucht = new boolean[breite+1][hoehe+1];
        
        /** 
         * Zunächst alle Punkte auf false initialisieren
         * 
         * Zeitaufwand: breite*hoehe
         */
        for(int x=0;x<=breite;x++){
            for(int y=0;y<=hoehe;y++){
                besucht[x][y] = OFFEN;
            }
        }
        
        /**
         *  RANDELEMENTE
         * 
         *  Zeitaufwand: 4*breite + 4*hoehe
         */     
        for(int x=0;x<breite;x++){
            // Oben
            startPunkte.add(new Punkt(x,0));
            endPunkte.add(new Punkt(x+1,0));
            // Unten
            startPunkte.add(new Punkt(x,hoehe));
            endPunkte.add(new Punkt(x+1,hoehe));
        }        
        for(int y=0;y<hoehe;y++){
            // Links
            startPunkte.add(new Punkt(0,y));
            endPunkte.add(new Punkt(0,y+1));
            // Rechts
            startPunkte.add(new Punkt(breite,y));
            endPunkte.add(new Punkt(breite,y+1));
        }

        allePunkte.addAll(startPunkte);
        allePunkte.addAll(endPunkte);
        
        /**
         *  LABYRITNH
         */
        int index = (int)Math.round(Math.random()*(allePunkte.size()-1));
        int x = allePunkte.get(index).gibX();
        int y = allePunkte.get(index).gibY();
        
        int newX = 0;
        int newY = 0;
                 
        int maxWandelemente = (breite +1)*(hoehe +1);
        
        boolean walk;
        int iterationcount = 0;
        
        /**
         * Raster mit Wandelementen füllen, bis die maximale Anzahl Elemente erreicht ist.
         * 
         * Zeitaufwand: 
         */
        while(startPunkte.size() < maxWandelemente){
            /**
             * 1. Zufälligen, freien angrenzenden Punkt wählen.     
             * 
             * Zeitaufwand: konstant
             */
            walk = false;
            List<int[]> dirs = Arrays.asList(OBEN, UNTEN, RECHTS, LINKS);
            Collections.shuffle(dirs);  
            for(int i=0;i<4;i++){
                int[] dir = dirs.get(i);
                newX = x+dir[0];
                newY = y+dir[1];                
                // Falls wir aus dem Raster fallen, weiter in der Schleife
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
                Punkt sp = new Punkt(x,y);
                Punkt ep = new Punkt(newX,newY);
                startPunkte.add(sp);
                endPunkte.add(ep);
                allePunkte.add(sp);
                allePunkte.add(ep);
                
                x = newX;
                y = newY;
            }else{
                // 3. Falls keine Bewegungsmöglichkeit mehr, zufälligen besuchten.
                index = (int)Math.round(Math.random()*(allePunkte.size()-1));
                x = allePunkte.get(index).gibX();
                y = allePunkte.get(index).gibY();
                allePunkte.remove(index);
            }  
            iterationcount += 1;
        }
        System.out.println("Iterationen: "+iterationcount);
        
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
