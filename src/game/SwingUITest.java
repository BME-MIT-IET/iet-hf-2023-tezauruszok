package game;

import game.OpeningMenu;
import game.WindowV;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import player.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SwingUITest {
    private JFrame frame;
    private JPanel panel1;

    private OpeningMenu menu;
    private Robot robot;

//    @BeforeEach
//    @Test
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
//            frame = new JFrame("Swing UI Test");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(300, 200);
//
//            JButton button = new JButton("Click Me");
//            button.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    buttonMouseClicked(evt);
//                }
//            });
//
//            frame.getContentPane().add(button);
//            frame.setVisible(true);
            frame = new JFrame("Swing UI Test");
            frame.setSize(600,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setResizable(false);
            panel1=new JPanel();
            panel1.setBounds(0,0,600,300);
            panel1.setBackground(new Color(64,95,111));
            panel1.setLayout(null);
            JButton button = new JButton("Click Me");
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        buttonMouseClicked(evt);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            panel1.add(button);
        });
    }



//    @Test
    public void testMouseClick() throws AWTException {

//            frame = new JFrame("Swing UI Test");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(300, 200);
//
//            JButton button = new JButton("Click Me");
//            button.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    buttonMouseClicked(evt);
//                }
//            });
//
//            frame.getContentPane().add(button);
//            frame.setVisible(true);
            frame = new JFrame("Swing UI Test");
            frame.setSize(600,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setResizable(false);
            panel1=new JPanel();
            panel1.setBounds(0,0,600,300);
            panel1.setBackground(new Color(64,95,111));
            panel1.setLayout(null);
            JButton button = new JButton("Click Me");
            button.setSize(200,200);
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    button.setText("Clicked!");
                    try {
                        buttonMouseClicked(evt);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
//            panel1.add(button);
//            frame.getContentPane().add(panel1);
            frame.getContentPane().add(button);
        frame.setVisible(true);

        Robot robot = new Robot();

        // Get the button's position relative to the screen
        Point buttonLocation = button.getLocation();
        int buttonX = 50; // Offset for button size and borders
        int buttonY = 50;

        // Simulate a mouse click on the button
        robot.mouseMove(buttonX, buttonY);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Wait for the event to be processed
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the button's text was updated
        JButton button1 = (JButton) frame.getContentPane().getComponent(0);
        assertEquals("Clicked!", button1.getText());
    }


    private void buttonMouseClicked(java.awt.event.MouseEvent evt) throws IOException {
        JButton button = (JButton) evt.getSource();
        frame.setTitle("atallitva");
        button.setText("Clicked!");

            FileWriter writer = new FileWriter("C:\\SynologyDrive\\1etM\\6. felev\\IET\\HF\\log.txt"); // Create a FileWriter object
            // Write content to the file
            writer.write("This is the first line.\n");
            writer.write("This is the second line.\n");
            writer.write("This is the third line.");
            writer.write(button.getText());
            writer.close(); // Close the writer to save and release resources
    }

//    @AfterEach
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            frame.dispose();
        });
    }

    private void arrangeWithOnePlayer() throws AWTException {

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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Press start game button
        Point startLocation = (((JPanel)menu.frame.getContentPane().getComponent(1)).getComponent(0).getLocationOnScreen());
        robot.mouseMove(startLocation.x+5, startLocation.y+5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openingGameOnePlayer() throws AWTException {
        arrangeWithOnePlayer();

        Virologist player = menu.getGame().getGtAtm().getCurrentPlayer();
        assertEquals("Barb", player.getName());
        boolean result = false;
        int inventoryCount = player.getMyEquipment().size() + player.getAgentCollection().size() + player.getEffects().size() + player.getLearnedCodes().size();
        assertEquals(0, inventoryCount);
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
