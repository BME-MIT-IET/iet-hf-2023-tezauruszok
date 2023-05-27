package game;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import field.Field;
import player.GameTable;
import player.Virologist;
/**
*
* A jatek ablakat megvalosito osztaly
* Az ablak resze a palya ami egy jtable az egyes cellakkal es bennuk lelevo virologusokkal
* agensekkel vagy felszerelesekkel
 */
public class WindowV extends JFrame {

    private GameTable gtAtm;
    /// DAVIDNAK
    private Menu menu;
    private JTable table;
    private JPanel up;
    private JLabel playerNameLabel;
    private JButton openInventoryButton;
    private JComboBox agentList;
    private JComboBox targetList;
    private JButton selectAgentButton;
    private JLabel aminoAcidCountLabel;
    private JLabel nucletideCountLabel;

    /**
     * Lepes utan amenubar frissiteset vegzi
     * Minden lepes utan frissiteni kell az adatokat, mert vehet fel pl extra anyagot a virologus
     */
    public void updateMenuBar(){
        agentList.removeAllItems();
        for(int i = 0; i < gtAtm.getCurrentPlayer().getAgentCollection().size(); i++){
            agentList.addItem(gtAtm.getCurrentPlayer().getAgentCollection().get(i));
        }
        targetList.removeAllItems();
        for(int i = 0; i < gtAtm.getPlayers().size(); i++){
            if(gtAtm.getPlayers().get(i).isSamePos(gtAtm.getCurrentPlayer().getMyField())){
                targetList.addItem(gtAtm.getPlayers().get(i).getName());
            }
        }
        playerNameLabel.setText("Current Player: " + gtAtm.getCurrentPlayer().getName());
        aminoAcidCountLabel.setText("Amino Acid:" + gtAtm.getCurrentPlayer().getAminoAcid().toString());
        nucletideCountLabel.setText("Nucleotide:" + gtAtm.getCurrentPlayer().getNucleotide().toString());
    }
    /**
    *
    * Létrehozza a felsõ menut, itt lehet hasznalni egy agenst egy masik virologusra
    * Itt latszik az eppen soron kovetkezo jatekos neve, es hogy mennyir aminosava es nukelotidja van
    * Itt lehet megnyitni az inventoryt is
     * Az inventory egy uj dialog ablakban nyilik meg es itt latszanak a virologus tartozekai:
     * A megtanult kodjai, az elkeszitett agensei, a felszerelesei
     * a rajta hato effektusok és itt tud agens letrehozni a kodokbol
     *
     * A menubar reszekent van meg ket combobox is.
     * Ezekkel lehet elhasznalni egy agenst egy masik virologusra.
     * A baloldali boxxal lehet kivalasztani hogy melyik agenset szeretne elhasznalni,
     * a jobb oldalival pedig azt hogy melyik a vele azonos mezon allo virologusra
    *
     */
    public void makeMenuBar(){
        up.removeAll();
        playerNameLabel = new JLabel();
        openInventoryButton = new JButton();
        agentList = new JComboBox();
        targetList = new JComboBox();
        selectAgentButton = new JButton();
        aminoAcidCountLabel = new JLabel("Amino Acid:" + gtAtm.getCurrentPlayer().getAminoAcid().toString());
        nucletideCountLabel = new JLabel("Nucleotide:" + gtAtm.getCurrentPlayer().getNucleotide().toString());
        playerNameLabel.setText("Current Player: " + gtAtm.getCurrentPlayer().getName());
        up.add(playerNameLabel);
        up.add(aminoAcidCountLabel);
        up.add(nucletideCountLabel);
        openInventoryButton.setText("Inventory");
        openInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Inventory.open(gtAtm);
            }
        });
        up.add(openInventoryButton);
        up.add(agentList);
        up.add(targetList);
        selectAgentButton.setText("Ágens használása");
        selectAgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Virologist vir : gtAtm.getPlayers()){
                    if(vir.getName() == targetList.getItemAt(targetList.getSelectedIndex())){
                        gtAtm.getCurrentPlayer().useAgent(vir, gtAtm.getCurrentPlayer().getAgentByIdx(agentList.getSelectedIndex() + 1));
                        break;
                    }
                }
            }
        });
        up.add(selectAgentButton);
    }
    public WindowV(List<String> names) {
        super("KURUMPLI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(900, 990));
        this.setLayout(new BorderLayout());
        //menu = m; DAVIDNAK, itt kell inicializalni...
        int colCount = 10;
        int rowCount = 10;
        //Table Model
        gtAtm = new GameTable(names);
        table = new JTable(gtAtm);
        KurumpliTableCellRenderer tableRenderer = new KurumpliTableCellRenderer();
        tableRenderer.setFont(tableRenderer.getFont().deriveFont(Font.BOLD));
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tableRenderer );
        table.setPreferredSize(new Dimension(colCount*90,rowCount*90));
        table.setRowHeight(90);
        table.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                Dimension size = getSize();
                int cellSize = Math.min(size.height / rowCount, size.width / colCount);
                cellSize--; // e nelkul kicsuszik a tablazat alja...
                table.setPreferredSize(new Dimension(colCount*cellSize,rowCount*cellSize));
                table.setRowHeight(cellSize);
            }
        });
        /**
         * A jataekosok mzgatasahoz szukseges kattintas event megvalositasa
         *  A kattintas hatasara az eppen soron kovetkezo virologus megprobal elmozogni
         *  arra a cellara amelyikre a kattintas esik
         *  ha nem tud oda mozogni akkor ez nem sikeres
         * */
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (true) {     /// AMEDDIG NEM ÁLLT LE A JÁTÉK, VALAMI KELL IDE GAMETABLEBOL
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        JTable target = (JTable)e.getSource();
                        int row = target.rowAtPoint(e.getPoint());
                        int column = target.columnAtPoint(e.getPoint());
                        // valamit lehet csinalni a mezore kattintassal, nem fog kelleni
                        FieldV f = (FieldV)table.getValueAt(row,column);
                        System.out.println(f.getPos().getX() + "\t" + f.getPos().getY() + "|" + row + "\t" + column);
                        int fieldId = row * colCount + column;
                        System.out.println(fieldId + "\t" + gtAtm.getCurrentPlayer().getMyField().getNumber());
                    }
                    if (e.getButton() == MouseEvent.BUTTON1) {      // bal gomb megnyomasakor
                        JTable target = (JTable)e.getSource();
                        int row = target.rowAtPoint(e.getPoint());
                        int column = target.columnAtPoint(e.getPoint());
                        // valamit lehet csinalni a mezore kattintassal
                        int fieldId = row * colCount + column;
                        System.out.println(fieldId + "\t" + gtAtm.getCurrentPlayer().getMyField().getNumber());
                        if (gtAtm.getCurrentPlayer().move(fieldId)) {
                            gtAtm.getCurrentPlayer().action();
                            gtAtm.stepPlayers();        // Ha sikeres a lépés akkor játékosok léptetése
                        }
                    }
                   updateMenuBar();
                    gtAtm.getCurrentPlayer().action();
                }
            }
        });
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        up = new JPanel();
        up.setLayout(new FlowLayout());
        add(up, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        makeMenuBar();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                //callMenu();       // Ez nekünk is így kell majd //! DÁVIDNAK
            }
        });
        pack();
    }
    /**
     * Táblázat egyedi megjelenése tartalom alapján
     * A táblázat egyes celláinak és az abban elhelyezkedõ dolgok megjlenítéséért felelõ sosztály
     */
    class KurumpliTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
        // érték alapján cellaszínek megváltoztatása
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            FieldV v = (FieldV)value;
            setBackground(v.c);
            ImageIcon fIcon = v.getIcon();
            ImageIcon vIcon = gtAtm.isHereVirologist(row, column);  //virologus megjelenitese
            // ikon megjelenitese
            if (vIcon != null) {
                setIcon(vIcon);
            } else if (fIcon != null) {
                setIcon(fIcon);
            } else {
                setIcon(null);
            }
            return this;
        }
    }
    public void draw() {
        this.setVisible(true);
        // draw other stuff
    }
}
