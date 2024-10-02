package object;

import java.awt.Graphics2D;

import Main.Utility;
import panels.GamePanel;
/**
 * Key object
 */
public class Key extends Object{
    /**
     * Constructor for Key
     * @param tileX x coordinate
     * @param tileY y coordinate
     * @param gp gamePanel
     */
    public Key(int tileX, int tileY, GamePanel gp){
        super(tileX, tileY, gp);
        if(images == null){
            final int NUM_KEY_FRAMES = 4;
            images = Utility.getImageSet("/Objects/Key/key", NUM_KEY_FRAMES, Utility.TILE_SIZE);
        }
    }
    /**
     * Collision method
     * Decreases the number of keys and increases the score
     */
    @Override
    public void collision(){
        gp.playSound(2);
        gp.objectManager.setNumKeys(gp.objectManager.getNumKeys()-1);
        gp.setScore(gp.getScore()+1);
        if(gp.objectManager.getNumKeys()== 0){
            gp.objectManager.getMainDoor().open();
        }
    }
    /**
     * Draws the key
     * @param g2d Graphics2D
     */
    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(animate(), tileX * Utility.TILE_SIZE, tileY * Utility.TILE_SIZE, null);
    }

    @Override
    public boolean isSolid(){
        return false;
    }

    @Override
    public boolean deleteOnCollision(){
        return true;
    }
}
