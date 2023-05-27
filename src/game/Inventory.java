package game;

import player.GameTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * az inventory megnyilo dialogus ablakeert felelos
 * <p>
 * Az inventory egy uj dialog ablakban nyilik meg es itt latszanak a virologus tartozekai:
 * A megtanult kodjai, az elkeszitett agensei, a felszerelesei
 * a rajta hato effektusok és itt tud agens letrehozni a kodokbol
 * <p>
 * A jatekos tartozekai egy tablazatba njelennek meg a kovetkezo sorrendben:
 * kodok
 * agensek
 * felszereles
 * virologusra ahto effektusok
 * egy kulon oszlop melyben egy gomb va namti megnyomva a virologus agens tud kesziteni az adot kodbol
 */
public class Inventory extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel topPanel;
    private JTable inventoryTable;
    private DefaultTableModel model;
    private static GameTable gt;

    public Inventory(GameTable _gt) {
        topPanel = new JPanel();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        gt = _gt;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        inventoryTable.setEnabled(false);

        initData();

        inventoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = inventoryTable.rowAtPoint(evt.getPoint());
                int col = inventoryTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 0) {     // elso oszlopra kattint
                    gt.getCurrentPlayer().createAgent(col);
                    initData();
                }
            }
        });
    }

    private void initData() {
        inventoryTable.removeAll();

        model = new DefaultTableModel();
        inventoryTable.setModel(model);

        model.addColumn("Kódok");
        model.addColumn("Ágensek");
        model.addColumn("Felszerelés");
        model.addColumn("Effektek");
        //model.addColumn("Ágens készítés");

        ArrayList<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Kódok", "Ágensek", "Felszerelés", "Effektek"});
        ArrayList<Integer> asd = new ArrayList<Integer>();
        asd.add(gt.getCurrentPlayer().countOfLearnedCodes());
        asd.add(gt.getCurrentPlayer().getAgentCollection().size());
        asd.add(gt.getCurrentPlayer().getMyEquipment().size());
        asd.add(gt.getCurrentPlayer().getEffects().size());
        int sorok = Collections.max(asd);
        for (int i = 0; i < sorok; i++) {
            ArrayList<String> sor = new ArrayList<>();
            if (i < gt.getCurrentPlayer().countOfLearnedCodes()) {
                String[] a = gt.getCurrentPlayer().getLearnedCodes().get(i).getClass().toString().split(".");
                //.add(a.length == 0 ? "" : a[a.length - 1]);
                sor.add(gt.getCurrentPlayer().getLearnedCodes().get(i).getClass().toString().substring(5).split("\\.")[1]);
            } else {
                sor.add("");
            }
            if (i < gt.getCurrentPlayer().getAgentCollection().size()) {
                System.out.println(gt.getCurrentPlayer().getAgentCollection());
                String[] b = gt.getCurrentPlayer().getAgentCollection().get(i).getClass().toString().split(".");
                //sor.add(b.length == 0 ? "" : b[b.length - 1]);
                sor.add(gt.getCurrentPlayer().getAgentCollection().get(i).getClass().toString().substring(5).split("\\.")[1]);
            } else {
                sor.add("");
            }
            if (i < gt.getCurrentPlayer().getMyEquipment().size()) {
                String[] c = gt.getCurrentPlayer().getMyEquipment().get(i).getClass().toString().split(".");
                //sor.add(c.length == 0 ? "" : c[c.length - 1]);
                sor.add(gt.getCurrentPlayer().getMyEquipment().get(i).getClass().toString().substring(5).split("\\.")[1]);
            } else {
                sor.add("");
            }
            if (i < gt.getCurrentPlayer().getEffects().size()) {
                String[] d = gt.getCurrentPlayer().getEffects().get(i).getClass().toString().split(".");
                //sor.add(d.length == 0 ? "" : d[d.length - 1]);
                sor.add(gt.getCurrentPlayer().getEffects().get(i).getClass().toString().substring(5).split("\\.")[1]);
            } else {
                sor.add("");
            }

            data.add(sor.toArray());
        }

        for (Object[] line : data) {
            model.addRow(line);
        }
    }

    private void onOK() {
        this.setVisible(false);
    }

    /**
     * "main" fuggvenye az ablaknak, ezen keresztul lehet inicializalni
     *
     * @param _gt az adott gametable amiben a jatekosok szerepelnek
     *            ezen keresztul udj az inventory elkesziteni a jatekosnak az agenst
     */
    public static void open(GameTable _gt) {
        Inventory dialog = new Inventory(_gt);
        dialog.pack();
        dialog.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        topPanel = new JPanel();
        topPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(topPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        inventoryTable = new JTable();
        topPanel.add(inventoryTable, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(700, 300), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
