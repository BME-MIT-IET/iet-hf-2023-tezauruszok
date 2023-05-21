import java.awt.*;

/**
 * A virol�gus ikonj�nak a grafikus megjelen�t�s�rt felel�s oszt�ly
 */
public class VirologistIcon {
    /**
     * A Virol�gus ikonj�nak k�z�ppontja
     */
    private Point center;
    /**
     * A kirajzoland� virol�gus
     */
    private Virologist virologist;

    /**
     * Egyparam�teres konstruktor
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
     * A kirajzol�s�rt felel�s f�ggv�ny
     */
    void Paint(){
        /**
         * IMPLEMENT�CI�RA V�R
         */
    }

    /**
     * Getter az ikon k�z�ppontj�hoz
     * @return a k�z�ppont
     */
    public Point getCenter(){
        return center;
    }

    /**
     * Setter az ikon k�z�ppontj�hoz
     * @param c a be�ll�tand� k�z�ppont
     */
    public void setCenter(Point c){
        center = c;
    }

    /**
     * Getter a megjelen�tett virol�gushoz
     * @return a virol�gus
     */
    public Virologist getVirologist(){
        return virologist;
    }

    /**
     * Setter a megjelen�tett virol�gushoz
     * @param v a be�ll�tand� virol�gus
     */
    public void setVirologist(Virologist v){
        virologist = v;
    }


}
