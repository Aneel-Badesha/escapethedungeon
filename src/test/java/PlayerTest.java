import moving.Player;
import panels.GamePanel;
import panels.KeyHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
//unit test for playertest class
public class PlayerTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Player player;

    //setup for test
    @BeforeEach
    void setUp() {
        keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler,null);
        player = gamePanel.player;
    }

    //testing x and y position of player
    @Test
    void testSetPos() {
        player.setPos(3, 4);
        assertEquals(3 * 32, player.getX());
        assertEquals(4 * 32, player.getY());
        assertEquals(3, player.getTargetX());
        assertEquals(4, player.getTargetY());
    }

    //test player initialization
    @Test
    void testBlockedByWalls(){
        gamePanel.loadNextMap();
        player.setPos(4, 3);
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
        player.update();
        assertEquals(4 * 32, player.getX());
        assertEquals(3 * 32, player.getY());
        assertEquals(4, player.getTargetX());
        assertEquals(3, player.getTargetY());
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
        assertEquals(4 * 32, player.getX());
        assertEquals(3 * 32, player.getY());
        assertEquals(4, player.getTargetX());
        assertEquals(3, player.getTargetY());
    }

    @Test
    void testInit() {
        assertEquals(Player.DIRECTION.RIGHT, player.getDirection());
        assertNotNull(player.getHitBox());
    }

    //test that movement updates
    @Test
    void testUpdateMovement() {
        gamePanel.loadNextMap();
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W'));
        player.setPos(2, 2);
        player.update();
        assertEquals(2, player.getTargetX());
        assertEquals(1, player.getTargetY());
        assertEquals(Player.DIRECTION.UP, player.getDirection());
    }

    @Test
void testUpdateMovementDown() {
    gamePanel.loadNextMap();
    keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
    player.setPos(2, 2);
    player.update();
    assertEquals(2, player.getTargetX());
    assertEquals(3, player.getTargetY());
    assertEquals(Player.DIRECTION.DOWN, player.getDirection());
}

@Test
void testUpdateMovementLeft() {
    gamePanel.loadNextMap();
    keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A'));
    player.setPos(2, 2);
    player.update();
    assertEquals(1, player.getTargetX());
    assertEquals(2, player.getTargetY());
    assertEquals(Player.DIRECTION.LEFT, player.getDirection());
}

@Test
void testUpdateMovementRight() {
    gamePanel.loadNextMap();
    keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D'));
    player.setPos(1, 2);
    player.update();
    assertEquals(2, player.getTargetX());
    assertEquals(2, player.getTargetY());
    assertEquals(Player.DIRECTION.RIGHT, player.getDirection());
}


    //test that animation updates with movements and other actions
    @Test
    void testUpdateAnimation() {
        gamePanel.loadNextMap();
        Graphics2D g2d = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).createGraphics();
        player.setPos(5, 5);
        int prevFrame = player.getCurFrame();
        keyHandler.keyPressed(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S'));
        player.update();
        for(int i = 0; i < 12; i++)
            player.draw(g2d);
        int curFrame = player.getCurFrame();
        assertNotEquals(prevFrame, curFrame);
    }

    //testing that the speed buff effect works for the player
    @Test
    void testUpdateSpeedBuff() {
        assertFalse(player.getIsSpeedBuff());
        player.buffSpeed(2.0f, 50);
        assertTrue(player.getIsSpeedBuff());
        assertEquals(2.0f * 4, player.getSpeed());
        player.stopSpeedBuff();
        assertFalse(player.getIsSpeedBuff());
        assertEquals(4, player.getSpeed());
    }

    //testing draw works
    @Test
    void testDraw() {
        Graphics2D g2d = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).createGraphics();
        assertDoesNotThrow(() -> player.draw(g2d));

    }

    
}
