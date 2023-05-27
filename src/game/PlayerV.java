package game;

import player.Virologist;

import javax.swing.*;
import java.awt.*;

/**
 * A jatekos vizualis megjeleniteseert felelos osztaly
 * Minden jatekoshoz tartozik egy, ezen keresztul tudjak lekerni peldaul a kepuket
 * Itt van megvalostiva a kepek betoltese a forrasfajlok kozul
 * a kepek egysegesen vannak elnevezne hogy egyszeru legyen oket betolteni
 *
 * amikor egy jatekos medveve valtozik, annak a kepet kell betolteni
 * ezek szinten egysegesen vannak elnevezve
 *
 * */
public class PlayerV {
    public String name;
    private static int counter = 0;
    private int id;
    ImageIcon imageIcon;

    public PlayerV(String name_) {
        id = counter++;
        name = name_;
        imageIcon = new ImageIcon(getClass().getResource("/resources/" + name + ".png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }
    public void setBear () {
        imageIcon = new ImageIcon(getClass().getResource("/resources/"  + name + "_bear.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50,70, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
    }

    public ImageIcon getIcon() {
        return imageIcon;
    }
}
