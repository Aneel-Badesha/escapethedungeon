package moving;

import java.awt.Rectangle;

/**
 * The abstract class representing a moving object in the game.
 */
public abstract class Moving {
    
    /**
     * position and speed vars
     */
    protected double x, y, speed;

    /**
     * 
     * @return x position value
     */
    public double getX(){
        return x;
    }

    /**
     * 
     * @return y position value
     */
    public double getY(){
        return y;
    }

    /**
     * 
     * @return speed value
     */
    public double getSpeed(){
        return speed;
    }

    /**
     * x hitbox is offset on both sides, y is only offset off the top
     */
    protected Rectangle hitBox;
    
    /**
     * Gets the hitbox of the moving object.
     * @return the hitbox of the moving object
     */
    public Rectangle getHitBox(){
        return hitBox;
    }

    abstract void update();
    abstract void draw(java.awt.Graphics2D g);

    /**
     * Enum representing the possible directions for movement.
     */
    public enum DIRECTION{ 
        /**
         * up 
         */ 
        UP, 
        /**
         * down
        */
        DOWN, 
        /**
         * left
         */
        LEFT, 
        /**
         * right
         */
        RIGHT}

    /**
     * direction var
     */
    protected DIRECTION direction;

    /**
     * Gets the direction of the moving object.
     * @return the direction of the moving object
     */
    public DIRECTION getDirection(){
        return direction;
    }

    /**
     * x and y vars
     */
    protected int prevX, prevY,targetX, targetY;

    /**
     * @return x target
     */
    public int getTargetX(){
        return targetX;
    }

    /**
     * @return y target
     */
    public int getTargetY(){
        return targetY;
    }

    /**
     * animation variables
     */
    protected int frameCounter, curFrame;

    /**
     * @return current frame
     */
    public int getCurFrame(){
        return curFrame;
    }
}
