import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A p�lya mez�inek megjelen�t�s�rt felel�s absztrakt �soszt�ly
 */
public abstract class Polygon {

    /**
     * A soksz�g oldalsz�m�t t�rolja
     */
    int sides;

    /**
     * A soksz�g �ltal megjelen�tett mez�
     */
    Field field;

    /**
     * A soksz�g szomsz�djai
     */
    ArrayList<Polygon> neighbours;

    /**
     * ????
     */
    ArrayList<Point> points = new ArrayList<>();

    /**
     * A soksz�g k�z�ppontja
     */
    Point center= new Point();

    /**
     * A rajta l�v� Virol�gusok ikonj�nak t�rol�s�ra
     */
    ArrayList<String> virologistIcons = new ArrayList<>();



    /**
     * A kirajzol�shoz
     */
    JLabel drawLabel;


    /**
     * Getter a soksz�g szomsz�djaihoz
     * @return a soksz�g szomsz�djai
     */
    public ArrayList<Polygon> getNeighbours(){
        return neighbours;
    }

    /**
     * Getter a soksz�g .... jaihoz
     * @return ....
     */
    public ArrayList<Point> getPoints(){
        return points;
    }

    /**
     * Getter a soksz�g k�z�ppontj�hoz
     * @return a k�z�ppont
     */
    public Point getCenter(){
        return center;
    }

    /**
     * F�ggv�ny a soksz�g grafikus megjelen�t�s�hez
     */
    public void paint(java.awt.Graphics g){
        /**
         * IMPLEMENT�CI�RA V�R
         */
    }
    /**
     * F�ggv�ny a virol�gus ikonj�nak hozz�ad�s�hoz
     */
    public void addIcon(String s){
        virologistIcons.add(s);
    }

    /**
     * F�ggv�ny a virol�gus ikonj�nak az elt�vol�t�s�hoz
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
