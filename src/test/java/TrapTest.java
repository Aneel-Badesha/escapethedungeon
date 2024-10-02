import object.*;
import panels.GamePanel;
import panels.KeyHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//unit test for trap class
public class TrapTest {

    private GamePanel gamePanel;

    //setup for test
    @BeforeEach
    void setUp() {
        KeyHandler keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler, null);
    }

    //test spike traps
    @Test
    void testSpikes() {
        Trap spikes = new Spikes(0, 0, gamePanel);

        assertEquals("Spikes", spikes.getType());
        assertEquals(5, spikes.getNumFrames());
        assertEquals(1, spikes.getDamage());
        assertFalse(spikes.isSolid());
        assertFalse(spikes.deleteOnCollision());
    }

    //test monster traps
    @Test
    void testMonster() {
        Trap monster = new Monster(0, 0, gamePanel);

        assertEquals("monster", monster.getType());
        assertEquals(4, monster.getNumFrames());
        assertEquals(1, monster.getDamage());
        assertFalse(monster.isSolid());
        assertFalse(monster.deleteOnCollision());
    }

    //test trap collisions
    @Test
    void testCollision() {
        Trap spikes = new Spikes(0, 0, gamePanel);
        gamePanel.setScore(10);

        spikes.collision();


        assertEquals(9, gamePanel.getScore());
    }

    //test that collision leads to game over when needed
    @Test
    void testCollisionGameOver() {
        Trap spikes = new Spikes(0, 0, gamePanel);
        gamePanel.setScore(0);

        spikes.collision();

        assertEquals(0, gamePanel.getScore());
    }

    //test that traps are drawn
    @Test
    void testDraw() {
        Trap spikes = new Spikes(1, 1, gamePanel);
        gamePanel.setScore(10);

        spikes.collision();

        Graphics2D g2d = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).createGraphics();
        spikes.draw(g2d);

        assertTrue(spikes.isAnimationPlaying());
    }
}