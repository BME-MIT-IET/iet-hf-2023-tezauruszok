package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * A jatek elinditasa elotti kezdokepernyo megvalositatsaert felelos osztaly
 * a kezdokepernyon lehet karaktereket valasztani, utana elinditani a jatekot
 *
 * A jtekosok valasztasat tobbfele keppen lehet csinalni:
 * lehet egyet kivalasztani egyszeru gommbnyomassal
 * lehet egyesvel tobbet kivalasztani a control gomb nyomvatartasaval
 * es lehet egyszerrre tobbet kivalasztani a shift lenyomasaval
 *
 * A kovetkezo jatekosok kozul lehete valasztani melyekhez mind kulonbozo ikon tartozik:
 * Barb
 * Andy
 * Sarah
 * Jake
 * Mike
 * Bob
 *
 * Kivalasztas utan a mentes gombra lehet elmenteni a valasztast
 * tobbszroi mentes eseten a mentesek felulirjak egymast
 *
 * A jatek elinditasakor ez az ablak bezarodik es helyette elindul a jatek,
 * amin veltlen helyekre vannak elheylezve a virologusok
 *
 */
public class OpeningMenu extends JFrame implements ActionListener {
    JFrame frame=new JFrame("Vak virológusok világa");
    ImageIcon icon=new ImageIcon(this.getClass().getResource("/resources/background.png" ));
    private String[] virologist_names={"Barb", "Andy", "Sarah", "Jake", "Mike", "Bob"};
    JList<String> list;
    JScrollPane scrollpane;
    private List<String> chosennames=new ArrayList<String>();
    private boolean bezart=false;
    private WindowV game;
    OpeningMenu(){
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(null);
         frame.setResizable(false);
        JPanel panel1=new JPanel();
        panel1.setBounds(0,0,600,300);
        panel1.setBackground(new Color(64,95,111));
        panel1.setLayout(null);
        JPanel panel2=new JPanel();
        panel2.setBounds(0,300,600,300);
        panel2.setBackground(new Color(64,95,133));
        JLabel cim=new JLabel();
        JLabel label=new JLabel();
        label.setIcon(icon);
        label.setBounds(0,30, 300, 300);
        cim.setText("Vak virológusok világa");
        cim.setForeground(Color.white);
        cim.setFont(new Font("Times New Roman", Font.BOLD, 30));
        cim.setBounds(150, 0, 300, 35);
        cim.setHorizontalAlignment(JLabel.CENTER);
        cim.setVerticalAlignment(JLabel.TOP);
        panel1.add(cim);
        panel1.add(label);
        JButton start_gomb=new JButton("Játék kezdése");
        start_gomb.setName("start_gomb");
        start_gomb.addActionListener(this) ;
        start_gomb.setBounds(200,75,200,100);
        panel2.setLayout(null);
        panel2.add(start_gomb);
        list=new  JList<String>(virologist_names);
        list.setVisible(true);
        list.setVisibleRowCount(6);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollpane=new JScrollPane(list);
        scrollpane.setPreferredSize(new Dimension(120,120));
        JLabel cim2=new JLabel("Válassza ki a játékosokat:");
        JPanel panel3=new JPanel();
        JButton kivalasztas=new JButton("Mentés");
        kivalasztas.setName("kivalasztas");
        kivalasztas.addActionListener(this);
        panel3.add(cim2);
        panel3.add(scrollpane);
        panel3.add(kivalasztas);
        panel3.setBounds(400, 100, 200, 200);
        panel1.add(panel3);
       frame.add(panel1);
       frame.add(panel2);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b=(JButton) e.getSource();
        String name=b.getName();
        if(name=="start_gomb"){
            bezart=true;
            frame.dispose();
            game = new WindowV(chosennames);
            game.setVisible(true);
        }
        if(name=="kivalasztas"){
           List<String>bejeloltek= list.getSelectedValuesList();
           chosennames = bejeloltek;
        }
    }
    String getname(int index){return chosennames.get(index);}
    boolean getbezart(){return bezart;}

    // It is needed to have it available for the SwingUITest class
    public WindowV getGame(){
        return game;
    }
}