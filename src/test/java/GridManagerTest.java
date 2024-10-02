import grid.GridManager;
import grid.Tile;
import panels.GamePanel;
import panels.KeyHandler;
import Main.Utility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

//unit test for GridManager class
public class GridManagerTest {

    private GridManager gridManager;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    //initialize for the test
    @Before
    public void setUp() {
        keyHandler = new KeyHandler();
        gamePanel = new GamePanel(keyHandler, new JPanel());
        gridManager = new GridManager(gamePanel);
    }

    //testing loading tiles
    @Test
    public void testLoadTiles() {
        gridManager.loadTiles();

        for (int i = 0; i < gridManager.getNumTileTextures(); i++) {
            assertNotNull(gridManager.getTile(i));
        }
    }

    //testing loading a tile
    @Test
    public void testLoadTile() {
        Tile tile = gridManager.loadTile("brick1", true);

        assertNotNull(tile);
        assertTrue(tile.isSolid);
        assertNotNull(tile.image);
    }

    //testing loading a map
    @Test
    public void testLoadMap() {
        assertThrows(IllegalArgumentException.class, () -> gridManager.loadMap(0));
        gridManager.loadMap(1); // Assuming map1 exists in the resources/Maps directory
        for (int i = 0; i < Utility.maxScreenRow; i++) {
            for (int j = 0; j < Utility.maxScreenCol; j++) {
                try{
                    gridManager.isSolid(i, j);
                } 
                catch (Exception e) {
                    fail("Exception thrown while loading map: " + e.getMessage());
                }
            }
        }
    }

    //testing draw
    @Test
    public void testDraw() {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        assertDoesNotThrow(() -> gridManager.draw(g2d));
    }

    //testing if object on the map are solid or not
    @Test
    public void testIsSolid() {
        gridManager.loadMap(1);
        boolean solid = gridManager.isSolid(-1, 0);
        assertTrue(solid);

        solid = gridManager.isSolid(0, -1);
        assertTrue(solid);

        solid = gridManager.isSolid(Utility.maxScreenCol, 0);
        assertTrue(solid);

        solid = gridManager.isSolid(0, Utility.maxScreenRow);
        assertTrue(solid);

        solid = gridManager.isSolid(1, 0);
        assertFalse(solid);

        solid = gridManager.isSolid(0, 0);
        assertTrue(solid);
    }
}
