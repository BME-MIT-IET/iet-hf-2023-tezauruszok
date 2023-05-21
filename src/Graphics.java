import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * A grafikat megvalosito osztalyt
 */
public class Graphics {
    /**
     * Színek definiálva
     */
    public static Color lightColor = new Color(231,232,164);
    public static Color greenColor = new Color(92,107,40);
    public static Color darkColor = new Color(20,28,17);
    public static Color orangeColor = new Color(218,148,50);
    /**
     * Virologus icocokbol allo lista
     */
    ArrayList<VirologistIcon> icons;
    /**
     * Polygonokbol allo lista
     */
    ArrayList<Polygon> polygons = new ArrayList<>();

    /**
     * SWING elemek mainMenu
     */
    private JPanel panel_1;
    private JButton bNewGame;
    private JButton bLoadGame;
    private JButton bExit;
    private JPanel drawPanel;
    private JFrame mainMenuframe;
    /** SWING elemek mainMenu vége
     */

    /**
     * SWING elemek gameMenu
     */
    private JButton attackButton;
    private JButton learnButton;
    private JButton spreadButton;
    private JButton stealButton;
    private JButton pickUpButton;
    private JButton craftButton;
    private JLabel WorkingUnitLabel;
    public JLabel CurrentNameLabel;
    private JPanel PanelMain;
    private JTextArea currentItemsOnFieldTextField;
    private JButton showThingsButton;
    private JButton virologistStatButton;
    private JFrame gameMenuframe;

    private JLabel ikon;
    /** SWING elemek gameMenu vége
     */


