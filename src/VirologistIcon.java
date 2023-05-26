import java.awt.*;

/**
 * A virológus ikonjának a grafikus megjelenítésért felelõs osztály
 */
public class VirologistIcon {
    /**
     * A Virológus ikonjának középpontja
     */
    private Point center;
    /**
     * A kirajzolandó virológus
     */
    private Virologist virologist;

    /**
     * Egyparaméteres konstruktor
     * @param c
     */
    VirologistIcon(Point c){
        center = c;
    }

    /**
     * Default konstruktor
     */
    VirologistIcon(){
        center = new Point(0, 0);
    }

    /**
     * A kirajzolásért felelõs függvény
     */
    void Paint(){
        /**
         * IMPLEMENTÁCIÓRA VÁR
         */
    }

    /**
     * Getter az ikon középpontjához
     * @return a középpont
     */
    public Point getCenter(){
        return center;
    }

    /**
     * Setter az ikon középpontjához
     * @param c a beállítandó középpont
     */
    public void setCenter(Point c){
        center = c;
    }

    /**
     * Getter a megjelenített virológushoz
     * @return a virológus
     */
    public Virologist getVirologist(){
        return virologist;
    }

    /**
     * Setter a megjelenített virológushoz
     * @param v a beállítandó virológus
     */
    public void setVirologist(Virologist v){
        virologist = v;
    }


}
