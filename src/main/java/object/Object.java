package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import panels.GamePanel;
/**
 * an Interactive Object
 */
public abstract class Object {
    
    /**
     * tile x y and index vars
     */
    public int tileX, tileY, index;
    
    /**
     * frame vars
     */
    protected int frameCounter = 0, curFrame = 0, frameDelay = 10;
    
    /**
     * buffered image array
     */
    protected BufferedImage[] images = null;
    
    /**
     * gamepanel object
     */
    protected GamePanel gp;
    
    /**
     * Constructor for the object
     * @param tileX the x position of the object
     * @param tileY the y position of the object
     * @param gp the game panel
     */
    public Object(int tileX, int tileY, GamePanel gp){
        this.tileX = tileX;
        this.tileY = tileY;
        this.gp = gp;
    }
    /**
     * Method to animate the object, loads the next frame of the object's animation
     * @return the current frame of the object's animation
     */
    protected BufferedImage animate(){
        if(frameCounter >= frameDelay){
            curFrame++;
            if(curFrame >= images.length){
                curFrame = 0;
            }
            frameCounter = 0;
        }else{
            frameCounter++;
        }
        return images[curFrame];
    }
    /**
     * Abstract method to draw the object
     * @param g2d the graphics object
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * behavior of the object when it collides with another object
     */
    public abstract void collision();

    /**
     * check if the object is solid
     * @return true if the object is solid, false otherwise
     */
    abstract public boolean isSolid();

    /**
     * check if the object should be deleted on collision
     * @return true if the object should be deleted on collision, false otherwise
     */
    abstract public boolean deleteOnCollision();
}
