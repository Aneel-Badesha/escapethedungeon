package object;

import java.awt.Graphics2D;
import java.util.Random;

import Main.Utility;
import panels.GamePanel;

/**
 * The abstract class representing a bonus object in the game.
 * It extends the Object class.
 */
public abstract class Bonus extends Object {
    
   
    final int SPAWN_RANGE_SECONDS = 10;
    final int TIME_TO_EXPIRE = 20;
     /**
     * Variable initilization
     */
    protected int curTicks = 0, ticksUntilExpire = Utility.FPS * TIME_TO_EXPIRE, ticksUntilSpawn;
    private boolean expired = false, spawned = false;

    /**
     * Checks if the bonus has expired.
     * @return true if the bonus has expired, false otherwise.
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * Checks if the bonus has spawned.
     * @return true if the bonus has spawned, false otherwise.
     */
    public boolean isSpawned() {
        return spawned;
    }

    /**
     * Constructor for the Bonus class.
     * Sets the ticksUntilSpawn to a random number between 0 and SPAWN_RANGE_SECONDS.
     * @param tileX the x coordinate of the tile.
     * @param tileY the y coordinate of the tile.
     * @param gp the GamePanel object.
     */
    public Bonus(int tileX, int tileY, GamePanel gp) {
        super(tileX, tileY, gp);
        ticksUntilSpawn = new Random().nextInt(Utility.FPS * SPAWN_RANGE_SECONDS);
    }

    /**
     * Updates the bonus object.
     * If the bonus has not spawned, it will wait until the ticksUntilSpawn has been reached.
     * If the bonus has spawned, it will wait until the ticksUntilExpire has been reached.
     * If the bonus has expired, it will set expired to true.
     */
    public void update() {
        // Wait until spawned and then begin expiration timer
        if (!spawned) {
            if (curTicks >= ticksUntilSpawn) {
                spawned = true;
                curTicks = 0;
            } else {
                curTicks++;
            }
        } else if (curTicks >= ticksUntilExpire) {
            expired = true;
        } else {
            curTicks++;
        }
    }

    /**
     * Abstract method for the effect of the bonus.
     */
    public abstract void effect();

    /**
     * Handles the collision of the bonus object.
     * If the bonus has not expired and has spawned, it will call the effect method.
     */
    @Override
    public void collision() {
        if (!expired && spawned) {
            gp.playSound(4);
            effect();
            expired = true;
        }
    }

    /**
     * Draws the bonus object.
     * If the bonus has spawned and has not expired, it will draw the bonus.
     * @param g2d the Graphics2D object.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (spawned && !expired) {
            g2d.drawImage(animate(), tileX * Utility.TILE_SIZE, tileY * Utility.TILE_SIZE, null);
        }
    }
}
