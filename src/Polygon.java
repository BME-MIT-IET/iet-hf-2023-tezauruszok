import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A pálya mezõinek megjelenítésért felelõs absztrakt õsosztály
 */
public abstract class Polygon {

    /**
     * A sokszög oldalszámát tárolja
     */
    int sides;

    /**
     * A sokszög által megjelenített mezõ
     */
    Field field;

    /**
     * A sokszög szomszédjai
     */
    ArrayList<Polygon> neighbours;

    /**
     * ????
     */
    ArrayList<Point> points = new ArrayList<>();

    /**
     * A sokszög középpontja
     */
    Point center= new Point();

    /**
     * A rajta lévõ Virológusok ikonjának tárolására
     */
    ArrayList<String> virologistIcons = new ArrayList<>();



    /**
     * A kirajzoláshoz
     */
    JLabel drawLabel;


    /**
     * Getter a sokszög szomszédjaihoz
     * @return a sokszög szomszédjai
     */
    public ArrayList<Polygon> getNeighbours(){
        return neighbours;
    }

    /**
     * Getter a sokszög .... jaihoz
     * @return ....
     */
    public ArrayList<Point> getPoints(){
        return points;
    }

    /**
     * Getter a sokszög középpontjához
     * @return a középpont
     */
    public Point getCenter(){
        return center;
    }

    /**
     * Függvény a sokszög grafikus megjelenítéséhez
     */
    public void paint(java.awt.Graphics g){
        /**
         * IMPLEMENTÁCIÓRA VÁR
         */
    }
    /**
     * Függvény a virológus ikonjának hozzáadásához
     */
    public void addIcon(String s){
        virologistIcons.add(s);
    }

    /**
     * Függvény a virológus ikonjának az eltávolításához
     */
    public void removeIcon(String s){
        virologistIcons.remove(s);
    }

    /**
     * Setter a labelhez
     * @param drawLabel
     */
    public void setDrawLabel(JLabel drawLabel) {
        this.drawLabel = drawLabel;
    }

    public boolean inside(int x, int y){
        int[] xs = new int[points.size()];
        int[] ys = new int[points.size()];
        for(int i = 0; i < points.size(); ++i){
            xs[i] = points.get(i).x;
            ys[i] = points.get(i).y;
        }

        java.awt.Polygon p = new java.awt.Polygon(xs, ys, points.size());
        if(p.contains(x, y))
            return true;
        return false;
    }
}
