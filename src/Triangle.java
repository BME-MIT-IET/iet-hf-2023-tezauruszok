import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
/**
 * A haromszogeket megvalosito osztaly
 */
public class Triangle extends Polygon{
    /**
     * Konstruktor
     * @param _points, a haromszog pontjai
     */
    public Triangle(ArrayList<Point> _points){
        points.addAll(_points);
        /*Point weight = new Point(0, 0);
        for (int i = 0; i < points.size(); i++) {
            weight = new Point(weight.x + points.get(i).x , weight.y + points.get(i).y);
        }
        center = new Point((int)(weight.x/3)-25, (int)(weight.y/3)-25);*/

        center.x = 0;
        center.y = 0;
        for(Point p: _points){
            center.x += p.x;
            center.y += p.y;
        }

        center.x /= 3;
        center.y /= 3;
    }

    /**
     * Fuggveny, a kirajzolashoz szukseges
     */
    @Override
    public void paint(java.awt.Graphics g) {
        int[] xs = {points.get(0).x, points.get(1).x, points.get(2).x};
        int[] ys = {points.get(0).y, points.get(1).y, points.get(2).y};

        g.setColor(Graphics.getColor(field.getColorCode()));

        g.fillPolygon(xs, ys, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xs, ys, 3);

        //testing
        String path = null;
        try{
            path = System.getProperty("user.dir")+"/test/icons/"+virologistIcons.get(0);
            BufferedImage img = ImageIO.read(new File(path));
            g.drawImage(img, center.x - 10, center.y - 10, null);
        }
        catch(Exception ex){
            //JOptionPane.showMessageDialog(null, ex.toString()+path);
        }

    }
}
