import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import panels.GamePanel;
import panels.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//unit test for GamePanel class
public class GamePanelTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    //initialize objects to test
    @Before
    public void setUp() {
        keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler, new JPanel());
    }

    //testing getCurrentMap function
    @Test
    public void testGetCurrentMap() {
        assertEquals(0, gamePanel.getCurrentMap());
    }

    //testing setCurrentMap
    @Test
    public void testSetCurrentMap() {
        gamePanel.setCurrentMap(2);
        assertEquals(2, gamePanel.getCurrentMap());
    }

    //testing startGame function
    @Test
    public void testStartGame() {
        gamePanel.startGame();
        assertFalse(gamePanel.isPaused());
        gamePanel.stopMusic();
    }

    //testing GameOver function
    @Test
    public void testGameOver() {
        gamePanel.startGame();
        gamePanel.gameOver();
        assertTrue(gamePanel.isPaused());
    }

    //testing Victory panel
    @Test
    public void testVictory() {
        gamePanel.startGame();
        gamePanel.victory();
        assertTrue(gamePanel.isPaused());
    }

    //testing loading next map
    @Test
    public void testLoadNextMap() {
        gamePanel.setCurrentMap(0);
        gamePanel.loadNextMap();
        assertEquals(1, gamePanel.getCurrentMap());
    }

    //testing update function
    @Test
    public void testUpdate() {
        try {
            gamePanel.startGame();
            gamePanel.update();
        } catch (Exception e) {
            fail("update should not throw exceptions");
        }
    }

    //testing paintComponenet function
    @Test
    public void testPaintComponent() {
        gamePanel.startGame();

        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
    
        try {
            gamePanel.paintComponent(g2d);
        } catch (Exception e) {
            fail("paintComponent should not throw exceptions: " + e.getMessage());
        } finally {
            // Dispose the Graphics object
            g2d.dispose();
        }
    }
}