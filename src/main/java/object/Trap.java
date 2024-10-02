package object;

import Main.Utility;
import panels.GamePanel;

import java.awt.Graphics2D;
/**
 * The abstract class representing a trap object in the game.
 */
abstract public class Trap extends Object{
    boolean playAnimation = false;

    /**
     * @return is animation playing or not
     */
    public boolean isAnimationPlaying(){
        return playAnimation;
    }

    /**
     * creates trap
     * @param x x position
     * @param y y position
     * @param gp gamepanel object
     */
    public Trap(int x, int y, GamePanel gp) {
        super(x, y, gp);
        loadFrames();
        frameDelay = 5;
    }
    /**
     * Returns the type of the trap as a string.
     * should be the same prefix as the image file name, without appending frame number and file extension
     * @return the type of the trap as a string.
     */
    abstract public String getType();
    /**
     * @return the number of frames for the trap animation.
     */
    abstract public int getNumFrames();
    /**
     * @return the damage caused by the trap.
     */
    abstract public int getDamage();
    /**
     * Loads the frames for the trap object.
     * The frames are loaded from the specified directory based on the trap type.
     * The number of frames and the tile size are used to determine the size of each frame.
     */
    private void loadFrames(){
        images = Utility.getImageSet("/Objects/Trap/" + getType(), getNumFrames(), Utility.TILE_SIZE);
    }

    /**
     * Handles the collision logic for the Trap object.
     * Decreases the player's score by the damage value of the Trap.
     * If the player's score becomes negative, triggers the game over.
     * Plays a sound effect and sets the playAnimation flag to true.
     */
    public void collision(){
        if(!playAnimation){
            gp.setScore(gp.getScore() - getDamage());
            if(gp.getScore() < 0){
                gp.setScore(0);
                gp.gameOver();
            }
            gp.playSound(8);
            playAnimation = true;
        }
    }

    /**
     * Draws the Trap object on the graphics context.
     * If the playAnimation flag is true, it animates the Trap by drawing each frame of the animation.
     * If the animation has played once, it stops playing and resets the current frame to the first frame.
     * If the playAnimation flag is false, it draws the first frame of the Trap.
     *
     * @param g2d The Graphics2D object to draw on.
     */
    @Override
    public void draw(Graphics2D g2d){
        if(playAnimation){
            g2d.drawImage(animate(), tileX * Utility.TILE_SIZE, tileY * Utility.TILE_SIZE, null);
            //if animation played once, stop playing it
            if(curFrame == images.length-1){
                playAnimation = false;
                curFrame = 0;
            }
        }else{
            g2d.drawImage(images[0], tileX * Utility.TILE_SIZE, tileY * Utility.TILE_SIZE, null);
        }
    }

    @Override
    public boolean isSolid(){
        return false;
    }

    @Override
    public boolean deleteOnCollision(){
        return false;
    }
}