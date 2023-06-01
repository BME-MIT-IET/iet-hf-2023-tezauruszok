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
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwingUITest {
    private OpeningMenu menu;
    private Robot robot;
    private final int idleTime = 750;


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
     * Opens main menu, chooses one or two players depending on the parameter, and then starts the game
     * @param multiplayer select one more player?s
     */
    public void arrangeHelper(boolean multiplayer) throws AWTException {
        // Starting game
        menu = new OpeningMenu();

        robot = new Robot();

        // Choosing player's name
        Point nameLocation = menu.scrollpane.getLocationOnScreen();
        int buttonX = nameLocation.x + 20; // Offset for button size and borders
        int buttonY = nameLocation.y + 10;
        robot.mouseMove(buttonX, buttonY);

        try {
            Thread.sleep(idleTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        ////
        if(multiplayer){
            int buttonX2 = nameLocation.x + 20; // Offset for button size and borders
            int buttonY2 = nameLocation.y + 30;
            robot.mouseMove(buttonX2, buttonY2);

            try {
                Thread.sleep(idleTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Shift should be pressed while selecting
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
        ////

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



    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @Order(1)
    class SimpleStepAndPickUpTest{
        @BeforeEach
        public void arrangeWithOnePlayer() throws AWTException {
            arrangeHelper(false);
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
            assertTrue(stepOnTable(5, 4), "Step failed");
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
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");

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
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");

            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
            assertEquals(1, player.getMyEquipment().size(), "inventory contains "+ player.getMyEquipment().size()+ " instances of the equipment on the field");
        }

        /**
         * Part of the "Simple step and pick up test"
         * Step 4:
         *      step down until the last bunker
         */
        @Test
        @Order(4)
        public void simpleStepAndPickupTest_StepFour() throws AWTException {
            // expected result:
            //			the inventory should only contain the first 3 equipment
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");
            assertTrue(stepOnTable(6, 6), "Step failed");
            assertTrue(stepOnTable(7, 6), "Step failed");
            assertTrue(stepOnTable(8, 6), "Step failed");

            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
            int amount = player.getMyEquipment().size();
            assertEquals(3, amount, "the inventory contains " + amount +  " equipment, only three is allowed at max");
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
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");
            assertTrue(stepOnTable(6, 6), "Step failed");
            assertTrue(stepOnTable(7, 6), "Step failed");
            assertTrue(stepOnTable(8, 6), "Step failed");
            assertTrue(stepOnTable(8, 7), "Step failed");

            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
            assertEquals(1, player.getLearnedCodes().size(), "Step failed");
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
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");
            assertTrue(stepOnTable(6, 6), "Step failed");
            assertTrue(stepOnTable(7, 6), "Step failed");
            assertTrue(stepOnTable(8, 6), "Step failed");
            assertTrue(stepOnTable(8, 7), "Step failed");
            assertTrue(stepOnTable(7, 7), "Step failed");
            assertTrue(stepOnTable(6, 7), "Step failed");
            assertTrue(stepOnTable(5, 7), "Step failed");

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
    }

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @Order(2)
    class SackAndJackEffectTest{
        @BeforeEach
        public void arrangeWithOnePlayer() throws AWTException {
            arrangeHelper(false);
        }
        /**
         * Part of the "Sack and jacket effect test:"
         * Step 1:
         *      step onto a storage
         */
        @Test
        @Order(0)
        public void SackAndJacketEffectTest_StepOne() throws AWTException {
            // expected result:
            //			amino acid and nucleotide counters are increased by 20
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");

            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
            assertEquals(20, player.getAminoAcid());
            assertEquals(20, player.getNucleotide());
        }

        /**
         * Part of the "Sack and jacket effect test:"
         * Step 2:
         *      step onto the bunker with sack
         */
        @Test
        @Order(1)
        public void SackAndJacketEffectTest_StepTwo() throws AWTException {
            // expected result:
            //			inventory contains sack
            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");

            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
            assertEquals(1, player.getMyEquipment().size(), "inventory contains "+ player.getMyEquipment().size()+ " instances of the equipment on the field");
        }

        /**
         * Part of the "Sack and jacket effect test:"
         * Step 3:
         *      step onto a storage
         */
        @Test
        @Order(2)
        public void SackAndJacketEffectTest_StepThree() throws AWTException {
            // expected result:
            //			amino acid and nucleotide counters are increased by 21
            Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();

            assertTrue(stepOnTable(5, 4), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");
            assertTrue(stepOnTable(5, 6), "Step failed");
            assertTrue(stepOnTable(5, 5), "Step failed");

            assertEquals(20+21, player.getAminoAcid(), "sometimes amino acid count and the label value is inconsistent");
            assertEquals(20+21, player.getNucleotide(), "sometimes nucleotid count and the label value is inconsistent");
        }

        @AfterEach
        public void closeGameWindow(){
            try{
                menu.getGame().dispose();
            }
            catch (Exception ignored){
            }
        }
    }


    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TwoVirologistsInteractionsTest {
        @BeforeEach
        public void arrangeWithTwoPlayer() throws AWTException {
            arrangeHelper(true);
        }

        /**
         * Part of the "Two virologists interactions test"
         * arrangements:
         * 			pick two players, hit Mentés button and then "Játék kezdése" button
         */
        @Test
        @Order(0)
        public void openingGameTwoPlayer() throws AWTException {
            LinkedList<Virologist> virologists= menu.getGame().getGtAtm().getPlayers();

            // expected result:
            //			map is loaded which consists of squares with players' name on their head, with empty inventory (two of them)

            // Virologist amount check
            assertEquals(2, virologists.size(), "There are " + virologists.size() + " should be two!");

            // Virologist name check
            assertEquals("Barb", virologists.get(0).getName(), "Player name is not correct!");
            assertEquals("Andy", virologists.get(1).getName(), "Player name is not correct!");

            // Virologist inventory check
            int inventoryCount = virologists.get(0).getMyEquipment().size() + virologists.get(0).getAgentCollection().size() + virologists.get(0).getEffects().size() + virologists.get(0).getLearnedCodes().size();
            assertEquals(0, inventoryCount, "Inventory is not empty for "+ virologists.get(0).getName() +"!");

            inventoryCount = virologists.get(1).getMyEquipment().size() + virologists.get(1).getAgentCollection().size() + virologists.get(1).getEffects().size() + virologists.get(1).getLearnedCodes().size();
            assertEquals(0, inventoryCount, "Inventory is not empty for "+ virologists.get(1).getName() +"!");
        }

        /**
         * Part of the "Two virologists interactions test"
         * step1:
         * 			step onto the other players field
         */
        @Test
        @Order(1)
        public void TwoVirologistsInteractionsTest_StepOne(){
            // expected result:
            //			two players are on the same field one of them covers the other
            assertTrue(stepOnTable(5, 4), "Step failed");

        }

        @AfterEach
        public void closeGameWindow(){
            try{
                menu.getGame().dispose();
            }
            catch (Exception ignored){
            }
        }
    }
}

