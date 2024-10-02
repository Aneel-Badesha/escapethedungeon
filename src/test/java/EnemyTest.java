import moving.DOrc;
import moving.KnightOrc;
import panels.GamePanel;
import panels.KeyHandler;
import moving.EnemyManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//this is a unit test to test the enemy class
public class EnemyTest {

    //initialize
    private GamePanel gamePanel;
    private EnemyManager enemyManager;
    @BeforeEach
    void setUp() {
        KeyHandler keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler, null);
        enemyManager = new EnemyManager(gamePanel);
    }

    //test loading invalid amount of enemies
    @Test
    void testLoadEnemiesWithInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> enemyManager.loadEnemies(0));
    }

    //test loading valid amount of enemies
    @Test
    void testLoadEnemiesWithValidNumber() {
        try {
            enemyManager.loadEnemies(1);
            enemyManager.update();
        } catch (Exception e) {
            fail("Exception thrown while loading enemies: " + e.getMessage());
        }
        try {
            enemyManager.loadEnemies(2);
            enemyManager.update();
        } catch (Exception e) {
            fail("Exception thrown while loading enemies: " + e.getMessage());
        }
    }

    //testing that an enemy actually occupys the tile it is in
    @Test
    void testEnemyMovesToPlayer() {
        gamePanel.startGame();
        gamePanel.player.setPos(17, 2);
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            fail("Thread interrupted");
        }
        assertTrue(gamePanel.isPaused());
    }

    @Test
    void testIsOccupiedWhenOccupied() {
        enemyManager.loadEnemies(1);
        assertTrue(enemyManager.isOccupied(18, 2));
    }

    //testing that an enemy doesnt occupy the tile it is not in
    @Test
    void testIsOccupiedWhenNotOccupied() {
        enemyManager.loadEnemies(1);
        assertFalse(enemyManager.isOccupied(10, 10));
    }

    //testing DOrc enemy name
    @Test
    void testDOrcName() {
        DOrc dOrc = new DOrc(0, 0, gamePanel);
        assertEquals("DOrc", dOrc.getEnemyName());
    }

    //testing  KinghtOrc name
    @Test
    void testKnightOrcName() {
        KnightOrc knightOrc = new KnightOrc(0, 0, gamePanel);
        assertEquals("KnightOrc", knightOrc.getEnemyName());
    }

    //testing collision physics
    @Test
    void testCollision() {
        gamePanel.startGame();
        DOrc dOrc = new DOrc(0, 0, gamePanel);
        gamePanel.player.setPos(0, 0);
        dOrc.update();
        
        assertTrue(gamePanel.isPaused());
    }

    //testing to see if it actually draws the enemy
    @Test
    void testDraw() {
        Graphics2D g2d = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
        assertDoesNotThrow(() -> enemyManager.draw(g2d));
    }
}