    /**
     * Függvény a mainMenu elemeinek inicializálásához
     */
    public void initMainMenu(){
        panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout(0, 0));
        panel_1.setMinimumSize(new Dimension(600, 480));
        panel_1.setPreferredSize(new Dimension(600, 480));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setPreferredSize(new Dimension(107, 480));
        panel_1.add(panel2, BorderLayout.NORTH);
        bExit = new JButton();
        bExit.setPreferredSize(new Dimension(150, 50));
        bExit.setText("Exit");
        bExit.addActionListener(new ExitButtonListener());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(bExit, gbc);
        bLoadGame = new JButton();
        bLoadGame.setPreferredSize(new Dimension(150, 50));
        bLoadGame.setText("Load game");
        bLoadGame.addActionListener(new LoadButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(bLoadGame, gbc);
        bNewGame = new JButton();
        bNewGame.setAlignmentY(0.5f);
        bNewGame.setAutoscrolls(true);
        bNewGame.setEnabled(true);
        bNewGame.setHorizontalAlignment(0);
        bNewGame.setPreferredSize(new Dimension(150, 50));
        bNewGame.setText("Start Game");
        bNewGame.addActionListener(new StartGameButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(bNewGame, gbc);
        mainMenuframe = new JFrame("legendary_student_list");
        mainMenuframe.setContentPane(panel_1);
        mainMenuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenuframe.setSize(1280, 720);
    }

    /**
     * Függvény a mainMenu megjelenítéséhez
     */
    public void showMainMenu(){
        if(gameMenuframe != null){
            gameMenuframe.setVisible(false);
        }
        if(mainMenuframe != null){
            mainMenuframe.setVisible(true);
        }
    }

    /**
     * Függvény a gameMenu elemeinek inicializálásához
     */
    public void initGameMenu(){
        PanelMain = new JPanel();
        PanelMain.setLayout(new GridBagLayout());
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 15;
        gbc.fill = GridBagConstraints.VERTICAL;
        PanelMain.add(spacer2, gbc);

        drawPanel = new JPanel(null){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                paintAll2(g);
            }
        };
        drawPanel.addMouseListener(new StepMouseListener());

        //drawPanel.setLayout(new GridBagLayout());
        drawPanel.setBackground(new Color(1f, 1f, 1f));
        drawPanel.setPreferredSize(new Dimension(800, 500));

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 15;
        gbc.fill = GridBagConstraints.BOTH;
        PanelMain.add(drawPanel, gbc);
        stealButton = new JButton();
        stealButton.setText("Steal");
        stealButton.addActionListener(new StealButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(stealButton, gbc);
        spreadButton = new JButton();
        spreadButton.setText("Spread");
        spreadButton.addActionListener(new SpreadButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(spreadButton, gbc);
        attackButton = new JButton();
        attackButton.setText("Attack");
        attackButton.addActionListener(new AttackButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(attackButton, gbc);
        learnButton = new JButton();
        learnButton.setText("Learn");
        learnButton.addActionListener(new LearnButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(learnButton, gbc);
        WorkingUnitLabel = new JLabel();
        WorkingUnitLabel.setPreferredSize(new Dimension(200, 40));
        WorkingUnitLabel.setText("Remaining working units: 4");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMain.add(WorkingUnitLabel, gbc);
        CurrentNameLabel = new JLabel();
        CurrentNameLabel.setPreferredSize(new Dimension(200, 40));
        CurrentNameLabel.setText("Actual player: TestVirologus");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMain.add(CurrentNameLabel, gbc);
        Font font = new Font("Courier", 2,12);
        currentItemsOnFieldTextField = new JTextArea();
        currentItemsOnFieldTextField.setEditable(false);
        currentItemsOnFieldTextField.setInheritsPopupMenu(true);
        currentItemsOnFieldTextField.setPreferredSize(new Dimension(220, 300));
        currentItemsOnFieldTextField.setText("Current things on field:  ");
        currentItemsOnFieldTextField.setFont(font);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight = 9;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(currentItemsOnFieldTextField, gbc);
        pickUpButton = new JButton();
        pickUpButton.setText("Pick up");
        pickUpButton.addActionListener(new PickupButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(pickUpButton, gbc);
        craftButton = new JButton();
        craftButton.setText("Craft");
        craftButton.addActionListener(new CraftButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(craftButton, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        PanelMain.add(spacer3, gbc);
        showThingsButton = new JButton();
        showThingsButton.setText("ShowThings");
        showThingsButton.addActionListener(new ShowThingsButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(showThingsButton, gbc);
        virologistStatButton = new JButton();
        virologistStatButton.setText("Virologist stat");
        virologistStatButton.addActionListener(new StatButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(virologistStatButton, gbc);
        JButton nextRoundButton = new JButton();
        nextRoundButton.setText(">>>");
        nextRoundButton.addActionListener(new nextRoundButtonListener());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 16;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMain.add(nextRoundButton, gbc);
        gameMenuframe = new JFrame("legendary_student");
        gameMenuframe.setContentPane(PanelMain);
        gameMenuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem i1 = new JMenuItem("Save");
        i1.addActionListener(new SaveButtonListener());
        JMenuItem i2 = new JMenuItem("Back to main menu");
        i2.addActionListener(new backToMainMenu());
        JMenuItem i3 = new JMenuItem("Exit");
        i3.addActionListener(new ExitButtonListener());
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        mb.add(menu);
        gameMenuframe.setJMenuBar(mb);
        gameMenuframe.setSize(1280, 720);
    }

    /**
     * Függvény a gameMenu megjelenítéséhez
     */
    public void showGameMenu(){
        if(mainMenuframe != null){
            mainMenuframe.setVisible(false);
        }
        if(gameMenuframe != null){
            gameMenuframe.setVisible(true);
        }
        updateComponents();
    }
    /**
     * Függvény ami frissiti a componenseket
     */
    public void updateComponents(){
        try{
            //update labels
            CurrentNameLabel.setText("Actual player: "+Prototype.g.getActualPlayer().getName());
            WorkingUnitLabel.setText("Remaining working units: "+Prototype.g.getActualPlayer().getWork_Unit());
        }
        catch(Exception ex){

        }


    }


    /**
     * Függvény, mely kirajzolja az összes dolgot
     */
    public void paintAll2(java.awt.Graphics g){
        /**
        Implementációra vár!
         */

        for(Polygon p : polygons){
            p.paint(g);

        }
    }


    /**
     * Eseménykezelõ osztályok
     */

    /**
     * Save gomb eseménykezelõ osztálya
     */
    public class SaveButtonListener implements ActionListener {

        /**
         * A felugró ablakon az input mezõ
         */
        JTextField field;

        /**
         * A felugró ablak kerete
         */
        JFrame frame;


        /**
         * Az eseménykezelõ függvény
         *
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame("Save");
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            label.setText("Filename:");
            field = new JTextField("Type here...");
            JButton bok = new JButton("Save");
            bok.addActionListener(new SaveOkButtonListener());
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new CancelButtonListener());
            panel.add(label);
            panel.add(field);
            panel.add(cancel);
            ;
            panel.add(bok);
            frame.add(panel);
            frame.setSize(300, 100);
            frame.setVisible(true);

        }

        /**
         * A betöltést leokázó gomb eseménykezelõ osztálya
         */
        public class SaveOkButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.save(field.getText(), Prototype.openedMap, false)) {
                        JOptionPane.showMessageDialog(null, "Saving to file was successful!");
                        showGameMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Saving to file was not successful!");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Saving to file was not successful!");
                }
            }
        }

        /**
         * A betöltési ablakot bezáró gomb eseménykezelõ osztálya
         */
        public class CancelButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame = null;
            }
        }
    }

    /**
     * Exit gomb eseménykezelõ osztálya
     */
    public class ExitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Back to main menu gomb eseménykezelõje
     */
    public class backToMainMenu implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            polygons.clear();
            Prototype.g = null;
            showMainMenu();
        }
    }

    /**
     * New game gomb eseménykezelõ osztálya
     */
    public class StartGameButtonListener implements  ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        public void actionPerformed(ActionEvent e) {
            try{
                Prototype.startGame(3);
                JOptionPane.showMessageDialog(null, "The game has started!");
                showGameMenu();
                //to get results in String attribute
                Prototype.testMode = true;


            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Error while starting a new game!");
            }
        }
    }

