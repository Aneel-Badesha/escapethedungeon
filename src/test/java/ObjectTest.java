import object.*;
import panels.GamePanel;
import panels.KeyHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//unit test for object class
public class ObjectTest {

    private GamePanel gamePanel;

    //set up for unit test
    @BeforeEach
    void setUp() {
        KeyHandler keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler,null);
    }

    //testing object manager
    @Test
    void testObjectManager(){
        ObjectManager objectManager = new ObjectManager(gamePanel);
        assertNotNull(objectManager);

        objectManager.loadObjects(1);
        //assert that object manager throws exception for non-natural numbers
        assertThrows(IllegalArgumentException.class, () -> objectManager.loadObjects(0));
        
        assertEquals(3, objectManager.getNumKeys());
        assertNotNull(objectManager.getMainDoor());
        assertTrue(objectManager.getNumBonuses() > 0);
    }

    //testing speed of objects(character) works as intended
    @Test
    void testSpeedProperties() {
        Speed speed = new Speed(0, 0, gamePanel);
        assertFalse(speed.isSolid());
        assertFalse(speed.deleteOnCollision());
    }

    //testing the key item's properties
    @Test
    void testKeyProperties() {
        Key key = new Key(0, 0, gamePanel);
        assertFalse(key.isSolid());
        assertTrue(key.deleteOnCollision());
    }

    //testing door's properites
    @Test
    void testDoorProperties() {
        Door door = new Door(0, 0, gamePanel);
        assertTrue(door.isSolid());
        assertFalse(door.deleteOnCollision());
    }

    //testing collision physics between 2 objects
    @Test
    void testObjectCollision() {
        //test Speed collision
        Speed speed = new Speed(0, 0, gamePanel);
        while(!speed.isSpawned()){
            speed.update();
        }
        speed.collision();
        assertTrue(gamePanel.player.getIsSpeedBuff());
        assertEquals(2, gamePanel.getScore());
        

        //test Key collision
        Key key = new Key(0, 0, gamePanel);
        int initialKeys = gamePanel.objectManager.getNumKeys();
        key.collision();
        assertEquals(initialKeys - 1, gamePanel.objectManager.getNumKeys());
        assertEquals(3, gamePanel.getScore());

        //test Door collision
        Door door = new Door(0, 0, gamePanel);
        door.open();
        door.collision();
        assertTrue(gamePanel.levelWon());
    }

    //test drawing objects works
    @Test
    void testObjectDraw() {
        //check if the draw method doesn't throw an exception for each object type
        Graphics2D g2d = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).createGraphics();
        assertDoesNotThrow(() -> {
            Speed speed = new Speed(0, 0, gamePanel);
            speed.draw(g2d);

            Key key = new Key(0, 0, gamePanel);
            key.draw(g2d);

            Door door = new Door(0, 0, gamePanel);
            door.draw(g2d);
        });
    }
}