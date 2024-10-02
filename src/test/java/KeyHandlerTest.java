import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import panels.KeyHandler;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import static org.junit.jupiter.api.Assertions.*;

//unit test for KeyHandlerTest class
public class KeyHandlerTest {

    private KeyHandler keyHandler;

    //setup for unit test
    @BeforeEach
    public void setUp() {
        keyHandler = new KeyHandler();
    }

    //test key states when they are pressed
    @Test
    public void testInitialKeyStates() {
        assertFalse(keyHandler.isUpPressed());
        assertFalse(keyHandler.isDownPressed());
        assertFalse(keyHandler.isLeftPressed());
        assertFalse(keyHandler.isRightPressed());
    }

    //testing key arrow input is recognized
    @Test
    public void testArrowKeyPress() {
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W'));
        assertTrue(keyHandler.isUpPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
        assertTrue(keyHandler.isDownPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
        assertTrue(keyHandler.isLeftPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D'));
        assertTrue(keyHandler.isRightPressed());
    }

    //testing key arrow release of input is recognized
    @Test
    public void testArrowKeyRelease() {
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W'));
        keyHandler.keyReleased(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W'));
        assertFalse(keyHandler.isUpPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
        keyHandler.keyReleased(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
        assertFalse(keyHandler.isDownPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
        keyHandler.keyReleased(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
        assertFalse(keyHandler.isLeftPressed());

        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D'));
        keyHandler.keyReleased(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D'));
        assertFalse(keyHandler.isRightPressed());
    }
}