    /**
     * Load gomb eseménykezelõ osztálya
     */
    public class LoadButtonListener implements ActionListener{

        /**
         * A felugró ablakon az input mezõ
         */
        JTextField field;

        /**
         * A felugró ablak kerete
         */
        JFrame frame;


        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame("Load");
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            label.setText("Filename:");
            field = new JTextField("Type here...");
            JButton bok = new JButton("Load");
            bok.addActionListener(new LoadOkButtonListener());
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new CancelButtonListener());
            panel.add(label);
            panel.add(field);
            panel.add(cancel);
;            panel.add(bok);
            frame.add(panel);
            frame.setSize(300, 100);
            frame.setVisible(true);

        }

        /**
         * A betöltést leokázó gomb eseménykezelõ osztálya
         */
        public class LoadOkButtonListener implements ActionListener{
            /**
             * Az eseménykezelõ függvény
             * @param e esemény
             */
            public void actionPerformed(ActionEvent e) {
                try{
                    if(Prototype.load(field.getText())){
                        JOptionPane.showMessageDialog(null, "Loading from file was successful!");
                        showGameMenu();
                        Prototype.testMode=true;
                        frame.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Loading from file was not successful!");
                    }
                }
                catch (Exception exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Loading from file was not successful!");
                }
            }
        }
        /**
         * A betöltési ablakot bezáró gomb eseménykezelõ osztálya
         */
        public class CancelButtonListener implements ActionListener{
            /**
             * Az eseménykezelõ függvény
             * @param e esemény
             */
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame = null;
            }
        }
    }

    /**
     * A ShowThings gomb eseménykezelõ osztálya
     */
    public class ShowThingsButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Prototype.listThingsOnField(true, true);
                String things = Prototype.results;
                currentItemsOnFieldTextField.setText("Current things on field: \n"+things);
            }
            catch(Exception ex){

            }
        }
    }

    /**
     * A Stat gomb eseménykezelõ osztálya
     */
    public class StatButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Prototype.stat();
                String things = Prototype.results;
                currentItemsOnFieldTextField.setText("Virologist stat: \n"+things);
            }
            catch(Exception ex){

            }
        }
    }

    /**
     * Konstansok az akció típusának meghatározásához
     */
    private static final int ATTACK = 1;
    private static final int SPREAD = 2;
    private static final int STEAL = 3;
    private static final int LEARN = 4;
    private static final int PICKUP = 5;
    private static final int CRAFT = 6;

    /**
     * Szótár az akció típusa, és az akció leírásának összepárosításához
     */
    private static final HashMap<Integer, String> actionType = new HashMap<Integer, String>()
    {
        {
            put(ATTACK, "Attack");
            put(SPREAD, "Spread");
            put(STEAL, "Steal");
            put(LEARN, "Learn");
            put(PICKUP, "Pick up");
            put(CRAFT, "Craft");
        }
    };


    /**
     * Felugró ablak a különbözõ parancsokhoz egységesen
     */
    public class popUpWindow{

        private JFrame mainFrame;
        private JPanel mainPanel;
        private JList playerList;
        private JList thingList;
        private int action_Type;

        /**
         * Felugró ablak konstruktora
         * @param virologistChoose kell e virológusnevek közül választani
         * @param itemChoose kell e a mezõkön lévõ itemek közül választani
         * @param action_Type_in milyen parancs
         */
        public popUpWindow(boolean virologistChoose, boolean itemChoose, int action_Type_in){
            action_Type = action_Type_in;
            mainFrame = new JFrame("Select");
            mainPanel =new JPanel();

            if(virologistChoose){
                JLabel label1 = new JLabel("Who: ");
                mainPanel.add(label1);

                //Az egy mezõn álló virológusok meghatározása, listába helyezése
                Prototype.listThingsOnField(true, false);
                String[] string = Prototype.results.split("\n");
                for (int i = 0; i < string.length; i++) {
                    string[i] = string[i].substring(3);
                }
                try{
                    playerList = new JList<String>(string);
                }
                catch(Exception ex){
                }
                playerList.addListSelectionListener(new SelectionListener());
                mainPanel.add(playerList);
            }
            if(itemChoose){
                JLabel label2 = new JLabel("Thing: ");
                mainPanel.add(label2);

                //Az egy mezõn lévõ dolgok meghatározása, listába helyezése
                Prototype.listThingsOnField(false, true);
                String[] string = Prototype.results.split("\n");
                for (int i = 0; i < string.length; i++) {
                    if(string[i].length()>=4)
                        string[i] = string[i].substring(3);
                }

                thingList =new JList<String>(string);
                mainPanel.add(thingList);
            }

            JButton actionButton = new JButton("");
            actionButton.setText(actionType.get(action_Type));
            ArrayList<String> string = new ArrayList<String>();

            switch(action_Type){
                case ATTACK:
                    actionButton.addActionListener(new ProceedAttackButtonListener());
                    break;
                case SPREAD:
                    actionButton.addActionListener(new ProceedSpreadButtonListener());
                    JLabel label2 = new JLabel("Agent: ");
                    mainPanel.add(label2);
                    string = new ArrayList<String>();
                    //A támadó virológus ágenseinek meghatározása, listába helyezése
                    ArrayList<Agent> ag = Prototype.g.getActualPlayer().getAgents().getAgents();
                    for (int i = 0; i < ag.size(); i++) {
                        string.add(ag.get(i).toString());
                    }
                    String[] strings = string.toArray(new String[0]);
                    thingList =new JList<String>(strings);
                    mainPanel.add(thingList);
                    break;
                case STEAL:
                    actionButton.addActionListener(new ProceedStealButtonListener());
                    JLabel label3 = new JLabel("Things: ");
                    mainPanel.add(label3);
                    thingList = new JList();
                    mainPanel.add(thingList);
                    break;
                case LEARN:
                    actionButton.addActionListener(new ProceedLearnButtonListener());
                    break;
                case PICKUP:
                    actionButton.addActionListener(new ProceedPickupButtonListener());
                    break;
                case CRAFT:
                    actionButton.addActionListener(new ProceedCraftButtonListener());
                    JLabel label4 = new JLabel("Which agent?: ");
                    mainPanel.add(label4);
                    String[] agents = {"Chorea_agent", "Oblivion_agent", "Invulnerability_agent", "Paralyze_agent"};
                    thingList = new JList<String>(agents);
                    mainPanel.add(thingList);
                    break;

            }
            mainPanel.add(actionButton);
            mainFrame.add(mainPanel);

            //set the size of frame
            mainFrame.setSize(450,130);

            mainFrame.setVisible(true);
        }

        /**
         * A virológus listából való választás eseményét lekezelõ osztály
         */
        public class SelectionListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(action_Type == STEAL){
                    mainPanel.remove(thingList);
                    thingList = new JList<String>(getVirologistItems());
                    mainPanel.add(thingList);
                    mainPanel.revalidate();

                }
            }
        }

        /**
         * Segédfüggvény a steal-hez, visszaadja egy listában a kiválasztott virológus dolgait
         * @return
         */
        public String[] getVirologistItems(){
            try{
                ArrayList<Virologist> virologists = Prototype.g.getVirologists();
                Virologist virologist =null;
                for(int i = 0; i< virologists.size(); i++){
                    if(virologists.get(i).getName().equals((String)playerList.getSelectedValue())){
                        virologist = virologists.get(i);
                    }
                }
                ArrayList<Agent> ag = virologist.getAgents().getAgents();
                ArrayList<Equipment> eq = virologist.getEquipment().getEquipment();
                ArrayList<Resource> re = virologist.getBackpack().getResources();
                ArrayList<String> string = new ArrayList<String>();
                for (int i = 0; i < ag.size(); i++) {
                    string.add(ag.get(i).toString());
                }
                for (int i = 0; i < eq.size(); i++) {
                    string.add(eq.get(i).toString());
                }
                for (int i = 0; i < re.size(); i++) {
                    string.add(re.get(i).toString());
                }
                String[] strings = string.toArray(new String[0]);
                return strings;
            }
            catch(Exception ex){
                return null;
            }


        }

        /**
         * A támadás véglegesítése gomb eseménykezelõ osztálya
         */
        public class ProceedAttackButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.attack((String) playerList.getSelectedValue())) {
                        JOptionPane.showMessageDialog(null, (String) playerList.getSelectedValue() + "is dead");
                    } else {
                        JOptionPane.showMessageDialog(null, "You could not attack " + (String) playerList.getSelectedValue());
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "You could not attack " + (String) playerList.getSelectedValue());
                }
                mainFrame.setVisible(false);
                mainFrame = null;
            }

        }

            /**
             * A kenés véglegesítése gomb eseménykezelõ osztálya
             */
            public class ProceedSpreadButtonListener implements ActionListener {
                /**
                 * Az eseménykezelõ függvény
                 *
                 * @param e esemény
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (Prototype.spread((String) playerList.getSelectedValue(), (String) thingList.getSelectedValue())) {
                            JOptionPane.showMessageDialog(null, "You have successfully spreaded " + (String) thingList.getSelectedValue() + " to " + (String) playerList.getSelectedValue());
                        } else {
                            JOptionPane.showMessageDialog(null, "You could not spread " + (String) thingList.getSelectedValue() + " to " + (String) playerList.getSelectedValue());
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "You could not spread  " + (String) thingList.getSelectedValue() + " to " + (String) playerList.getSelectedValue());
                    }
                    mainFrame.setVisible(false);
                    mainFrame = null;
                }
            }
        /**
         * A lopás véglegesítése gomb eseménykezelõ osztálya
         */
        public class ProceedStealButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.steal((String) playerList.getSelectedValue(), (String) thingList.getSelectedValue())) {
                        JOptionPane.showMessageDialog(null, "You have successfully stole " + (String) thingList.getSelectedValue() + " from " + (String) playerList.getSelectedValue());
                    } else {
                        JOptionPane.showMessageDialog(null, "You could not steal " + (String) thingList.getSelectedValue() + " from " + (String) playerList.getSelectedValue());
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "You could not steal  " + (String) thingList.getSelectedValue() + " from " + (String) playerList.getSelectedValue());
                }
                mainFrame.setVisible(false);
                mainFrame = null;
            }
        }

        /**
         * A tanulás véglegesítése gomb eseménykezelõ osztálya
         */
        public class ProceedLearnButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.learnGene((String) thingList.getSelectedValue())) {
                        JOptionPane.showMessageDialog(null, "You have successfully learned " + (String) thingList.getSelectedValue());
                        if(Prototype.results.startsWith("Current player")){
                            JOptionPane.showMessageDialog(null, Prototype.results);
                            Prototype.results="";
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You could not learn " + (String) thingList.getSelectedValue());
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "You could not learn " + (String) thingList.getSelectedValue());
                }
                mainFrame.setVisible(false);
                mainFrame = null;
            }
        }
        /**
         * A felvétel véglegesítése gomb eseménykezelõ osztálya
         */
        public class ProceedPickupButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.pickUp((String) thingList.getSelectedValue())) {
                        JOptionPane.showMessageDialog(null, "You have successfully picked up " + (String) thingList.getSelectedValue());
                    } else {
                        JOptionPane.showMessageDialog(null, "You could not pick up " + (String) thingList.getSelectedValue());
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "You could not pick up " + (String) thingList.getSelectedValue());
                }
                mainFrame.setVisible(false);
                mainFrame = null;
            }
        }
        /**
         * A craftolás véglegesítése gomb eseménykezelõ osztálya
         */
        public class ProceedCraftButtonListener implements ActionListener {
            /**
             * Az eseménykezelõ függvény
             *
             * @param e esemény
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Prototype.craftAgent((String) thingList.getSelectedValue())) {
                        JOptionPane.showMessageDialog(null, "You have successfully crafted " + (String) thingList.getSelectedValue());
                    } else {
                        JOptionPane.showMessageDialog(null, "You could not craft " + (String) thingList.getSelectedValue());
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "You could not craft " + (String) thingList.getSelectedValue());
                }
                mainFrame.setVisible(false);
                mainFrame = null;
            }
        }
    }

    /**
     * Az Attack gomb eseménykezelõ függvénye
     */
    public class AttackButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            popUpWindow window = new popUpWindow(true, false, ATTACK);
        }
    }

    /**
     * A Spread gomb eseménykezelõ függvénye
     */
    public class SpreadButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            popUpWindow window = new popUpWindow(true, false, SPREAD);
        }
    }

    /**
     * A Spread gomb eseménykezelõ függvénye
     */
    public class StealButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            popUpWindow window = new popUpWindow(true, false, STEAL);
        }
    }

    /**
     * A Spread gomb eseménykezelõ függvénye
     */
    public class LearnButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(Prototype.g.getActualPlayer().getField().toString().equals("Laboratory")){
                    popUpWindow window = new popUpWindow(false, true, LEARN);
                }
            }
            catch(Exception ex){

            }
        }
    }
    /**
     * A Pickup gomb eseménykezelõ függvénye
     */
    public class PickupButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(Prototype.g.getActualPlayer().getField().toString().equals("Warehouse") || Prototype.g.getActualPlayer().getField().toString().equals("Shelter")){
                    popUpWindow window = new popUpWindow(false, true, PICKUP);
                }
            }
            catch(Exception ex){

            }
        }
    }

    /**
     * A Craft gomb eseménykezelõ függvénye
     */
    public class CraftButtonListener implements ActionListener{
        /**
         * Az eseménykezelõ függvény
         * @param e esemény
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                popUpWindow window = new popUpWindow(false, false, CRAFT);
            }
            catch(Exception ex){

            }
        }
    }
    /**
     * Hozzaad a polygon listahoz egy polygont
     * @param p polygon amit hozzaadunk
     */
    public void addPolygon(Polygon p){
        polygons.add(p);
    }

    /**
     * Eseménykezelõ osztály a játék map-jában való kattintáshoz
     */
    public class StepMouseListener implements MouseListener {
        /**
         * Egér kattintás
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        /**
         * Egér gomb lenyomását kezelõ fv.
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {
            Field f = null;
            for (Polygon p : polygons) {
                if (p.inside(e.getX(), e.getY())){
                    f = p.field;
                    break;
                }
            }
            if (f != null) {
                try{
                    if (Prototype.g.getActualPlayer().getField().isNeighbour(f)) {
                        int idx = Prototype.g.getActualPlayer().getField().neighbours.indexOf(f);
                        Prototype.step(idx + 1);
                        drawPanel.repaint();
                        if(Prototype.results.startsWith("Stepped on Bear")){
                            JOptionPane.showMessageDialog(null, Prototype.results);
                            Prototype.results = "";
                        }
                    }
                }
                catch(Exception ex){

                }
            }
        }

        /**
         * Egér gomb elengedésését kezelõ fv.
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Kurzor területre való belépését lekezelõ fv.
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * A kurzor területrõl való kilépését kezelõ fv.
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * A következõ kör gombjához tartozó eseményeket kezelõ osztály
     */
    public class nextRoundButtonListener implements ActionListener{
        /**
         * Eseménykezelõ függvény
         * @param e the event to be processed
         */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Prototype.nextPlayer();
                    CurrentNameLabel.setText("Actual player: "+Prototype.g.getActualPlayer().getName());
                    gameMenuframe.revalidate();
                }
                catch(Exception ex){

                }
            }
        }
        public static Color getColor(int szinid) {
            switch (szinid) {
                case 0:
                    return lightColor;
                case 1:
                    return darkColor;
                case 2:
                    return greenColor;
                case 3:
                    return orangeColor;
            }
            return null;
        }
}
