package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A fieldek megjeleniteseert felelos osztaly
 * minden fieldhez tartozik egy igy lehet lekerdezni a field koordinatait
 * ez szukseges a cellak letrehozasanal es kirajzolasanal
 *
 * A Pos belso osztaly a a field cellaban levo poziciojanak reprezentalasara van
 * az x koordinata a sor az y az oszlop
 *
 */
public class FieldV {
    private ImageIcon imageIcon;
    public class Pos {
        private Integer x;
        private Integer y;

        /**
         * Pos Konstruktor
         * @param x_ - x koordináta
         * @param y_ - y koordináta
         */
        public Pos(Integer x_, Integer y_) {
            x = x_;
            y = y_;
        }

        public Integer getX() { return x; }
        public Integer getY() { return y; }
        public Integer getSum() { return x+y; }
    }

    public Color c;
    private Pos pos;
    private Integer number;
    private String path = "";

    /**
     * Field Konstruktor
     * @param x_ - x koordináta
     * @param y_ - y koordináta
     */
    public FieldV(Integer x_, Integer y_) {
        pos = new Pos(x_, y_);
        number = 0;
        imageIcon = null;
    }

    public void setJacket() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/jacket.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    public void setSack() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/sack.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    public void setGlove() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/glove.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    public void setAxe() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/axe.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    public ImageIcon getIcon() {
        return imageIcon;
    }


    /**
     * Mezõ pozíció lekérdezõ
     * @return - a mezõ pozíciója
     */
    public Pos getPos() { return pos; }

}
