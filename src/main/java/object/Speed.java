package object;

import Main.Utility;
import panels.GamePanel;

/**
 * Represents a Speed bonus object in the game.
 * Inherits from the Bonus class.
 */
public class Speed extends Bonus{
    final float SPEED_BONUS = 2.0f;
    final int SPEED_BONUS_DURATION = Utility.FPS * 5;
    final int BONUS_SCORE = 2;
    
    /**
     * Constructs a Speed object at the specified tile position.
     * @param tileX The x-coordinate of the tile position.
     * @param tileY The y-coordinate of the tile position.
     * @param gp The GamePanel object.
     */
    public Speed(int tileX, int tileY, GamePanel gp){
        super(tileX, tileY, gp);
        images = Utility.getImageSet("/Objects/Bonus/Speed/", 4, Utility.TILE_SIZE);
    }
    
    /**
     * Increases the score and speed of the player.
     * Overrides the effect() method in the Bonus class.
     */
    @Override
    public void effect() {
        gp.setScore(gp.getScore()+BONUS_SCORE);
        gp.player.buffSpeed(SPEED_BONUS, SPEED_BONUS_DURATION);
    }

    /**
     * Checks if the Speed object is solid.
     * Overrides the isSolid() method in the Bonus class.
     * @return false, indicating that the Speed object is not solid.
     */
    @Override
    public boolean isSolid() {
        return false;
    }

    /**
     * Checks if the Speed object should be deleted upon collision.
     * Overrides the deleteOnCollision() method in the Bonus class.
     * @return false, indicating that the Speed object should not be deleted upon collision.
     */
    @Override
    public boolean deleteOnCollision() {
        return false;
    }
}