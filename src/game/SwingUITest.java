package game;

import game.OpeningMenu;
import game.WindowV;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import player.GameTable;
import player.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwingUITest {
    private JFrame frame;
    private JPanel panel1;

    private OpeningMenu menu;
    private Robot robot;
    private final int idleTime = 500;

    @BeforeEach
    public void arrangeWithOnePlayer() throws AWTException {

        // Starting game
        menu = new OpeningMenu();

        robot = new Robot();

        // Choosing player's name
        Point nameLocation = menu.scrollpane.getLocationOnScreen();
        int buttonX = nameLocation.x + 20; // Offset for button size and borders
        int buttonY = nameLocation.y + 10;
        robot.mouseMove(buttonX, buttonY);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Press save button
        Point saveLocation = ((JPanel)((JPanel)menu.frame.getContentPane().getComponent(0)).getComponent(2)).getComponent(2).getLocationOnScreen();
        robot.mouseMove(saveLocation.x+5, saveLocation.y+5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(idleTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Press start game button
        Point startLocation = (((JPanel)menu.frame.getContentPane().getComponent(1)).getComponent(0).getLocationOnScreen());
        robot.mouseMove(startLocation.x+5, startLocation.y+5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        try {
            Thread.sleep(idleTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Robot steps on the desired field
     * @param row col
     * @param col row
     * @return success?
     */
    public boolean stepOnTable(int row, int col){
        robot.mouseMove(50 + col * 90, 100 + row * 90);

        try {
            Thread.sleep(idleTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        try {
            Thread.sleep(idleTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FieldV.Pos position = menu.getGame().getGtAtm().getCurrentPlayer().getMyField().view.getPos();
        return position.getX() == row && position.getY() == col;
    }

    /**
     * Part of the "Simple step and pick up test"
     * arrangements:
     * 			pick one player, hit "Mentés" button and then "Játék kezdése" button
     */
    @Test
    @Order(0)
    public void openingGameOnePlayer() throws AWTException {
        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();

        // expected result:
        //			map is loaded which consists of squares with player's name on its head, with empty inventory

        assertEquals("Barb", player.getName(), "Player name is not correct!");
        int inventoryCount = player.getMyEquipment().size() + player.getAgentCollection().size() + player.getEffects().size() + player.getLearnedCodes().size();
        assertEquals(0, inventoryCount, "Inventory is not empty!");
    }


    /**
     * Part of the "Simple step and pick up test"
     * Step 1:
     *      Click on the field next to the player
     */
    @Test
    @Order(1)
    public void simpleStepAndPickupTest_StepOne() throws AWTException {
        // expected result:
        //			the player steps on the chosen field
        assertTrue(stepOnTable(5, 4), "Nem jó");


    }

    /**
     * Part of the "Simple step and pick up test"
     * Step 2:
     *      click on the storage next to the player
     */
    @Test
    @Order(2)
    public void simpleStepAndPickupTest_StepTwo() throws AWTException {
        // expected result:
        //			the player steps on the chosen storage amino acid and nucleotide counters are increased by 20
        assertTrue(stepOnTable(5, 4), "Nem jó");
        assertTrue(stepOnTable(5, 5), "Nem jó");

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        assertEquals(20, player.getAminoAcid());
        assertEquals(20, player.getNucleotide());
    }

    /**
     * Part of the "Simple step and pick up test"
     * Step 3:
     *      click on the bunker next to the player
     */
    @Test
    @Order(3)
    public void simpleStepAndPickupTest_StepThree() throws AWTException {
        // expected result:
        //			the player steps on the chosen bunker the equipment on the field is displayed in the inventory
        assertTrue(stepOnTable(5, 4), "Nem jó");
        assertTrue(stepOnTable(5, 5), "Nem jó");
        assertTrue(stepOnTable(5, 6), "Nem jó");

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        assertEquals(1, player.getMyEquipment().size(), "inventory contains "+ player.getMyEquipment().size()+ " instances of the equipment on the field");
    }

    /**
     * Part of the "Simple step and pick up test"
     * Step 4:
     *      step down until the last bunker
     */
    /*TODO: ATNEZNI MIERT CSAK AZ ELSO 3-NAK SZABAD NALA LENNIE?*/
    @Test
    @Order(4)
    public void simpleStepAndPickupTest_StepFour() throws AWTException {
        // expected result:
        //			the inventory should only contain the first 3 equipment
        assertTrue(stepOnTable(5, 4), "Nem jó");
        assertTrue(stepOnTable(5, 5), "Nem jó");
        assertTrue(stepOnTable(5, 6), "Nem jó");
        assertTrue(stepOnTable(6, 6), "Nem jó");
        assertTrue(stepOnTable(7, 6), "Nem jó");
        assertTrue(stepOnTable(8, 6), "Nem jó");

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        assertEquals(3, player.getMyEquipment().size(), "the inventory contains all kinds of equipment");
    }

    /**
     * Part of the "Simple step and pick up test"
     * Step 5:
     *      click on the lab next to the player
     */
    @Test
    @Order(5)
    public void simpleStepAndPickupTest_StepFive() throws AWTException {
        // expected result:
        //			the player steps on the chosen lab a genetic code which was on the field is displayed in the inventory
        assertTrue(stepOnTable(5, 4), "Nem jó");
        assertTrue(stepOnTable(5, 5), "Nem jó");
        assertTrue(stepOnTable(5, 6), "Nem jó");
        assertTrue(stepOnTable(6, 6), "Nem jó");
        assertTrue(stepOnTable(7, 6), "Nem jó");
        assertTrue(stepOnTable(8, 6), "Nem jó");
        assertTrue(stepOnTable(8, 7), "Nem jó");

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        assertEquals(1, player.getLearnedCodes().size(), "Nem jó");
    }

    /**
     * Part of the "Simple step and pick up test"
     * Step 6:
     *      step up until the last lab
     */
    @Test
    @Order(6)
    public void simpleStepAndPickupTest_StepSix() throws AWTException {
        // expected result:
        //			game should be over, the virologist should win
        assertTrue(stepOnTable(5, 4), "Nem jó");
        assertTrue(stepOnTable(5, 5), "Nem jó");
        assertTrue(stepOnTable(5, 6), "Nem jó");
        assertTrue(stepOnTable(6, 6), "Nem jó");
        assertTrue(stepOnTable(7, 6), "Nem jó");
        assertTrue(stepOnTable(8, 6), "Nem jó");
        assertTrue(stepOnTable(8, 7), "Nem jó");
        assertTrue(stepOnTable(7, 7), "Nem jó");
        assertTrue(stepOnTable(6, 7), "Nem jó");
        assertTrue(stepOnTable(5, 7), "Nem jó");

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        GameTable game = menu.getGame().getGtAtm();
        assertTrue(game.getWinGame(), "games goes on");
    }


    @AfterEach
    public void closeGameWindow(){
        try{
            menu.getGame().dispose();
        }
        catch (Exception ignored){
        }
    }

//        // Move the Player
//        robot.mouseMove(300, 650);
//        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        assertEquals("Barb", menu.getname(0));

}

